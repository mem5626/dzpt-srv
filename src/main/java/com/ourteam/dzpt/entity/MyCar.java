package com.ourteam.dzpt.entity;

import java.util.Date;

public class MyCar {
    private int listedGoodsId;
    private Date createDate;

    public int getListedGoodsId() {
        return listedGoodsId;
    }

    public void setListedGoodsId(int listedGoodsId) {
        this.listedGoodsId = listedGoodsId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
