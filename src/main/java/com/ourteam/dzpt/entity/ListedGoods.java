package com.ourteam.dzpt.entity;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ListedGoods {

  public interface ListedGoodsInfo {

  }

  public interface ListedGoodsCreate {

  }

  private Integer id;
  @NotBlank(message = "商品名不能为空", groups = ListedGoodsInfo.class)
  private String goodsName;
  @NotNull(message = "数量不能为空", groups = ListedGoodsInfo.class)
  private Integer amount;
  @NotBlank(message = "单位不能为空", groups = ListedGoodsInfo.class)
  private String unit;
  @NotNull(message = "单价不能为空", groups = ListedGoodsInfo.class)
  private Integer price;
  @NotBlank(message = "质量标准不能为空", groups = ListedGoodsInfo.class)
  private String quality;
  @NotNull(message = "挂牌者不能为空", groups = ListedGoodsCreate.class)
  private Integer supplier;
  @NotBlank(message = "商品类型不能为空", groups = ListedGoodsInfo.class)
  private String type;
  @NotNull(message = "来源地不能为空", groups = ListedGoodsInfo.class)
  private String region;
  @NotNull(message = "挂牌类型不能为空", groups = ListedGoodsCreate.class)
  private String hangType;
  @NotNull(message = "请选择是否允许撮合交易", groups = ListedGoodsCreate.class)
  private Boolean ismatch;
  private String image;
  private Integer status;


  private Date createDate;
  private String billNumber;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName == null ? null : goodsName.trim();
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit == null ? null : unit.trim();
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getQuality() {
    return quality;
  }

  public void setQuality(String quality) {
    this.quality = quality == null ? null : quality.trim();
  }

  public Integer getSupplier() {
    return supplier;
  }

  public void setSupplier(Integer supplier) {
    this.supplier = supplier;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(String billNumber) {
    this.billNumber = billNumber == null ? null : billNumber.trim();
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getHangType() {
    return hangType;
  }

  public void setHangType(String hangtype) {
    this.hangType = hangtype;
  }

  public Boolean getIsmatch() {
    return ismatch;
  }

  public void setIsmatch(Boolean ismatch) {
    this.ismatch = ismatch;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
