package com.yhyt.health.model;

import java.io.Serializable;
import java.util.Date;

public class UpgradeQuery implements Serializable{

    private static final long serialVersionUID = -6037361639883237684L;
    private String appType;
    private Byte clientType;
    private Date upgradeStartTime;
    private Date upgradeEndTime;
    private Integer pageIndex;
    private Integer pageSize;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Byte getClientType() {
        return clientType;
    }

    public void setClientType(Byte clientType) {
        this.clientType = clientType;
    }

    public Date getUpgradeStartTime() {
        return upgradeStartTime;
    }

    public void setUpgradeStartTime(Date upgradeStartTime) {
        this.upgradeStartTime = upgradeStartTime;
    }

    public Date getUpgradeEndTime() {
        return upgradeEndTime;
    }

    public void setUpgradeEndTime(Date upgradeEndTime) {
        this.upgradeEndTime = upgradeEndTime;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
