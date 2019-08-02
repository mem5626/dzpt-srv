package com.ourteam.dzpt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Bill {
    private Integer id;
    private Integer userId;
    private long money;
    private long balance;
    private Integer drcrflg;
    private Integer tradeWay;
    private String tradeWayName;
    private Integer tradeType;

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    private Integer tradeId;

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

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Integer getDrcrflg() {
        return drcrflg;
    }

    public void setDrcrflg(Integer drcrflg) {
        this.drcrflg = drcrflg;
    }

    public Integer getTradeWay() {
        return tradeWay;
    }

    public void setTradeWay(Integer tradeWay) {
        this.tradeWay = tradeWay;
    }

    public String getTradeWayName() {
        return tradeWayName;
    }

    public void setTradeWayName(String tradeWayName) {
        this.tradeWayName = tradeWayName;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }







}
