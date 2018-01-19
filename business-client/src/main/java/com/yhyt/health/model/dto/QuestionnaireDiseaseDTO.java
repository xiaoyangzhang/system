package com.yhyt.health.model.dto;

import java.io.Serializable;

public class QuestionnaireDiseaseDTO implements Serializable{

    private static final long serialVersionUID = 8410348303262334265L;
    private Long id;
    private String name;
    private Long dictDiseaseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDictDiseaseId() {
        return dictDiseaseId;
    }

    public void setDictDiseaseId(Long dictDiseaseId) {
        this.dictDiseaseId = dictDiseaseId;
    }
}
