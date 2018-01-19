package com.yhyt.health.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderDetail extends Persistable{

    private String orderNo;

    private Long patientId;

    private Long oderId;

    private Integer consumeAmount = 30;

    private Byte type;

    private Date createTime;

    private Long departId;
    
    private String isGivenForFree;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getOderId() {
        return oderId;
    }

    public void setOderId(Long oderId) {
        this.oderId = oderId;
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public OrderDetail(String orderNo, Long patientId, Long oderId) {
        this.orderNo = orderNo;
        this.patientId = patientId;
        this.oderId = oderId;
        this.createTime = new Date();
    }

    public OrderDetail() {
    }

	public String getIsGivenForFree() {
		return isGivenForFree;
	}

	public void setIsGivenForFree(String isGivenForFree) {
		this.isGivenForFree = isGivenForFree;
	}
}