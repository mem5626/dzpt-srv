package com.ourteam.dzpt.entity;

public enum ExceptionMsg {
  Success("1", "操作执行成功"),
  Error("-1", "系统未知异常"),
  ParameterError("E0001", "参数错误"),
  UserNameOccupied("E0002", "用户名已存在"),
  UserNotExist("E0003", "用户不存在"),
  NotLogin("E0004", "未登录"),
  NotAllow("E0005", "没有权限"),
  OrderExist("E0006", "订单已存在"),
  AgreementNotExit("E0007","合同信息不存在"),
  AgreementExit("E0008","合同信息已存在"),
  AgreementNotSigned("E0009","合同未签名"),
  AgreementHasBeenSigned("E0010","合同已签名"),
  InNegotiate("E0011","议价中"),
  ListedGoodsNotExist("E0012","挂牌商品不存在");

  private String code;
  private String msg;

  ExceptionMsg(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static ExceptionMsg getByCode(String code) {
    for (ExceptionMsg msg : ExceptionMsg.values()) {
      if (msg.getCode().equals(code)) {
        return msg;
      }
    }
    return Error;
  }

  public String getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
