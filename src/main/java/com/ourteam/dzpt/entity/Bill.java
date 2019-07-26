package com.ourteam.dzpt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Bill {
    private Integer id;
    private Integer userId;
    private Integer money;
    private Integer balance;
    private Integer drcrflg;
    private String tradeWay;
    private Integer tradeType;
    private Integer orderId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getDrcrflg() {
        return drcrflg;
    }

    public void setDrcrflg(Integer drcrflg) {
        this.drcrflg = drcrflg;
    }

    public String getTradeWay() {
        return tradeWay;
    }

    public void setTradeWay(String tradeWay) {
        this.tradeWay = tradeWay;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }



}
