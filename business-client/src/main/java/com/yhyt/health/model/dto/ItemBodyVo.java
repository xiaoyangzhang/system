package com.yhyt.health.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gsh
 * @create 2018-01-02 15:39
 **/
public class ItemBodyVo {

    private Long noConfirmedCount;

    private Long dealingCount;

    private Long confirmedCount;

    private List<ItemListVo> tasks = new ArrayList<ItemListVo>();

    public Long getNoConfirmedCount() {
        return noConfirmedCount;
    }

    public void setNoConfirmedCount(Long noConfirmedCount) {
        this.noConfirmedCount = noConfirmedCount;
    }

    public Long getDealingCount() {
        return dealingCount;
    }

    public void setDealingCount(Long dealingCount) {
        this.dealingCount = dealingCount;
    }

    public Long getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(Long confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public List<ItemListVo> getTasks() {
        return tasks;
    }

    public void setTasks(List<ItemListVo> tasks) {
        this.tasks = tasks;
    }
}
