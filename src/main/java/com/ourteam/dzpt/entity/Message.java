package com.ourteam.dzpt.entity;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class Message {

  int id;
  @NotNull(message = "发送方不能为空")
  int sender;
  @NotNull(message = "接受方不能为空")
  int receiver;
  @NotNull(message = "类型不能为空")
  String type;
  @NotNull(message = "标题不能为空")
  String title;
  @NotNull(message = "内容不能为空")
  String content;
  Date createDate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSender() {
    return sender;
  }

  public void setSender(int sender) {
    this.sender = sender;
  }

  public int getReceiver() {
    return receiver;
  }

  public void setReceiver(int receiver) {
    this.receiver = receiver;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
