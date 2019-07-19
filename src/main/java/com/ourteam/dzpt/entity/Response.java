package com.ourteam.dzpt.entity;

public class Response {

    private String code = "0";
    private String msg = "操作成功";
    private Object data;

    public Response(){
    }

    public Response(ExceptionMsg exceptionMsg){
        this.code = exceptionMsg.getCode();
        this.msg = exceptionMsg.getMsg();
    }
    public Response(ExceptionMsg exceptionMsg, Object data){
        this.code = exceptionMsg.getCode();
        this.msg = exceptionMsg.getMsg();
        this.data = data;
    }
    public Response(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public Response(String code, String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
