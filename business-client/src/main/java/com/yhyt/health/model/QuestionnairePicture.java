package com.yhyt.health.model;

import java.io.Serializable;
import java.util.Date;

public class QuestionnairePicture extends Persistable implements Serializable {
    private static final long serialVersionUID = -6877469725979812L;
//    private Long id;

    private Long questionnaireItemId;

    private String url;

    private Date createTime;

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getQuestionnaireItemId() {
        return questionnaireItemId;
    }

    public void setQuestionnaireItemId(Long questionnaireItemId) {
        this.questionnaireItemId = questionnaireItemId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}