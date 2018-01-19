package com.yhyt.health.controller;

import com.yhyt.health.model.DictDepartment;
import com.yhyt.health.model.DictDisease;
import com.yhyt.health.model.DictDiseaseGroup;
import com.yhyt.health.model.DictDiseaseGroupRelation;
import com.yhyt.health.service.DictDiseaseService;
import com.yhyt.health.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@ApiIgnore
public class DiseaseController {
	
	@Autowired
	private DictDiseaseService diseaseService;
	
	
	@GetMapping(value ="/disease")
	public List<DictDisease> findDiseases(DictDisease disease, Long diseaseGroupId) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(disease!=null&&StringUtils.isNotBlank(disease.getName())){
			params.put("name", disease.getName());
		}
		if(disease!=null&&StringUtils.isNotBlank(disease.getCode())){
			params.put("code", disease.getCode());
		}
		if(diseaseGroupId!=null){
			params.put("diseaseGroupId", diseaseGroupId);
		}
		return this.diseaseService.findDiseases(params);
	}
	
	@GetMapping(value ="/disease/{id}")
	public DictDisease findDiseases(DictDisease disease) {
		return this.diseaseService.findPersistable(disease.getId());
	}
	
	@GetMapping(value ="/disease/unique/")
	public DictDisease findDiseaseByName(DictDisease disease) {
		return this.diseaseService.findDiseaseUnique(disease);
	}
	
	@RequestMapping(value ="/disease/page")
	public Page<DictDisease> findDiseases(@RequestParam("pageIndex")Integer pageNo,Integer pageSize, DictDisease disease) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(disease!=null&&StringUtils.isNotBlank(disease.getName())){
			params.put("name", disease.getName());
		}
		Page<DictDisease> page = new Page<DictDisease>();
		if(pageNo==null){
			pageNo = 1;
		}
		page.setPageNo(pageNo);
		if(pageSize != null && pageSize != 0){
			page.setPageSize(pageSize);
		}
		if(StringUtils.isNotBlank(disease.getCode())){
			params.put("code", disease.getCode());
		}
		params.put("page", page);
		page.setResult(this.diseaseService.findDiseases(params));
		return page;
	}
	
	@RequestMapping(value ="/diseaseGroupRelation/disease/page/{dictDiseaseGroupId}")
	public Page<DictDisease> findDiseasesforRalation(@PathVariable("dictDiseaseGroupId")Long dictDiseaseGroupId,@RequestParam("pageIndex")Integer pageNo,Integer pageSize, DictDisease disease){
		Map<String,Object> params = new HashMap<String,Object>();
		if(disease!=null&&StringUtils.isNotBlank(disease.getName())){
			params.put("name", disease.getName());
		}
		Page<DictDisease> page = new Page<DictDisease>();
		if(pageNo==null){
			pageNo = 1;
		}
		page.setPageNo(pageNo);
		if(pageSize != null && pageSize != 0){
			page.setPageSize(pageSize);
		}
		if(dictDiseaseGroupId!=null){
			params.put("existDiseaseGroupId", dictDiseaseGroupId);
		}
		if(StringUtils.isNotBlank(disease.getCode())){
			params.put("code", disease.getCode());
		}
		params.put("page", page);
		page.setResult(this.diseaseService.findDiseases(params));
		return page;
	}
	
	@RequestMapping(value ="/disease/edit")
	public DictDisease editDisease(DictDisease disease ){
		return this.diseaseService.edit(disease);
	}
	
	
	@RequestMapping(value ="/disease/delete/{id}")
	public void deleteDiseases(DictDisease disease) {
		this.diseaseService.delete(disease.getId());
	}
	
	@RequestMapping(value ="/diseaseGroup")
	public List<DictDiseaseGroup> findDictDiseaseGroup(DictDiseaseGroup diseaseGroup){
		Map<String,Object> params = new HashMap<String,Object>();
		if(diseaseGroup!=null&&StringUtils.isNotBlank(diseaseGroup.getName())){
			params.put("name", diseaseGroup.getName());
		}
		if(diseaseGroup!=null&&StringUtils.isNotBlank(diseaseGroup.getDesc())){
			params.put("desc", diseaseGroup.getDesc());
		}
		return this.diseaseService.findDiseaseGroup(params);
	}

	@RequestMapping(value ="/diseaseGroup/{id}")
	public DictDiseaseGroup findDictDiseaseGroupById(DictDiseaseGroup diseaseGroup){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", diseaseGroup.getId());
		List<DictDiseaseGroup> list = this.diseaseService.findDiseaseGroup(params);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@RequestMapping(value ="/diseaseGroup/edit")
	public DictDiseaseGroup editDictDiseaseGroup(DictDiseaseGroup diseaseGroup){
		return this.diseaseService.editDiseaseGroup(diseaseGroup);
	}
	
	@RequestMapping(value ="/diseaseGroup/delete/{id}")
	public void deleteDictDiseaseGroup(DictDiseaseGroup diseaseGroup){
		this.diseaseService.deleteDiseaseGroup(diseaseGroup);
	}
	
	@RequestMapping(value ="/diseaseGroupRelation/add")
	public void addDiseaseGroupRelation(Long diseaseGroupId,Long[] diseaseIds){
		List<Long> diseases = Arrays.asList(diseaseIds);
		this.diseaseService.addDiseaseGroupRelation(diseaseGroupId,diseases);
	}
	
	
	@RequestMapping(value ="/diseaseGroupRelation/delete")
	public void deleteDiseaseGroupRelation(DictDiseaseGroupRelation relation){
		this.diseaseService.deleteDiseaseGroupRelation(relation);
	}
	
	@RequestMapping(value ="/dictDepartment")
	public List<DictDepartment> findDictDepartmentList(Integer level, DictDepartment dictDepartment, HttpServletResponse response){
		return this.diseaseService.findDictDepartment(dictDepartment,level);
	}
	
	@RequestMapping(value ="/dictDepartment/{id}")
	public DictDepartment findDictDepartment(DictDepartment dictDepartment ){
		return this.diseaseService.findDictDepartment(dictDepartment);
	}
	
	@RequestMapping(value ="/dictDepartment/edit")
	public DictDepartment editDictDepartment(DictDepartment dictDepartment ){
		return this.diseaseService.editDictDepartment(dictDepartment);
	}
	
	@RequestMapping(value ="/dictDepartment/delete/{id}")
	public void deleteDictDepartment(DictDepartment dictDepartment ){
		this.diseaseService.deleteDictDepartment(dictDepartment.getId());
	}

	@RequestMapping("/category/dept/disease/count/{cateStr}")
	public int countDeptCategoryDisease(@PathVariable("cateStr") String str){

		return 0;
	}
	
}
