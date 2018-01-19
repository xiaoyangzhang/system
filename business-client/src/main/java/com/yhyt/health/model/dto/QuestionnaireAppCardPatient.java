package com.yhyt.health.model.dto;

import com.yhyt.health.model.QuestionnaireItem;
import com.yhyt.health.model.QuestionnaireResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gsh
 * @create 2017-11-16 16:48
 **/
public class QuestionnaireAppCardPatient extends QuestionnaireAppVo{

    private String isFilled;
    private Long questionnaireDoctorPatientId;
    private String questionnaireTitle;

    List<QuestionnaireResultAppVo> questionnaireItems = new ArrayList<QuestionnaireResultAppVo>();

    public String getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(String isFilled) {
        this.isFilled = isFilled;
    }

    public Long getQuestionnaireDoctorPatientId() {
        return questionnaireDoctorPatientId;
    }

    public void setQuestionnaireDoctorPatientId(Long questionnaireDoctorPatientId) {
        this.questionnaireDoctorPatientId = questionnaireDoctorPatientId;
    }

    public List<QuestionnaireResultAppVo> getQuestionnaireItems() {
        return questionnaireItems;
    }

    public void setQuestionnaireItems(List<QuestionnaireResultAppVo> questionnaireItems) {
        this.questionnaireItems = questionnaireItems;
    }

    public String getQuestionnaireTitle() {
        return questionnaireTitle;
    }

    public void setQuestionnaireTitle(String questionnaireTitle) {
        this.questionnaireTitle = questionnaireTitle;
    }
}

