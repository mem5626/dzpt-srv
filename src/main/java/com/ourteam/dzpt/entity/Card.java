package com.ourteam.dzpt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Card {

  private int id;
  private int userId;
  private long cardNumber;
  private String bank;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date bindTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public long getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(long cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }


  public Date getBindTime() {
    return bindTime;
  }

  public void setBindTime(Date bindTime) {
    this.bindTime = bindTime;
  }
}
