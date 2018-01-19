package com.yhyt.health.service;

import java.util.List;

import com.yhyt.health.model.QuestionnaireItem;
import com.yhyt.health.model.QuestionnaireResult;

public interface PatientQuestionaireService {

	  public int addQuestionnaireResult(QuestionnaireResult questionnaireResult);
	  
	  public List getAllItem(QuestionnaireItem QuestionnaireItem);
	
}
