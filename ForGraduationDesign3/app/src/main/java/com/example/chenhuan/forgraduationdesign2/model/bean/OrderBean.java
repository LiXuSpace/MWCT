package com.example.chenhuan.forgraduationdesign2.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lixu on 2017/5/17.
 */

public class OrderBean extends BmobObject {
    private String orderName;
    private double orderPrice;
    private String imgUrl;
    private String username;
    private String address;
    private String phone;

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", imgUrl='" + imgUrl + '\'' +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
