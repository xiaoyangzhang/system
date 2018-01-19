package com.yhyt.health.service;

import java.util.List;
import java.util.Map;

import com.yhyt.health.model.Persistable;
import com.yhyt.health.util.Page;

public abstract interface BaseService<T extends Persistable> {
	
	public <T> T instaceXml(String url,String xml,Class<T> returnClass);
	
	List<T> findPersistableList(T persistable);
	
	List<T> findPersistableList(Map<String,Object> params);
	
	Page<T> findPersistableList(T persistable,Integer pageNo,Integer pageSize);
	
	Page<T> findPersistableList(T persistable,Integer pageNo);
	
	Page<T> findPersistableList(Map<String,Object> params,Integer pageNo,Integer pageSize);

	T edit(T persistable);
	
	void delete(Long id);
	
	T findPersistable(Long id);
}
