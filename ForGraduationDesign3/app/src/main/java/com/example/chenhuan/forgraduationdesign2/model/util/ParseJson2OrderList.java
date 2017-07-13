package com.example.chenhuan.forgraduationdesign2.model.util;

import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by lixu on 2017/5/19.
 */

public class ParseJson2OrderList {
    public static ArrayList<OrderBean> getOrderList(String json){
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        ArrayList<OrderBean> orderList = new ArrayList<>();
        for (JsonElement obj : jsonArray) {
            OrderBean orderBean = gson.fromJson(obj, OrderBean.class);
            orderList.add(orderBean);
        }
        return orderList;
    }
}
