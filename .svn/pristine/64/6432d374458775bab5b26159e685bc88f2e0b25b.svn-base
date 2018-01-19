package com.yhyt.health.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.yhyt.health.HealthConstants;
import com.yhyt.health.dao.BaseDao;
import com.yhyt.health.dao.DeptDiseaseMapper;
import com.yhyt.health.dao.DictDepartmentMapper;
import com.yhyt.health.dao.DictDiseaseGroupMapper;
import com.yhyt.health.dao.DictDiseaseGroupRelationMapper;
import com.yhyt.health.dao.DictDiseaseMapper;
import com.yhyt.health.model.DictDepartment;
import com.yhyt.health.model.DictDisease;
import com.yhyt.health.model.DictDiseaseGroup;
import com.yhyt.health.model.DictDiseaseGroupRelation;
import com.yhyt.health.util.BusinessException;

@Service
public class DictDiseaseServiceImpl extends BaseServiceImpl<DictDisease> implements com.yhyt.health.service.DictDiseaseService{

	@Autowired
	private DictDiseaseMapper diseaseMapper;
	
	@Autowired
	private DictDiseaseGroupMapper diseaseGroupMapper;
	
	@Autowired
	private DictDiseaseGroupRelationMapper diseaseGroupRelationMapper;
	
	@Autowired
	private DictDepartmentMapper departmentMapper;
	
	@Override
	public List<DictDisease> findDiseases(Map<String,Object> params) {
		return this.diseaseMapper.findPersistableList(params);
	}

	@Override
	public List<DictDiseaseGroup> findDiseaseGroup(Map<String, Object> params) {
		return this.diseaseGroupMapper.findDiseaseGroupMapper(params);
	}

	@Override
	public DictDiseaseGroup editDiseaseGroup(DictDiseaseGroup diseaseGroup) {
		DictDiseaseGroup group  = null;
		if(diseaseGroup.getId()!=null){
			 group = this.diseaseGroupMapper.selectByPrimaryKey(diseaseGroup.getId());
			 if(group == null){
				 throw new BusinessException(HealthConstants.EXCEPTION_PARAMERROR,"未找到对应的疾病分组");
			 }
		}else{
			group = new DictDiseaseGroup();
			group.setCreateTime(new Date());
		}
		group.setDesc(diseaseGroup.getDesc());
		group.setName(diseaseGroup.getName());
		if(group.getId()==null){
			this.diseaseGroupMapper.insertSelective(group);
		}else{
			this.diseaseGroupMapper.updateByPrimaryKey(group);
		}
		return group;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public void addDiseaseGroupRelation(Long diseaseGroupId, List<Long> diseaseIds) {
		List<DictDiseaseGroupRelation> list = this.diseaseGroupRelationMapper.findRelationList(diseaseGroupId,diseaseIds);
		for (DictDiseaseGroupRelation dictDiseaseGroupRelation : list) {
			for (Long diseaseId : diseaseIds) {
				if(diseaseId == dictDiseaseGroupRelation.getDictDiseaseId()){
					diseaseIds.remove(diseaseId);
					break;
				}
			}
		}
		if(diseaseIds!=null&&diseaseIds.size()>0){
			this.diseaseGroupRelationMapper.inertBatch(diseaseGroupId,diseaseIds);
		}
	}

	@Override
	public List<DictDepartment> findDictDepartment(DictDepartment dictDepartment, Integer level) {
		Map<String,Object> params = new HashMap<String,Object>();
//		if(level!=null&&level>0){
//			params.put("level", level);
//		}
		params.put("parentCode", dictDepartment.getParentCode());
		return this.departmentMapper.findDictDepartmentList(params);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public DictDepartment editDictDepartment(DictDepartment dictDepartment) {
		if(dictDepartment!=null){
			DictDepartment department = null;
			if(dictDepartment.getId()!=null){
				department = this.departmentMapper.selectByPrimaryKey(dictDepartment.getId());
				if(department==null||department.getId()==null){
					 throw new BusinessException(HealthConstants.EXCEPTION_PARAMERROR,"未找到对应的科室分类");
				}
			}else{
				department = new DictDepartment();
			}
			department.setChildCode(dictDepartment.getChildCode());
			department.setChildName(dictDepartment.getChildName());
			department.setParentCode(dictDepartment.getParentCode());
			department.setParentName(dictDepartment.getParentName());
			if(department.getId()==null){
				this.departmentMapper.insertSelective(department);
				return department;
			}else{
				this.departmentMapper.updateByPrimaryKeySelective(department);
				return department;
			}
		}
		return null;
	}

	@Override
	public void deleteDictDepartment(Long id) {
		this.departmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public DictDepartment findDictDepartment(DictDepartment dictDepartment) {
		return this.departmentMapper.selectByPrimaryKey(dictDepartment.getId());
	}

	@Override
	public BaseDao<DictDisease> getBaseDao() {
		return this.diseaseMapper;
	}
	/**
	 * 保存前参数检查
	 * @param persistable
	 */
	void deleteBefore(Long id){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("diseaseId", id);
		this.diseaseGroupRelationMapper.delete(params);
	}

	@Transactional
	@Override
	public void deleteDiseaseGroup(DictDiseaseGroup diseaseGroup) {
		this.diseaseGroupMapper.deleteByPrimaryKey(diseaseGroup.getId());
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("diseaseGroupId", diseaseGroup.getId());
		this.diseaseGroupRelationMapper.delete(params);
	}

	@Override
	public void deleteDiseaseGroupRelation(DictDiseaseGroupRelation relation) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("diseaseId", relation.getDictDiseaseId());
		params.put("diseaseGroupId",relation.getDictDiseaseGroupId());
		this.diseaseGroupRelationMapper.delete(params);		
	}
	
	@Override
	void afterFindPersistable(final DictDisease disease){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("diseaseId", disease.getId());
		List<DictDepartment> deptList = this.departmentMapper.findDictDepartmentList(params);
		disease.setDepartmentList(deptList);
	}

	@Override
	public DictDisease findDiseaseUnique(DictDisease disease) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(disease!=null&&StringUtils.isNotBlank(disease.getName())){
			params.put("name", disease.getName());
		}
		List<DictDisease> diseases = this.diseaseMapper.findPersistableList(params);
		if(diseases!=null&&diseases.size()>0){
			DictDisease result = diseases.get(0);
			this.afterFindPersistable(result);
			return result;
		}
		return null;
	}
}
