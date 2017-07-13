package com.example.chenhuan.forgraduationdesign2.presenter;

import android.os.Handler;

import com.example.chenhuan.forgraduationdesign2.model.ILoadData;
import com.example.chenhuan.forgraduationdesign2.model.ILoadDataImpl;
import com.example.chenhuan.forgraduationdesign2.model.OnDrinksBeanLoadListener;
import com.example.chenhuan.forgraduationdesign2.model.OnFoodBeanLoadListener;
import com.example.chenhuan.forgraduationdesign2.model.OnOrderBeanLoadListener;
import com.example.chenhuan.forgraduationdesign2.model.bean.DrinksBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.FoodBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;
import com.example.chenhuan.forgraduationdesign2.view.ICollectionView;
import com.example.chenhuan.forgraduationdesign2.view.IFoodBeanView;

import java.util.List;

/**
 * Created by lixu on 2017/4/17.
 */

public class FoodBeanPresenter {
    private ILoadData iLoadData;
    private IFoodBeanView iFoodBeanView;
    private ICollectionView iCollectionView;
    private Handler handler = new Handler();


    public FoodBeanPresenter(IFoodBeanView iFoodBeanView) {
        this.iLoadData = new ILoadDataImpl();
        this.iFoodBeanView = iFoodBeanView;
    }

    public FoodBeanPresenter(ICollectionView iCollectionView,String aa) {
        this.iLoadData = new ILoadDataImpl();
        this.iCollectionView = iCollectionView;
    }

    public void show(){
        iLoadData.getFoodBeanList(new OnFoodBeanLoadListener() {
            @Override
            public void success(final List<FoodBean> foodBeanList) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iFoodBeanView.show(foodBeanList);
                    }
                });
            }

            @Override
            public void error(final String e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iFoodBeanView.error(e);
                    }
                });
            }
        });

        iLoadData.getDrinksBeanList(new OnDrinksBeanLoadListener() {
            @Override
            public void success(final List<DrinksBean> drinksBeanList) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iFoodBeanView.show2(drinksBeanList);
                    }
                });
            }

            @Override
            public void error(final String e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iFoodBeanView.error(e);
                    }
                });
            }
        });

        iLoadData.getOrderBeanList(new OnOrderBeanLoadListener() {
            @Override
            public void success(final List<OrderBean> orderBeanList) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iFoodBeanView.show3(orderBeanList);
                    }
                });
            }

            @Override
            public void error(String e) {
                iFoodBeanView.error(e.toString());
            }
        });

//        iLoadData.getCollectionBeanList(new OnCollectionLoadListener() {
//            @Override
//            public void success(final List<CollectionBean> collectionBeanList) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("test", "run: -------------" + collectionBeanList.toString());
//                    }
//                });
//            }
//
//            @Override
//            public void error(String e) {
//                iCollectionView.error(e);
//            }
//        });

    }
}
