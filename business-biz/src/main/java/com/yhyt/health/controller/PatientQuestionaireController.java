package com.yhyt.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yhyt.health.model.QuestionnaireResult;
import com.yhyt.health.model.dto.QuestionnaireDTO;
import com.yhyt.health.service.PatientQuestionaireService;
import com.yhyt.health.service.QuestionnaireService;

public class PatientQuestionaireController {
    @Autowired
    private PatientQuestionaireService patientQuestionaireService;
    
    
    @PostMapping("/questionnaireresult/add")
    public int addQuestionnaire(@RequestBody QuestionnaireResult questionnaireResult){
        return patientQuestionaireService.addQuestionnaireResult(questionnaireResult);
    }
    
    
}
