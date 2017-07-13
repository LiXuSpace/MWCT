package com.example.chenhuan.forgraduationdesign2.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lixu on 2017/4/11.
 */

public class FoodBean extends BmobObject{
    private String foodName;
    private int monthSaleNum;
    private double price;
    private int praiseNum;
    private String imgUrl;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
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

    @Override
    public String toString() {
        return "FoodBean{" +
                "foodName='" + foodName + '\'' +
                ", monthSaleNum='" + monthSaleNum + '\'' +
                ", price=" + price +
                ", praiseNum=" + praiseNum +
                '}';
    }
}
