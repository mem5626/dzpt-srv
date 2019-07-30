package com.ourteam.dzpt.entity;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class MyCar {

  private Integer id;

  @NotNull(message = "用户ID不能为空")
  private Integer userId;

  @NotNull(message = "商品不能为空")
  private Integer listedGoodsId;

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

  public Integer getListedGoodsId() {
    return listedGoodsId;
  }

  public void setListedGoodsId(Integer listedGoodsId) {
    this.listedGoodsId = listedGoodsId;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }


}
