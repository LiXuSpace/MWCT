package com.example.chenhuan.forgraduationdesign2.model;

/**
 * Created by lixu on 2017/5/18.
 */

public interface OnOrderInfoListener {
    public void success(String address,String name,String phoneNum);
    public void error(String e);
}
