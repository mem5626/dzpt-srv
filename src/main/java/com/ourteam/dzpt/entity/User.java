package com.ourteam.dzpt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class User {

    public interface Default{}
    public interface Info{}

    private int id;
    @NotBlank(message="用户名不能为空",groups = {Default.class})
    private String userName;

    @NotBlank(message = "密码不能为空",groups = {Default.class})
    private String password;

    @NotBlank(message = "电话号码不能为空",groups = {Info.class})
    private String phone;

    @NotBlank(message = "邮箱不能为空",groups = {Info.class})
    private String email;

    @NotBlank(message = "公司名称不能为空",groups = {Info.class})
    private String companyName;

    @NotBlank(message = "地址不能不空",groups = {Info.class})
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    private int admin=0;
    private int ifBan=0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIfBan() {
        return ifBan;
    }

    public void setIfBan(int ifBan) {
        this.ifBan = ifBan;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }


}
