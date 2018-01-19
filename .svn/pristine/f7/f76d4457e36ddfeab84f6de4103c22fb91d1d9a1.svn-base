package com.yhyt.health.service;

import com.yhyt.health.model.DictDepartment;
import com.yhyt.health.model.DictDisease;
import com.yhyt.health.model.DictDiseaseGroup;
import com.yhyt.health.model.DictDiseaseGroupRelation;

import java.util.List;
import java.util.Map;

public interface DictDiseaseService extends BaseService<DictDisease> {

	// @DataSource("slave")
	public List<DictDisease> findDiseases(Map<String, Object> params);

	public List<DictDiseaseGroup> findDiseaseGroup(Map<String, Object> params);

	public DictDiseaseGroup editDiseaseGroup(DictDiseaseGroup diseaseGroup);

	/**
	 * 疾病分组添加疾病
	 * 
	 * @param diseaseGroupId
	 * @param diseaseIds
	 */
	public void addDiseaseGroupRelation(Long diseaseGroupId, List<Long> diseaseIds);

	/**
	 * 查询科室分类
	 * 
	 * @param dictDepartment
	 * @param level
	 * @return
	 */
	public List<DictDepartment> findDictDepartment(DictDepartment dictDepartment, Integer level);

	public DictDepartment editDictDepartment(DictDepartment dictDepartment);

	public void deleteDictDepartment(Long id);

	public DictDepartment findDictDepartment(DictDepartment dictDepartment);

	public void deleteDiseaseGroup(DictDiseaseGroup diseaseGroup);

	public void deleteDiseaseGroupRelation(DictDiseaseGroupRelation relation);

	public DictDisease findDiseaseUnique(DictDisease disease);
}
