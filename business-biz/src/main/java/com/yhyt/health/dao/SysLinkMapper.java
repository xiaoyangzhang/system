package com.yhyt.health.dao;

import com.yhyt.health.model.SysLink;

public interface SysLinkMapper extends BaseDao<SysLink>{
    int deleteByPrimaryKey(Long id);

    int insert(SysLink record);

    int insertSelective(SysLink record);

    SysLink selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLink record);

    int updateByPrimaryKey(SysLink record);
}