package com.example.chenhuan.forgraduationdesign2.model;

import com.example.chenhuan.forgraduationdesign2.model.bean.DrinksBean;

import java.util.List;

/**
 * Created by lixu on 2017/5/16.
 */

public interface OnDrinksBeanLoadListener {
    public void success(List<DrinksBean> drinksBeanList);
    public void error(String e);
}
