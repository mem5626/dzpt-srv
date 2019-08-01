package com.ourteam.dzpt.entity;

import java.util.Date;

public class Negotiate {

  private Integer id;
  private Integer status;
  private Integer currentPrice;
  private Date createDate;
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

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Integer getTradeBillId() {
    return tradeBillId;
  }

  public void setTradeBillId(Integer tradeBillId) {
    this.tradeBillId = tradeBillId;
  }
}
