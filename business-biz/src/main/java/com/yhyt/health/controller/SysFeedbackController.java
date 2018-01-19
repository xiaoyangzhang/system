package com.yhyt.health.controller;

import com.alibaba.fastjson.JSON;
import com.yhyt.health.config.PathConfiguration;
import com.yhyt.health.model.SysFeedback;
import com.yhyt.health.model.SysMessage;
import com.yhyt.health.service.SysFeedbackService;
import com.yhyt.health.service.SysMessageService;
import com.yhyt.health.util.HealthDateUtil;
import com.yhyt.health.util.Page;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value="",description ="app意见反馈接口")
@ControllerAdvice
@RequestMapping("/sysfeedback")
@RestController
public class SysFeedbackController {

	private static Logger logger = LoggerFactory.getLogger(SysFeedbackController.class);
	
	@Autowired
	private SysFeedbackService sysFeedbackService;
	
	@Resource
	private RestTemplate loadBalanced;
	@Autowired
	private PathConfiguration pathConfiguration;
	
	public RestTemplate getLoadBalanced() {
		return loadBalanced;
	}

	public void setLoadBalanced(RestTemplate loadBalanced) {
		this.loadBalanced = loadBalanced;
	}
	
	@ApiIgnore
	@RequestMapping("/page")
	public Page<SysFeedback> findSysFeedbackPageList(SysFeedback feedback,String startDate,String endDate,Integer pageIndex,Integer pageSize){
		Map<String,Object> params = new HashMap<String,Object>();
		if(feedback.getUserId()!=null){
			params.put("userId", feedback.getUserId());
		}
		if(StringUtils.isNotBlank(feedback.getUsername())){
			params.put("username", feedback.getUsername());
		}
		if(feedback.getUseType()!=null){
			params.put("useType", feedback.getUseType());
		}
		if(StringUtils.isNotBlank(feedback.getRealname())){
			params.put("realname", feedback.getRealname());
		}
		if(StringUtils.isNotBlank(feedback.getContent())){
			params.put("content", feedback.getContent());
		}
		if(feedback.getDealState()!=null){
			params.put("dealState", feedback.getDealState());
		}
		if(StringUtils.isNotBlank(startDate)){
			params.put("startDate", HealthDateUtil.parseDate(startDate+" 00:00:00"));
		}
		if(StringUtils.isNotBlank(endDate)){
			params.put("endDate", HealthDateUtil.parseDate(endDate+" 23:59:59"));
		}
		return this.sysFeedbackService.findPersistableList(params, pageIndex,pageSize);
	}
	
	@ApiIgnore
	@RequestMapping("/")
	public List<SysFeedback> findSysFeedbackList(SysFeedback feedback,String startDate,String endDate){
		Map<String,Object> params = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(feedback.getUsername())){
			params.put("userName", feedback.getUsername());
		}
		if(feedback.getUseType()!=null){
			params.put("useType", feedback.getUseType());
		}
		if(StringUtils.isNotBlank(feedback.getRealname())){
			params.put("realName", feedback.getRealname());
		}
		if(StringUtils.isNotBlank(feedback.getContent())){
			params.put("content", feedback.getContent());
		}
		if(feedback.getDealState()!=null){
			params.put("dealState", feedback.getDealState());
		}
		params.put("startDate", HealthDateUtil.parseDate(startDate+" 00:00:00"));
		params.put("endDate", HealthDateUtil.parseDate(endDate+" 23:59:59"));
		return this.sysFeedbackService.findPersistableList(params);
	}
	
	/*@ApiOperation(value="意见反馈", notes="系统")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "content", value = "反馈内容",paramType="query", required = true, dataType = "String"),
        @ApiImplicitParam(name = "deviceInfo", value = "机器类型", paramType="query", required = true, dataType = "String"),
        @ApiImplicitParam(name="appVersion",value="app版本号",paramType="query", required = true, dataType = "String"),
        @ApiImplicitParam(name="useType",value="用户类型 2-医生  1 病人",paramType="query", required = true, dataType = "String"),
    })*/
	@ApiIgnore
	@PostMapping("/edit")
	public SysFeedback editSysFeedback(@RequestBody SysFeedback feeback){
//		feeback.setUserId(Long.valueOf(WebUtils.getCurrentUserId()));
//		feeback.setUsername(WebUtils.getCurrentUserName());
//		feeback.setUsername(WebUtils.getCurrentRealUserName());
		return this.sysFeedbackService.edit(feeback);
	}
	
	@ApiIgnore
	@GetMapping("/feedbackList/query/{userId}/{userType}")
	public List<SysFeedback> queryFeedbackList(@PathVariable("userId")long id,@PathVariable("userType")long type){
		return sysFeedbackService.queryFeedbackList(id,type);
	}

	@PostMapping("/add")
	public int addFeedback(@RequestBody SysFeedback feeback){
//		sysFeedback.setCreateTime(new Date());
		return sysFeedbackService.insert(feeback);
	}

	@Autowired
	private SysMessageService messageService;
	
	@ApiIgnore
	@RequestMapping("/message/send")
	public SysMessage sendMessage(SysMessage message){
		message.setCreateTime(new Date());
		SysMessage sysmessage = this.messageService.edit(message);
		//TODO 发送消息
		try {
			Long userId = sysmessage.getDoctorId();
			int userType = 1;
			if(userId==null){
				userId = sysmessage.getPatientId();
				userType = 2;
			}
			 Map appResult = this.loadBalanced.postForObject(pathConfiguration.getDialogUrl()+
					 "/dialog/sendmessage?userId={1}&userType={2}&messageTitle={3}&messageContent={4}&messageType={5}",
					 null, Map.class, userId, userType, message.getTitle(), sysmessage.getMessage(), 1);
			//发送系统消息
//			this.loadBalanced.postForEntity(pathConfiguration.getDialogUrl()+
//					"/dialog/addsysmessage?receiverId={1}&receiverType={2}&title={3}&content={4}",
//					null, Map.class, userId, userType, message.getTitle(), sysmessage.getMessage());
			logger.info("send message result,appresult:{}", JSON.toJSONString(appResult));
			 if (appResult != null && !"200".equals(appResult.get("status"))) {
			     logger.error("receiverId : " + userId + "content : " +  sysmessage.getMessage() + " 发送失败!");
			 }
		} catch (RestClientException e) {
			e.printStackTrace();
			logger.error("console send message error,error:{} ",e);
		}
		return sysmessage;
	}
	
	@ApiIgnore
	@RequestMapping("/message/page")
	public Page<SysMessage> findMessageList(SysMessage message,Integer pageIndex,Integer pageSize){
		Map<String,Object> params = new HashMap<String,Object>();
		if(message!=null&&message.getDoctorId()!=null){
			params.put("doctorId", message.getDoctorId());
		}
		if(message!=null&&message.getPatientId()!=null){
			params.put("patientId", message.getPatientId());
		}
		return this.messageService.findPersistableList(params, pageIndex,pageSize);
	}
}
