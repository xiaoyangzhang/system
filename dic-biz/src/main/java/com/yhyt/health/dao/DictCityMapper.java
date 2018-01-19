package com.yhyt.health.dao;

import com.yhyt.health.model.DictCity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DictCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictCity record);

    int insertSelective(DictCity record);

    DictCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DictCity record);

    int updateByPrimaryKey(DictCity record);

    List<DictCity> getCitiesByLevel(byte level);

	List<DictCity> findDictCityList(Map<String, Object> params);
	
	List<DictCity> findDictCityListforsearch(Map<String, Object> params);

}