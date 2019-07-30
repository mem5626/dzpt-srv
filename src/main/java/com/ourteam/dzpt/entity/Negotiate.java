package com.ourteam.dzpt.entity;

public class Negotiate {

  private Integer id;
  private Integer status;
  private Integer currentPrice;
  private String createDate;
  private Integer tradeBillId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(Integer currentPrice) {
    this.currentPrice = currentPrice;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public Integer getTradeBillId() {
    return tradeBillId;
  }

  public void setTradeBillId(Integer tradeBillId) {
    this.tradeBillId = tradeBillId;
  }
}
