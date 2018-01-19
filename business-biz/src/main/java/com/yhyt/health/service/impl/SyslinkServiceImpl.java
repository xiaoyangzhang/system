package com.yhyt.health.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhyt.health.dao.BaseDao;
import com.yhyt.health.dao.SysLinkMapper;
import com.yhyt.health.model.SysLink;
import com.yhyt.health.service.SyslinkService;
import com.yhyt.health.util.WebUtils;

@Service
public class SyslinkServiceImpl extends BaseServiceImpl<SysLink> implements SyslinkService{

	@Autowired
	private SysLinkMapper linkMapper;
	@Override
	public BaseDao<SysLink> getBaseDao() {
		return this.linkMapper;
	}
	
	/**
	 * 查询参数转换
	 */
	void paramsCharge(Map params,SysLink syslink){
		if(syslink!=null){
			if(syslink.getCode()!=null){
				params.put("code", syslink.getCode());
			}
			if(StringUtils.isNotBlank(syslink.getContent())){
				params.put("content", syslink.getContent());
			}
		}
	}
	
	/**
	 * 保存前参数检查
	 * @param persistable
	 */
	void editBefore(SysLink syslink){
		if(syslink.getId()==null){
			syslink.setCreateTime(new Date());
			syslink.setUpdateTime(new Date());
		}
		syslink.setOperatorId(Long.valueOf(WebUtils.getCurrentUserId()));
		
	}
}
