package com.yhyt.health.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDepartmentDetail {
    private Long id;

    private Long doctorIdFirst;

    private Long oderDetailId;

    private Long orderId;

    private BigDecimal originAmount;

    private BigDecimal incomeAmount;

    private Byte type;

    private Integer incomeRatio;

    private Date createTime;

    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorIdFirst() {
        return doctorIdFirst;
    }

    public void setDoctorIdFirst(Long doctorIdFirst) {
        this.doctorIdFirst = doctorIdFirst;
    }

    public Long getOderDetailId() {
        return oderDetailId;
    }

    public void setOderDetailId(Long oderDetailId) {
        this.oderDetailId = oderDetailId;
    }

    public BigDecimal getOriginAmount() {
        return originAmount;
    }

    public void setOriginAmount(BigDecimal originAmount) {
        this.originAmount = originAmount;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getIncomeRatio() {
        return incomeRatio;
    }

    public void setIncomeRatio(Integer incomeRatio) {
        this.incomeRatio = incomeRatio;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}