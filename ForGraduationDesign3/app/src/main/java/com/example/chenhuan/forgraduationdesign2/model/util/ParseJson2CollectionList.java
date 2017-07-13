package com.example.chenhuan.forgraduationdesign2.model.util;

import com.example.chenhuan.forgraduationdesign2.model.bean.CollectionBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by lixu on 2017/5/16.
 */

public class ParseJson2CollectionList {
    public static ArrayList<CollectionBean> getCollectionList(String jsonStr) {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(jsonStr).getAsJsonArray();
        ArrayList<CollectionBean> collectionBeanList = new ArrayList<>();
        for (JsonElement obj : jsonArray) {
            CollectionBean collectionBean = gson.fromJson(obj, CollectionBean.class);
            collectionBeanList.add(collectionBean);
        }

        return collectionBeanList;
    }
}
