package com.yhyt.health.query;

import com.yhyt.health.page.BasePage;

import java.io.Serializable;

public class DictDieaseQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 5362300528604821942L;
    private Long id;
    private String name;

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
}
