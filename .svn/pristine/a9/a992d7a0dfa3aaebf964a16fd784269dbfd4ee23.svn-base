package com.yhyt.health.dao;

import com.yhyt.health.model.SysUpgrade;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysUpgradeMapper extends BaseDao<SysUpgrade> {
    int deleteByPrimaryKey(Long id);

    int insert(SysUpgrade record);

    int insertSelective(SysUpgrade record);

    SysUpgrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUpgrade record);

    int updateByPrimaryKey(SysUpgrade record);
    List<SysUpgrade> selectUpGradeListPage(Map<String,Object> params);

}