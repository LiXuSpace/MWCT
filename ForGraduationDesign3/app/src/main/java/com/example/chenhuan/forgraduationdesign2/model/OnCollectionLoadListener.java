package com.example.chenhuan.forgraduationdesign2.model;

import com.example.chenhuan.forgraduationdesign2.model.bean.CollectionBean;

import java.util.List;

/**
 * Created by lixu on 2017/5/20.
 */

public interface OnCollectionLoadListener {
    public void success(List<CollectionBean> collectionBeanList);
    public void error(String e);
}
