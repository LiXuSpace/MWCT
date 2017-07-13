package com.example.chenhuan.forgraduationdesign2.model;

import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;

import java.util.List;

/**
 * Created by lixu on 2017/4/13.
 */

public interface OnOrderBeanLoadListener {
    public void success(List<OrderBean> orderBeanList);
    public void error(String e);
}
