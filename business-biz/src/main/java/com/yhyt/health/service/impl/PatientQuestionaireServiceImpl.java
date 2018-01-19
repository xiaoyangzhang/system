package com.yhyt.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhyt.health.dao.QuestionnaireItemMapper;
import com.yhyt.health.dao.QuestionnaireResultMapper;
import com.yhyt.health.model.QuestionnaireItem;
import com.yhyt.health.model.QuestionnaireResult;
import com.yhyt.health.service.PatientQuestionaireService;

public class PatientQuestionaireServiceImpl implements PatientQuestionaireService {

	
	
    @Autowired
    private QuestionnaireResultMapper questionnaireResultMapper;
	
	
	@Override
	public int addQuestionnaireResult(QuestionnaireResult questionnaireResult) {
		// TODO Auto-generated method stub
		questionnaireResultMapper.insertSelective(questionnaireResult);
		return 0;
	}

	@Override
	public List getAllItem(QuestionnaireItem QuestionnaireItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
