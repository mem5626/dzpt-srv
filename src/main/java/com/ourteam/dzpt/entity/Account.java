package com.ourteam.dzpt.entity;

import javax.validation.constraints.NotBlank;

public class Account {

    public interface Default{}

    @NotBlank(message = "用户id不能为空",groups = {Account.Default.class})
    private int userId;

    @NotBlank(message = "密码不能为空",groups = {Account.Default.class})
    private String payPassword;

    private long balance;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

}
