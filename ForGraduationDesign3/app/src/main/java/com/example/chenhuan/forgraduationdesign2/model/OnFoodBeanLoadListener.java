package com.example.chenhuan.forgraduationdesign2.model;

import com.example.chenhuan.forgraduationdesign2.model.bean.FoodBean;

import java.util.List;

/**
 * Created by lixu on 2017/4/13.
 */

public interface OnFoodBeanLoadListener {
    public void success(List<FoodBean> foodBeanList);
    public void error(String e);
}
