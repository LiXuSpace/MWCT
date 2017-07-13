package com.example.chenhuan.forgraduationdesign2.model;

import android.util.Log;

import com.example.chenhuan.forgraduationdesign2.model.bean.CollectionBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.DrinksBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.FoodBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lixu on 2017/4/13.
 */

public class ILoadDataImpl implements ILoadData {

    /**
     * 执行查询语句 查询到foodbeanlist数据
     */
    @Override
    public void getFoodBeanList(final OnFoodBeanLoadListener onFoodBeanLoadListener) {
        //查询foodbean的list
        BmobQuery<FoodBean> query = new BmobQuery<>();
        query.addWhereLessThan("price", 100.0);
        query.findObjects(new FindListener<FoodBean>() {
            @Override
            public void done(List<FoodBean> list, BmobException e) {
                if (e == null && onFoodBeanLoadListener != null) {
                    onFoodBeanLoadListener.success(list);

                } else if (e != null) {
                    onFoodBeanLoadListener.error(e.toString());
                } else {
                    onFoodBeanLoadListener.error("onFoodBeanListener不能为空！");
                }
            }
        });
    }
    /**
     * 执行查询语句 查询到drinksbeanlist的数据
     */
    @Override
    public void getDrinksBeanList(final OnDrinksBeanLoadListener onDrinksBeanLoadListener) {
        BmobQuery<DrinksBean> drinksBeanBmobQuery = new BmobQuery<>();
        drinksBeanBmobQuery.addWhereLessThan("price", 100.0);
        drinksBeanBmobQuery.findObjects(new FindListener<DrinksBean>() {
            @Override
            public void done(List<DrinksBean> list, BmobException e) {
                if (e == null && onDrinksBeanLoadListener != null) {
                    onDrinksBeanLoadListener.success(list);

                } else if (e != null) {
                    onDrinksBeanLoadListener.error(e.toString());
                } else {
                    onDrinksBeanLoadListener.error("onDrinksBeanLoadListener不能为空！");
                }
            }
        });

    }

    @Override
    public void getOrderBeanList(final OnOrderBeanLoadListener onOrderBeanLoadListener) {
        BmobQuery<OrderBean> orderBeanBmobQuery = new BmobQuery<>();
        orderBeanBmobQuery.addWhereLessThan("orderPrice",100.0);
        orderBeanBmobQuery.findObjects(new FindListener<OrderBean>() {
            @Override
            public void done(List<OrderBean> list, BmobException e) {
                if (e==null&& onOrderBeanLoadListener!=null) {
                    onOrderBeanLoadListener.success(list);
                } else if (e!=null) {
                    onOrderBeanLoadListener.error(e.toString());
                }else {
                    onOrderBeanLoadListener.error("onOrderBeanListener不能为空！");
                }
            }
        });


    }

    @Override
    public void getCollectionBeanList(final OnCollectionLoadListener onCollectionLoadListener) {
        BmobQuery<CollectionBean> orderBeanBmobQuery = new BmobQuery<>();
        orderBeanBmobQuery.addWhereLessThan("orderPrice",100.0);
        orderBeanBmobQuery.findObjects(new FindListener<CollectionBean>() {
            @Override
            public void done(List<CollectionBean> list, BmobException e) {
                Log.d("test", "done: -----------" + list.size());
                if (e==null&& onCollectionLoadListener!=null) {
                    onCollectionLoadListener.success(list);
                } else if (e!=null) {
                    onCollectionLoadListener.error(e.toString());
                }else {
                    onCollectionLoadListener.error("onCollectionLoadListener不能为空！");
                }
            }
        });
    }


}
