package com.yhyt.health.dao;

import com.yhyt.health.model.SysFeedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysFeedbackMapper  extends BaseDao<SysFeedback> {
    int deleteByPrimaryKey(Long id);

    int insert(SysFeedback record);

    int insertSelective(SysFeedback record);

    SysFeedback selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysFeedback record);

    int updateByPrimaryKey(SysFeedback record);
    List<SysFeedback> queryFeedback(@Param("userId") long userId, @Param("userType") long userType);

}