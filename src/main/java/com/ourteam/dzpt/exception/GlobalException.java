package com.ourteam.dzpt.exception;

import com.ourteam.dzpt.entity.ExceptionMsg;

public class GlobalException extends RuntimeException {

  private String code;
  private String detail;

  public GlobalException(ExceptionMsg exceptionMsg) {
    super(exceptionMsg.getMsg());
    this.code = exceptionMsg.getCode();
  }

  public GlobalException(ExceptionMsg exceptionMsg,String detail) {
    super(exceptionMsg.getMsg());
    this.code = exceptionMsg.getCode();
    this.detail = detail;
  }

  public GlobalException(String code, String msg) {
    super(msg);
    this.code = code;
  }

  public GlobalException(String code, String msg, String detail) {
    super(msg);
    this.code = code;
    this.detail = detail;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }
}
