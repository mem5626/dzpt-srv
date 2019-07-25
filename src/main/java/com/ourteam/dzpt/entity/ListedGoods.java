package com.ourteam.dzpt.entity;

import java.util.Date;

public class ListedGoods {
    private Integer id;
    private String goodsName;
    private Integer amount;
    private String unit;
    private Integer price;
    private String quality;
    private Integer supplier;
    private Date createDate;
    private String billNumber;
    private String type;
    private Integer region;
    private Integer hangtype;
    private Boolean ismatch;
    private Integer tradeBillId;

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

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getHangtype() {
        return hangtype;
    }

    public void setHangtype(Integer hangtype) {
        this.hangtype = hangtype;
    }

    public Boolean getIsmatch() {
        return ismatch;
    }

    public void setIsmatch(Boolean ismatch) {
        this.ismatch = ismatch;
    }

    public Integer getTradebillid() {
        return tradeBillId;
    }

    public void setTradeBillId(Integer tradeBillId) {
        this.tradeBillId = tradeBillId;
    }
}
