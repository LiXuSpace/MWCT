package com.example.chenhuan.forgraduationdesign2.view.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import cn.bmob.v3.Bmob;

/**
 * Created by lixu on 2017/3/30.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Fragment showFragment;//当前显示的fragment
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());

        //初始化BMOB
        Bmob.initialize(this, "4a3ef36539768055979ce74e8e5c0c31");
//        CollectionBean collectionBean = new CollectionBean();
//        collectionBean.setOrderName("aa");
//        collectionBean.setImgUrl("aa");
//        collectionBean.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//
//            }
//        });

        //获得fragment管理器
        fragmentManager = getSupportFragmentManager();

        loadData();
        initView();
        bindListener();



        //开启沉浸式状态栏
        if (isOpenStatus()) {
            Window window = getWindow();
            //判断系统版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0
                window.setStatusBarColor(Color.TRANSPARENT);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }

    }
    //获得内容ID
    protected abstract int getContentId();
    //初始化视图
    protected abstract void initView();
    //初始化数据
    protected abstract void loadData();
    //绑定监听
    protected abstract void bindListener();

    //是否开启沉浸式状态栏  默认为true
    protected boolean isOpenStatus() {
        return true;
    }

    //显示fragment的方法
    public void showFragment(int layoutId, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (showFragment != null) {
            //隐藏正在显示的fragment
            fragmentTransaction.hide(showFragment);
        }
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        Log.d("test", "showFragment: -------------"+fragment.getClass().getName());
        if (fragmentByTag != null) {
            //显示
            fragmentTransaction.show(fragmentByTag);
        } else {
            //新建
            fragmentByTag = fragment;
            fragmentTransaction.add(layoutId, fragmentByTag, fragmentByTag.getClass().getName());
        }
        fragmentTransaction.commit();
        showFragment = fragmentByTag;
    }
}
