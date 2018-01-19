package com.yhyt.health.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long id;

    private Long patientIdPurchaser;

    private Long patientIdOwner;

    private Long dictServiceCardId;

    /**
     * 商品id
     */
    private Long itemId;
    private String orderNo;

    private Integer remainCount;

    private BigDecimal price;

    private Byte state;

    private Byte isReceipt;

    private Byte payType;

    private Date updateTime;

    private Date createTime;

    private Date payTime;

    /**
     * 商品类型
     */
    private Byte itemType;
    private String refundMsg;
    private Byte refundClient;
    private Long version;

    public Byte getRefundClient() {
        return refundClient;
    }

    public void setRefundClient(Byte refundClient) {
        this.refundClient = refundClient;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientIdPurchaser() {
        return patientIdPurchaser;
    }

    public void setPatientIdPurchaser(Long patientIdPurchaser) {
        this.patientIdPurchaser = patientIdPurchaser;
    }

    public Long getPatientIdOwner() {
        return patientIdOwner;
    }

    public void setPatientIdOwner(Long patientIdOwner) {
        this.patientIdOwner = patientIdOwner;
    }

    public Long getDictServiceCardId() {
        return dictServiceCardId;
    }

    public void setDictServiceCardId(Long dictServiceCardId) {
        this.dictServiceCardId = dictServiceCardId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(Integer remainCount) {
        this.remainCount = remainCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(Byte isReceipt) {
        this.isReceipt = isReceipt;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getRefundMsg() {
        return refundMsg;
    }

    public void setRefundMsg(String refundMsg) {
        this.refundMsg = refundMsg == null ? null : refundMsg.trim();
    }
}