package com.yhyt.health.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author gsh
 * @create 2018-01-02 14:20
 **/
public class ItemListVo {

    private Long id;

    private String patientName;

    private String patientSex;

    private String patientAges;

    private String resideLocation;

    private String itemName;

    private Date payTime;

    private String doctorState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientAges() {
        return patientAges;
    }

    public void setPatientAges(String patientAges) {
        this.patientAges = patientAges;
    }

    public String getResideLocation() {
        return resideLocation;
    }

    public void setResideLocation(String resideLocation) {
        this.resideLocation = resideLocation;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonFormat(pattern = "yyyy年MM月dd HH:mm",timezone = "GMT+8")
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getDoctorState() {
        return doctorState;
    }

    public void setDoctorState(String doctorState) {
        this.doctorState = doctorState;
    }
}
