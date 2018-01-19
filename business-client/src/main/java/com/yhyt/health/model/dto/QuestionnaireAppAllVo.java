package com.yhyt.health.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gsh
 * @create 2017-11-16 16:11
 **/
public class QuestionnaireAppAllVo {

    private Long departmentId;
    private String departmentName;
    List<QuestionnaireAppVo> questionnaires = new ArrayList<QuestionnaireAppVo>();

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<QuestionnaireAppVo> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<QuestionnaireAppVo> questionnaires) {
        this.questionnaires = questionnaires;
    }
}
