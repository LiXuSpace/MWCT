package com.example.chenhuan.forgraduationdesign2.presenter;

import android.util.Log;

import com.example.chenhuan.forgraduationdesign2.model.ILoadData;
import com.example.chenhuan.forgraduationdesign2.model.ILoadDataImpl;
import com.example.chenhuan.forgraduationdesign2.model.OnCollectionLoadListener;
import com.example.chenhuan.forgraduationdesign2.model.bean.CollectionBean;
import com.example.chenhuan.forgraduationdesign2.view.ICollectionView;

import java.util.List;

/**
 * Created by lixu on 2017/5/20.
 */

public class CollectionPresenter {
    private ICollectionView iCollectionView;
    private ILoadData iLoadData;
    public CollectionPresenter(ICollectionView iCollectionView) {
        this.iLoadData = new ILoadDataImpl();
        this.iCollectionView = iCollectionView;
    }
    public void show(){
        iLoadData.getCollectionBeanList(new OnCollectionLoadListener() {
            @Override
            public void success(final List<CollectionBean> collectionBeanList) {
                        Log.d("test", "run: -------------" + collectionBeanList.toString());
                        iCollectionView.showCollection(collectionBeanList);

            }

            @Override
            public void error(String e) {
                iCollectionView.error(e);
            }
        });
    }
}
