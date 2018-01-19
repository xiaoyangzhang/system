package com.yhyt.health.dao;

import com.yhyt.health.model.QuestionnaireResult;
import com.yhyt.health.model.dto.QuestionnaireAppCardPatient;

import java.util.List;
import java.util.Map;

public interface QuestionnaireResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionnaireResult record);

    int insertSelective(QuestionnaireResult record);

    QuestionnaireResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionnaireResult record);

    int updateByPrimaryKey(QuestionnaireResult record);

    List<QuestionnaireAppCardPatient> getQuestionnairePatient(Map<String,Object> map);
}