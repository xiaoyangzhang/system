package com.yhyt.health.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yhyt.health.model.Questionnaire;
import com.yhyt.health.model.QuestionnaireDept;
import com.yhyt.health.model.QuestionnaireDisease;
import com.yhyt.health.model.QuestionnaireItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionnaireDTO implements Serializable{

    private static final long serialVersionUID = -647919984242708075L;
    private Questionnaire questionnaire;
    private List<QuestionnaireItem> questionnaireItems = new ArrayList<>();
    private List<QuestionnaireDept> questionnaireDepts = new ArrayList<>();
    private List<QuestionnaireDeptDTO> questionnaireDeptDTOS = new ArrayList<>();
    private List<QuestionnaireDisease> questionnaireDiseases = new ArrayList<>();
    private List<QuestionnaireDiseaseDTO> questionnaireDiseaseDTOs = new ArrayList<>();
//    private List<QuestionnairePicture> questionnairePictures = new A=;
    private String deptName;//所属科室
    //以下属性分页使用
    private String title;

    private String subTitle;

    private Long id;

    private Byte state;

    private Date updateTime;
    private String qrCode;//预览

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<QuestionnaireItem> getQuestionnaireItems() {
        return questionnaireItems;
    }

    public void setQuestionnaireItems(List<QuestionnaireItem> questionnaireItems) {
        this.questionnaireItems = questionnaireItems;
    }

    public List<QuestionnaireDept> getQuestionnaireDepts() {
        return questionnaireDepts;
    }

    public void setQuestionnaireDepts(List<QuestionnaireDept> questionnaireDepts) {
        this.questionnaireDepts = questionnaireDepts;
    }

    public List<QuestionnaireDisease> getQuestionnaireDiseases() {
        return questionnaireDiseases;
    }

    public void setQuestionnaireDiseases(List<QuestionnaireDisease> questionnaireDiseases) {
        this.questionnaireDiseases = questionnaireDiseases;
    }

//    public List<QuestionnairePicture> getQuestionnairePictures() {
//        return questionnairePictures;
//    }

//    public void setQuestionnairePictures(List<QuestionnairePicture> questionnairePictures) {
//        this.questionnairePictures = questionnairePictures;
//    }

    public List<QuestionnaireDeptDTO> getQuestionnaireDeptDTOS() {
        return questionnaireDeptDTOS;
    }

    public void setQuestionnaireDeptDTOS(List<QuestionnaireDeptDTO> questionnaireDeptDTOS) {
        this.questionnaireDeptDTOS = questionnaireDeptDTOS;
    }

    public List<QuestionnaireDiseaseDTO> getQuestionnaireDiseaseDTOs() {
        return questionnaireDiseaseDTOs;
    }

    public void setQuestionnaireDiseaseDTOs(List<QuestionnaireDiseaseDTO> questionnaireDiseaseDTOs) {
        this.questionnaireDiseaseDTOs = questionnaireDiseaseDTOs;
    }
}
