package com.example.chenhuan.forgraduationdesign2.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lixu on 2017/5/18.
 */

public class CollectionBean extends BmobObject {
    private String orderName;
    private double orderPrice;
    private String imgUrl;

    @Override
    public String toString() {
        return "CollectionBean{" +
                "orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
