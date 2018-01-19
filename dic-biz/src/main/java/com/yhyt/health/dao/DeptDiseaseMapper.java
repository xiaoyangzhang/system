package com.yhyt.health.dao;

import com.yhyt.health.model.DeptDisease;

public interface DeptDiseaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(DeptDisease record);

    int insertSelective(DeptDisease record);

    DeptDisease selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeptDisease record);

    int updateByPrimaryKey(DeptDisease record);
}