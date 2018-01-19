package com.yhyt.health.service.impl;

import com.yhyt.health.dao.SysUpgradeMapper;
import com.yhyt.health.model.SysUpgrade;
import com.yhyt.health.model.UpgradeQuery;
import com.yhyt.health.service.UpGradeService;
import com.yhyt.health.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpGradeServiceImpl implements UpGradeService {

    @Autowired
    private SysUpgradeMapper sysUpgradeMapper;
    @Override
    public int insert(SysUpgrade sysUpgrade) {
        sysUpgrade.setCreateTime(new Date());
        sysUpgrade.setUpdateTime(new Date());
        return sysUpgradeMapper.insertSelective(sysUpgrade);
    }

    @Override
    public SysUpgrade selectById(long id) {
        return sysUpgradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SysUpgrade> getSysUpGradeListPage(UpgradeQuery query) {
//        Page<SysUpgrade> page = PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        Map<String,Object> params = new HashMap<String,Object>();
        if (StringUtils.isNotEmpty(query.getAppType())){
            params.put("appType", query.getAppType());
        }
        if (query.getClientType()!=null){
            params.put("clientType", query.getClientType());
        }
        if (query.getUpgradeEndTime()!=null){

            params.put("upgradeEndTime", query.getUpgradeEndTime());
        }
        if (query.getUpgradeStartTime()!=null){

            params.put("upgradeStartTime", query.getUpgradeStartTime());
        }
        Page<SysUpgrade> page = new Page<SysUpgrade>();
        if(query.getPageIndex()==null || query.getPageIndex()==0){
             query.setPageIndex(1);
        }
        page.setPageNo(query.getPageIndex());
        if(query.getPageSize() != null && query.getPageSize() != 0){
            page.setPageSize(query.getPageSize());
        }
        params.put("page", page);
        page.setResult(sysUpgradeMapper.selectUpGradeListPage(params));
//      PageInfo<SysUpgrade> pageInfo = new PageInfo<>(sysUpgrades);
        return page;
    }

    @Override
    public int update(SysUpgrade sysUpgrade) {
        sysUpgrade.setUpdateTime(new Date());
        return sysUpgradeMapper.updateByPrimaryKeySelective(sysUpgrade);
    }
}
