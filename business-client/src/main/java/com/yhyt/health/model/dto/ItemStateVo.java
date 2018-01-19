package com.yhyt.health.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author gsh
 * @create 2018-01-10 10:45
 **/
public class ItemStateVo {

    private String name;

    private Date time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm",timezone = "GMT+8")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ItemStateVo(String name, Date time) {
        this.name = name;
        this.time = time;
    }
}
