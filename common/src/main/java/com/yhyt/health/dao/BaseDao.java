package com.yhyt.health.dao;

import java.util.List;
import java.util.Map;

import com.yhyt.health.model.Persistable;

public interface BaseDao<T extends Persistable> {
	
	int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
    
    List<T> findPersistableList(Map<String,Object> params);
}
