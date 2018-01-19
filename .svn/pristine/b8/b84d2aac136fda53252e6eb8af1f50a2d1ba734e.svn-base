package com.yhyt.health.service.impl;

import com.yhyt.health.HealthConstants;
import com.yhyt.health.dao.DictCityMapper;
import com.yhyt.health.model.DictCity;
import com.yhyt.health.service.DictCityService;
import com.yhyt.health.util.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictCityServiceImpl implements DictCityService {

	@Autowired
	private DictCityMapper cityMapper;
	
	@Override
	public List<DictCity> findCityList(DictCity city) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(city!=null){
			if(city.getParentId()!=null){
				params.put("parentId", city.getParentId());
			}
			if(StringUtils.isNotBlank(city.getName())){
				params.put("name", city.getName());
			}
			if(StringUtils.isNotBlank(city.getCityCode())){
				params.put("cityCode", city.getCityCode());
			}
			if(city.getLevel()!=null){
				params.put("level", city.getLevel());
			}
			if(city.getIsMainCity()!=null){
				params.put("isMainCity", city.getIsMainCity());
				params.put("level", (byte)2);
			}
		}
		return this.cityMapper.findDictCityList(params);
	}
	
	
	
	@Override
	public List<DictCity> findCityListforsearch(DictCity city) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(city!=null){
			if(city.getParentId()!=null){
				params.put("parentId", city.getParentId());
			}
			if(StringUtils.isNotBlank(city.getName())){
				params.put("name", city.getName());
			}
			if(StringUtils.isNotBlank(city.getCityCode())){
				params.put("cityCode", city.getCityCode());
			}
			if(city.getLevel()!=null){
				params.put("level", city.getLevel());
			}
			if(city.getIsMainCity()!=null){
				params.put("isMainCity", city.getIsMainCity());
			}
		}
		return this.cityMapper.findDictCityListforsearch(params);
	}

	@Override
	public DictCity editDictCity(DictCity city) {
		if(city!=null){
			DictCity dictCity = null;
			if(city.getId()!=null){
				dictCity = this.cityMapper.selectByPrimaryKey(city.getId());
			}else{
				dictCity = new DictCity();
				dictCity.setCreateTime(new Date());
			}
			BeanUtils.copyProperties(city, dictCity);
			if(city.getId()==null){
				this.cityMapper.insertSelective(dictCity);
				city.setId(dictCity.getId());
			}else{
				this.cityMapper.updateByPrimaryKeySelective(dictCity);
			}
		}
		return city;
	}

	@Override
	public void deleteDictCity(DictCity city) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentId", city.getId());
		List<DictCity> children = this.cityMapper.findDictCityList(params);
		if(children!=null&&children.size()>0){
			throw new BusinessException(HealthConstants.EXCEPTION_PARAMERROR,"请优先删除下级城市");
		}
		this.cityMapper.deleteByPrimaryKey(city.getId());
	}

}
