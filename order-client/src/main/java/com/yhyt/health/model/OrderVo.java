package com.yhyt.health.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by localadmin on 17/9/1.
 */
public class OrderVo implements Serializable {
    private long patientId;
    private long goodsId;
    private int goodsNum = 1;
    private boolean isReceipt;
    //    private int payType;//1-支付宝；2-微信
    private Date createTime;

    /**
     * 商品 id
     */
    private Long itemId;
    /**
     * 商品类型1：服务卡2：服务包
     */

    private Byte itemType;
    private String itemName;
    private String hospital;
    private String department;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Byte getItemType() {
        return itemType;
    }

    public void setItemType(Byte itemType) {
        this.itemType = itemType;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public boolean isReceipt() {
        return isReceipt;
    }

    public void setReceipt(boolean receipt) {
        isReceipt = receipt;
    }

//    public int getPayType() {
//        return payType;
//    }
//
//    public void setPayType(int payType) {
//        this.payType = payType;
//    }
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public OrderVo() {
        this.createTime = new Date();
    }

    public OrderVo(long patientId, long goodsId, int goodsNo, boolean isReceipt) {
        this();
        this.patientId = patientId;
        this.goodsId = goodsId;
        this.goodsNum = goodsNo;
        this.isReceipt = isReceipt;
    }
}
