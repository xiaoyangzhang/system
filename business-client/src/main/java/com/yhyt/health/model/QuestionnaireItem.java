package com.yhyt.health.model;

import java.io.Serializable;
import java.util.Date;

public class QuestionnaireItem extends Persistable implements Serializable {
    private static final long serialVersionUID = -5012460205970655893L;
//    private Long id;

    private Long questionnaireId;

    private Byte type;

    private String title;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String answerE;

    private String answerF;

    private String minDescription;

    private Integer ratioScope;

    private String maxDescription;

    private Byte isNecessary;

    private Date updateTime;

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA == null ? null : answerA.trim();
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB == null ? null : answerB.trim();
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC == null ? null : answerC.trim();
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD == null ? null : answerD.trim();
    }

    public String getAnswerE() {
        return answerE;
    }

    public void setAnswerE(String answerE) {
        this.answerE = answerE == null ? null : answerE.trim();
    }

    public String getAnswerF() {
        return answerF;
    }

    public void setAnswerF(String answerF) {
        this.answerF = answerF == null ? null : answerF.trim();
    }

    public String getMinDescription() {
        return minDescription;
    }

    public void setMinDescription(String minDescription) {
        this.minDescription = minDescription == null ? null : minDescription.trim();
    }

    public Integer getRatioScope() {
        return ratioScope;
    }

    public void setRatioScope(Integer ratioScope) {
        this.ratioScope = ratioScope;
    }

    public String getMaxDescription() {
        return maxDescription;
    }

    public void setMaxDescription(String maxDescription) {
        this.maxDescription = maxDescription == null ? null : maxDescription.trim();
    }

    public Byte getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(Byte isNecessary) {
        this.isNecessary = isNecessary;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}