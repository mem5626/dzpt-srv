package com.ourteam.dzpt.entity;

public enum  ExceptionMsg {
    Success("0","操作执行成功"),
    Error("-1","系统未知异常"),
    ParameterError("E0001","参数错误"),
    UserNameOccupied("E0002","用户名已存在"),
    UserNotExist("E0003","用户不存在"),
    NotLogin("E0004","未登录");

    private String code;
    private String msg;

     ExceptionMsg(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
