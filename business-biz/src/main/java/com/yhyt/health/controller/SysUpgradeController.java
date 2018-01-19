package com.yhyt.health.controller;

import com.yhyt.health.dao.SysUpgradeMapper;
import com.yhyt.health.model.SysUpgrade;
import com.yhyt.health.service.UpGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value="",description ="app版本接口")
@ControllerAdvice
@RestController
@RequestMapping("/sys")
public class SysUpgradeController {

	@Autowired
	private SysUpgradeMapper upgradeMapper;
	@Autowired
	private UpGradeService upGradeService;
	
	@ApiOperation(value="app最新版本接口", notes="系统")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "clientType", value = "机器类型(客户端类型1-android 2 ios 等)",paramType="query", required = true, dataType = "String"),
        @ApiImplicitParam(name = "appType", value = "客户端类型(hh-doctor-hd 海虹新医疗HD hh-doctor 海虹新医疗 health 新健康端)", paramType="query", required = true, dataType = "String"),
        @ApiImplicitParam(name="appVersion",value="app版本号",paramType="query", required = true, dataType = "String")
    })
	@GetMapping("/isUpgrade")
	public SysUpgrade findNewVersion( @RequestParam("clientType")String clientType,
									  @RequestParam("appType")String appType,
									  @RequestParam("appVersion")String appVersion){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("clientType", clientType);
		params.put("appType", appType);
//		params.put("appVersion", appVersion);
		List<SysUpgrade> upgradeList = this.upgradeMapper.findPersistableList(params);
		if(!CollectionUtils.isEmpty(upgradeList) && Integer.parseInt(appVersion)<Integer.parseInt(upgradeList.get(0).getAppVersion()==null?"0":upgradeList.get(0).getAppVersion())){
			SysUpgrade sysUpgrade = upgradeList.get(0);
			String content = sysUpgrade.getContent();
			if (!StringUtils.isEmpty(content)){

				String replaceAll = content.replaceAll("\\<p>", "").
						replaceAll("\\</p>", "\n").
						replaceAll("\\<br>","\n").
						replaceAll("&nbsp;"," ");
				sysUpgrade.setContent(replaceAll);
			}
			return upgradeList.get(0);
		}else{
			return null;
		}
	}



}
