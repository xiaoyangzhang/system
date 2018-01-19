package com.yhyt.health.dao;

import com.yhyt.health.model.QuartzJobLog;

public interface QuartzJobLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(QuartzJobLog record);

    int insertSelective(QuartzJobLog record);

    QuartzJobLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(QuartzJobLog record);

    int updateByPrimaryKey(QuartzJobLog record);
}