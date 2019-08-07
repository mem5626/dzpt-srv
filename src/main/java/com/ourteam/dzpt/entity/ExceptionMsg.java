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
  AgreementNotExit("E0007", "合同信息不存在"),
  AgreementExit("E0008", "合同信息已存在"),
  AgreementNotSigned("E0009", "合同未签名"),
  AgreementHasBeenSigned("E0010", "合同已签名"),
  InNegotiate("E0011", "议价中"),
  ListedGoodsNotExist("E0012", "挂牌商品不存在"),
  PasswordError("E0013", "密码错误"),
  DeliveryNotExit("E0014", "交收信息不存在"),
  DeliveryExit("E0015", "交收单已存在"),
  UploadFiled("E0016", "上传图片失败"),
  AddMyCarError("E0017","商品已售出"),
  HasBeenBan("E0018","用户已被封禁"),
  RepeatRequest("E0019","重复请求");


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
