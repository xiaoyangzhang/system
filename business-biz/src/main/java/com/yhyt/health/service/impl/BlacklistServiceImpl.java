package com.yhyt.health.service.impl;

import com.yhyt.health.dao.SysBlacklistMapper;
import com.yhyt.health.model.SysBlacklist;
import com.yhyt.health.service.SysBlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlacklistServiceImpl implements SysBlackService {

    @Autowired
    private SysBlacklistMapper sysBlacklistMapper;
    @Override
    public List<SysBlacklist> getSysBlacksByUserId(long userId, long userType) {
        return sysBlacklistMapper.getSysBlacksByUserId(userId,userType);
    }

    @Override
    public SysBlacklist addBlacklist(SysBlacklist sysBlacklist) {
        sysBlacklist.setCreateTime(new Date());
        sysBlacklistMapper.insertSelective(sysBlacklist);
        return sysBlacklistMapper.selectByPrimaryKey(sysBlacklist.getId());
    }

    @Override
    public SysBlacklist selectById(long id) {
        return sysBlacklistMapper.selectByPrimaryKey(id);
    }
}
