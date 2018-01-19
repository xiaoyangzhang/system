package com.yhyt.health.dao;

import com.yhyt.health.model.SysBlacklist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysBlacklistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysBlacklist record);

    int insertSelective(SysBlacklist record);

    SysBlacklist selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysBlacklist record);

    int updateByPrimaryKey(SysBlacklist record);

    List<SysBlacklist> getSysBlacksByUserId(@Param("userId") long userId,@Param("userType") long userType);
}