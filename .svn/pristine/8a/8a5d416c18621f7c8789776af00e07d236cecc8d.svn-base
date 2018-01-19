package com.yhyt.health.service.impl;

import com.yhyt.health.dao.BaseDao;
import com.yhyt.health.dao.SysFeedbackMapper;
import com.yhyt.health.model.SysFeedback;
import com.yhyt.health.service.SysFeedbackService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysFeedbackServiceImpl extends BaseServiceImpl<SysFeedback> implements SysFeedbackService{

	@Autowired
	private SysFeedbackMapper feedbackMapper;
	
	@Override
	public BaseDao<SysFeedback> getBaseDao() {
		return this.feedbackMapper;
	}

	/**
	 * 查询参数转换
	 */
	void paramsCharge(Map params,SysFeedback feedback){
		if(feedback!=null){
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
		}
	}
	/**
	 * 保存前参数检查
	 * @param persistable
	 */
	void editBefore(SysFeedback feedback){
		if(feedback.getId()==null){
			feedback.setCreateTime(new Date());
		}
	}
	@Override
	public List<SysFeedback> queryFeedbackList(long userId, long userType) {
		return feedbackMapper.queryFeedback(userId,userType);
	}

	@Override
	public int insert(SysFeedback sysFeedback) {
		sysFeedback.setCreateTime(new Date());
		return feedbackMapper.insertSelective(sysFeedback);
	}
}
