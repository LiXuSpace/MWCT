package com.example.chenhuan.forgraduationdesign2.model;

/**
 * Created by lixu on 2017/4/13.
 */

public interface ILoadData {
    public void getFoodBeanList(OnFoodBeanLoadListener onFoodBeanLoadListener);
    public void getDrinksBeanList(OnDrinksBeanLoadListener onDrinksBeanLoadListener);
    public void getOrderBeanList(OnOrderBeanLoadListener onOrderBeanLoadListener);
    public void getCollectionBeanList(OnCollectionLoadListener onCollectionLoadListener);
}
