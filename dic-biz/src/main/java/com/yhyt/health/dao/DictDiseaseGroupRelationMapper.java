package com.yhyt.health.dao;

import java.util.List;
import java.util.Map;

import com.yhyt.health.model.DictDiseaseGroupRelation;

public interface DictDiseaseGroupRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictDiseaseGroupRelation record);

    int insertSelective(DictDiseaseGroupRelation record);

    DictDiseaseGroupRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DictDiseaseGroupRelation record);

    int updateByPrimaryKey(DictDiseaseGroupRelation record);

    /**
     * 判断重复提交
     * @param diseaseGroupId
     * @param diseaseIds
     * @return
     */
	List<DictDiseaseGroupRelation> findRelationList(Long diseaseGroupId, List<Long> diseaseIds);
	  /**
     * 添加疾病
     * @param diseaseGroupId
     * @param diseaseIds
     * @return
     */
	void inertBatch(Long diseaseGroupId, List<Long> diseaseIds);

	
	void delete(Map<String, Object> params);
}