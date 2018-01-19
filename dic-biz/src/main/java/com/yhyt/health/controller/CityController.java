package com.yhyt.health.controller;

import com.yhyt.health.dao.DictCityMapper;
import com.yhyt.health.model.DictCity;
import com.yhyt.health.service.DictCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@Api(value="",description ="获取城市列表")
@RequestMapping("/city")
@RestController
public class CityController {

	@Autowired
	private DictCityService cityService;

	@Autowired
	private DictCityMapper cityMapper;
	
	@ApiOperation(value="获取城市列表", notes="系统")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "parentId", value = "上级城市id",paramType="query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "name", value = "城市名称", paramType="query", required = false, dataType = "String"),
        @ApiImplicitParam(name="level",value="城市级别",paramType="query", required = false, dataType = "String"),
    })
	@GetMapping("/")
	public List<DictCity> findCityList(DictCity city){
		if ((city.getParentId() != null && -1==city.getParentId())){
			city.setIsMainCity((byte)1);
			city.setParentId(null);
		}
		List<DictCity> cityList = new ArrayList<>();
		if (null!=city.getLevel() && (byte)1==city.getLevel()) {
			DictCity dictCity = new DictCity();
			dictCity.setName("主要城市");
			dictCity.setParentId(-1L);
			dictCity.setId(-1L);
			dictCity.setLevel((byte)1);
			cityList.add(dictCity);
		}
		List<DictCity> cityList1 = cityService.findCityList(city);
		cityList.addAll(cityList1);
		if ((city.getParentId() != null && -1==city.getParentId())){
			cityList.remove(0);
		}
//		city.setIsMainCity((byte)1);
//		List<DictCity> mainCities = cityService.findCityList(city);
		return cityList;
	}
	
	@ApiOperation(value="获取城市列表(搜索专用)", notes="系统")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "parentId", value = "上级城市id",paramType="query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "name", value = "城市名称", paramType="query", required = false, dataType = "String"),
        @ApiImplicitParam(name="level",value="城市级别",paramType="query", required = false, dataType = "String"),
    })
	@GetMapping("/forsearch")
	public List<DictCity> findCityListforsearch(DictCity city){
		if ((city.getParentId() != null && -1==city.getParentId())){
			city.setIsMainCity((byte)1);
			city.setParentId(null);
		}
		List<DictCity> cityList = new ArrayList<>();
		if (null!=city.getLevel() && (byte)1==city.getLevel()) {
			DictCity dictCity = new DictCity();
			dictCity.setName("主要城市");
			dictCity.setParentId(-1L);
			dictCity.setId(-1L);
			dictCity.setLevel((byte)1);
			cityList.add(dictCity);
		}
		List<DictCity> cityList1 = cityService.findCityListforsearch(city);
		cityList.addAll(cityList1);
		if ((city.getParentId() != null && -1==city.getParentId())){
			cityList.remove(0);
		}
//		city.setIsMainCity((byte)1);
//		List<DictCity> mainCities = cityService.findCityList(city);
		return cityList;
	}
	
	@ApiIgnore
	@RequestMapping("/edit")
	public DictCity editDictCity(DictCity city){
		if(city.getParentId()!=null){
			DictCity parent = this.cityMapper.selectByPrimaryKey(city.getParentId());
			city.setLevel((byte) (parent.getLevel()+1));
		}
		return this.cityService.editDictCity(city);
	}
	
	@ApiIgnore
	@RequestMapping("/delete/{id}")
	public void deleteDictCity(DictCity city){
		this.cityService.deleteDictCity(city);
	}
}
