package com.yhyt.health.model.dto;

import java.util.Date;

/**
 * @author gsh
 * @create 2017-12-07 17:04
 **/
public class QuestionnairePatientVo {
    private Long questionnaireDoctorPatientId;
    private Long questionnaireId;
    private Long departmentId;
    private String departmentLogo;
    private String departmentName;
    private String hospitalName;
    private String title;
    private String subTitle;
    private String isFilled;
    private Date updateTime;

    public Long getQuestionnaireDoctorPatientId() {
        return questionnaireDoctorPatientId;
    }

    public void setQuestionnaireDoctorPatientId(Long questionnaireDoctorPatientId) {
        this.questionnaireDoctorPatientId = questionnaireDoctorPatientId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentLogo() {
        return departmentLogo;
    }

    public void setDepartmentLogo(String departmentLogo) {
        this.departmentLogo = departmentLogo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
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

    public String getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(String isFilled) {
        this.isFilled = isFilled;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
