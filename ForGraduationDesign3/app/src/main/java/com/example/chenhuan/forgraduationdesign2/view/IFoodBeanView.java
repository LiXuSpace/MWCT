package com.example.chenhuan.forgraduationdesign2.view;

import com.example.chenhuan.forgraduationdesign2.model.bean.DrinksBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.FoodBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;

import java.util.List;

/**
 * Created by lixu on 2017/4/17.
 */

public interface IFoodBeanView {
    public void show(List<FoodBean> foodBeanList);
    public void show2(List<DrinksBean> drinksBeanList);
    public void show3(List<OrderBean> orderBeanList);

    public void error(String e);
}
