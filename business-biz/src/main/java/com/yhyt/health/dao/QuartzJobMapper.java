package com.yhyt.health.dao;

import com.yhyt.health.model.QuartzJob;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuartzJobMapper {
    int deleteByPrimaryKey(Long jobId);

    int insert(QuartzJob record);

    int insertSelective(QuartzJob record);

    QuartzJob selectByPrimaryKey(Long jobId);

    int updateByPrimaryKeySelective(QuartzJob record);

    int updateByPrimaryKey(QuartzJob record);

    List<QuartzJob> list();
}