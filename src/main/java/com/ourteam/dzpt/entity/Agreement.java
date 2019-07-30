package com.ourteam.dzpt.entity;

public class Agreement {

  private int id;
  private String buyerSign;
  private String sellerSign;
  private int status;
  private int tradeBillId;
  private String createDate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBuyerSign() {
    return buyerSign;
  }

  public void setBuyerSign(String buyerSign) {
    this.buyerSign = buyerSign;
  }

  public String getSellerSign() {
    return sellerSign;
  }

  public void setSellerSign(String sellerSign) {
    this.sellerSign = sellerSign;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getTradeBillId() {
    return tradeBillId;
  }

  public void setTradeBillId(int tradeBillId) {
    this.tradeBillId = tradeBillId;
  }

}
