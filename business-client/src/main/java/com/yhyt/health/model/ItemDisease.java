package com.yhyt.health.model;

import java.util.Date;

public class ItemDisease {
    private Long id;

    private Long itemId;

    private Long dictDiseaseId;

    private Date ctreateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getDictDiseaseId() {
        return dictDiseaseId;
    }

    public void setDictDiseaseId(Long dictDiseaseId) {
        this.dictDiseaseId = dictDiseaseId;
    }

    public Date getCtreateTime() {
        return ctreateTime;
    }

    public void setCtreateTime(Date ctreateTime) {
        this.ctreateTime = ctreateTime;
    }
}