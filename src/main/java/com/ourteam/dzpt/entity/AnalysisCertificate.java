package com.ourteam.dzpt.entity;

import java.util.Date;

public class AnalysisCertificate {

  private int id;
  private String image;
  private Date createDate;
  private int listedGoodsId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image == null ? null : image.trim();
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public int getListedGoodsId() {
    return listedGoodsId;
  }

  public void setListedGoodsId(int listedGoodsId) {
    this.listedGoodsId = listedGoodsId;
  }
}
