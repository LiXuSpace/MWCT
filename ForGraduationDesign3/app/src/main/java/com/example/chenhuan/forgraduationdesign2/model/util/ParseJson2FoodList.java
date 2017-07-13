package com.example.chenhuan.forgraduationdesign2.model.util;

import android.util.Log;

import com.example.chenhuan.forgraduationdesign2.model.bean.FoodBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;


/**
 * Created by lixu on 2017/5/14.
 */

public class ParseJson2FoodList {
    public static ArrayList<FoodBean> getFoodList(String jsonStr) {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(jsonStr).getAsJsonArray();
        ArrayList<FoodBean> foodList = new ArrayList<>();
        for (JsonElement obj : jsonArray) {
            FoodBean foodBean = gson.fromJson(obj, FoodBean.class);
            foodList.add(foodBean);
        }
        Log.d("test", "getFoodList: ------------333333" + foodList.toString());

        return foodList;
    }
}
