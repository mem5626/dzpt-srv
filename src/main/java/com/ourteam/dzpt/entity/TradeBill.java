package com.ourteam.dzpt.entity;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class TradeBill {

  private Integer id;

  @NotNull(message = "买方不能不空")
  private Integer buyer;
  @NotNull(message = "卖方不能为空")
  private Integer seller;
  @NotNull(message = "对应挂牌不不能为空")
  private Integer listedGoodsId;
  private Integer status;
  private Date createDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getBuyer() {
    return buyer;
  }

  public void setBuyer(Integer buyer) {
    this.buyer = buyer;
  }

  public Integer getSeller() {
    return seller;
  }

  public void setSeller(Integer seller) {
    this.seller = seller;
  }

  public Integer getListedGoodsId() {
    return listedGoodsId;
  }

  public void setListedGoodsId(Integer listedGoodsId) {
    this.listedGoodsId = listedGoodsId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
