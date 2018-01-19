package com.yhyt.health.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SysOperatorLog {
    private Long id;

    private String content;

    private Long operatorId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}