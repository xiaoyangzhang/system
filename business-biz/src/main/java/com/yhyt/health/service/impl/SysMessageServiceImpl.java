package com.yhyt.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhyt.health.dao.BaseDao;
import com.yhyt.health.dao.SysMessageMapper;
import com.yhyt.health.model.SysMessage;
import com.yhyt.health.service.SysMessageService;

@Service
public class SysMessageServiceImpl extends BaseServiceImpl<SysMessage> implements SysMessageService{

	@Autowired
	private SysMessageMapper messageMapper;
	
	@Override
	public BaseDao<SysMessage> getBaseDao() {
		return this.messageMapper;
	}

}
