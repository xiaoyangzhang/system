package com.yhyt.health.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class CustomerServiceDTO implements Serializable {

    private static final long serialVersionUID = -4792006445688867132L;
    private Long id;

    private String taskNo;
    private Long orderId;

    private String name;

    private Byte type;

    private Byte doctorState;

    private Byte taskState;

    private Long serviceDoctorId;

    private Date reviewTime;

    private Date updateTime;

    private Date createTime;

    private String hospital;

    private String patientRealName;
    private String patientUserName;
    private Date payTime;
    private Date refundTime;
    private String operator;

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getDoctorState() {
        return doctorState;
    }

    public void setDoctorState(Byte doctorState) {
        this.doctorState = doctorState;
    }

    public Byte getTaskState() {
        return taskState;
    }

    public void setTaskState(Byte taskState) {
        this.taskState = taskState;
    }

    public Long getServiceDoctorId() {
        return serviceDoctorId;
    }

    public void setServiceDoctorId(Long serviceDoctorId) {
        this.serviceDoctorId = serviceDoctorId;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getPatientRealName() {
        return patientRealName;
    }

    public void setPatientRealName(String patientRealName) {
        this.patientRealName = patientRealName;
    }

    public String getPatientUserName() {
        if (!StringUtils.isEmpty(patientUserName))
            return patientUserName.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        return "";

    }

    public void setPatientUserName(String patientUserName) {
        this.patientUserName = patientUserName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
