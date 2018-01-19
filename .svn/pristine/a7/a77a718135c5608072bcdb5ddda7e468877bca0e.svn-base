package com.yhyt.health.dao;

import com.yhyt.health.model.ServiceTaskLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceTaskLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ServiceTaskLog record);

    int insertSelective(ServiceTaskLog record);

    ServiceTaskLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ServiceTaskLog record);

    int updateByPrimaryKey(ServiceTaskLog record);

    List<ServiceTaskLog> selectByTaskId(Long serviceTaskId);
}