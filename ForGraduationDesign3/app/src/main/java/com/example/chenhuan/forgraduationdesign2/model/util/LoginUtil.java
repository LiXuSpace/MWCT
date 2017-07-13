package com.example.chenhuan.forgraduationdesign2.model.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lixu on 2017/4/17.
 */

public class LoginUtil {
    public static void setLoginStatus(Context context,boolean bool){

        SharedPreferences preferences = context.getSharedPreferences("isLogin", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("loginStatus", bool);
//        Log.d("okok", "setLoginStatus: ---设置了为true");
        edit.commit();
//        Log.d("okok", "setLoginStatus: ------现在的状态为"+getLoginStatus(context));
    }
    public static boolean getLoginStatus(Context context){
        SharedPreferences isLogin = context.getSharedPreferences("isLogin", MODE_PRIVATE);
        return isLogin.getBoolean("loginStatus", false);
    }

    public static void setLoginUserName(Context context,String username){

        SharedPreferences preferences = context.getSharedPreferences("isLogin", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("username", username);
        //        Log.d("okok", "setLoginStatus: ---设置了为true");
        edit.commit();
    }
    public static String getLoginUsername(Context context){
        SharedPreferences isLogin = context.getSharedPreferences("isLogin", MODE_PRIVATE);
        return isLogin.getString("username","user");
    }
}
