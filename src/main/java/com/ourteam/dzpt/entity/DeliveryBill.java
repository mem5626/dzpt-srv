package com.ourteam.dzpt.entity;

public class DeliveryBill {

  private int id;
  private String goodsName;
  private String deliveryWay;
  private String address;
  private int tradeBillId;
  private int status;
  private Boolean isReceive;
  private Boolean isDeliver;
  private Boolean isReturn;
  private Boolean isAgree;
  private Boolean isConfirm;
  private String createDate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public String getDeliveryWay() {
    return deliveryWay;
  }

  public void setDeliveryWay(String deliveryWay) {
    this.deliveryWay = deliveryWay;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public int getTradeBillId() {
    return tradeBillId;
  }

  public void setTradeBillId(int tradeBillId) {
    this.tradeBillId = tradeBillId;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Boolean getIsReceive() {
    return isReceive;
  }

  public void setIsReceive(Boolean isReceive) {
    this.isReceive = isReceive;
  }

  public Boolean getIsDeliver() {
    return isDeliver;
  }

  public void setIsDeliver(Boolean isDeliver) {
    this.isDeliver = isDeliver;
  }

  public Boolean getIsReturn() {
    return isReturn;
  }

  public void setIsReturn(Boolean isReturn) {
    this.isReturn = isReturn;
  }

  public Boolean getIsAgree() {
    return isAgree;
  }

  public void setIsAgree(Boolean isAgree) {
    this.isAgree = isAgree;
  }

  public Boolean getIsConfirm() {
    return isConfirm;
  }

  public void setIsConfirm(Boolean isConfirm) {
    this.isConfirm = isConfirm;
  }
}
