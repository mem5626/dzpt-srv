package com.ourteam.dzpt.exception;

import com.ourteam.dzpt.entity.ExceptionMsg;

public class GlobalException extends RuntimeException {
    private String code;

    public GlobalException(ExceptionMsg exceptionMsg) {
        super(exceptionMsg.getMsg());
        this.code = exceptionMsg.getCode();
    }

    public GlobalException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
