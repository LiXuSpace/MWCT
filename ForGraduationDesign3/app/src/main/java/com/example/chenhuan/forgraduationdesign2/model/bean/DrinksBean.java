package com.example.chenhuan.forgraduationdesign2.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lixu on 2017/5/16.
 */

public class DrinksBean extends BmobObject {
    private String drinksName;
    private int monthSaleNum;
    private double price;
    private int praiseNum;
    private String imgUrl;

    public String getDrinksName() {
        return drinksName;
    }

    public void setDrinksName(String drinksName) {
        this.drinksName = drinksName;
    }

    public int getMonthSaleNum() {
        return monthSaleNum;
    }

    public void setMonthSaleNum(int monthSaleNum) {
        this.monthSaleNum = monthSaleNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "DrinksBean{" +
                "drinksName='" + drinksName + '\'' +
                ", monthSaleNum=" + monthSaleNum +
                ", price=" + price +
                ", praiseNum=" + praiseNum +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
