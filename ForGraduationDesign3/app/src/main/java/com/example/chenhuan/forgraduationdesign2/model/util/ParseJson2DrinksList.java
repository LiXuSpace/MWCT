package com.example.chenhuan.forgraduationdesign2.model.util;

import com.example.chenhuan.forgraduationdesign2.model.bean.DrinksBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by lixu on 2017/5/16.
 */

public class ParseJson2DrinksList {
    public static ArrayList<DrinksBean> getDrinksList(String jsonStr) {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(jsonStr).getAsJsonArray();
        ArrayList<DrinksBean> drinksBeanList = new ArrayList<>();
        for (JsonElement obj : jsonArray) {
            DrinksBean drinksBean = gson.fromJson(obj, DrinksBean.class);
            drinksBeanList.add(drinksBean);
        }

        return drinksBeanList;
    }
}
