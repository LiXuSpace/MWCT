package com.example.chenhuan.forgraduationdesign2.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.OnOrderInfoListener;
import com.example.chenhuan.forgraduationdesign2.model.bean.CollectionBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.DrinksBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;
import com.example.chenhuan.forgraduationdesign2.model.util.ParseJson2DrinksList;
import com.example.chenhuan.forgraduationdesign2.model.util.PopupWindow;
import com.example.chenhuan.forgraduationdesign2.view.StoreActivity;
import com.example.chenhuan.forgraduationdesign2.view.adapter.RightListViewAdapter;
import com.example.chenhuan.forgraduationdesign2.view.base.BaseFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lixu on 2017/4/4.
 * 这是首页 饮品的fragment
 */

public class HomeRightSubFragment extends BaseFragment {

    private static HomeRightSubFragment subFragment;
    private ArrayList<DrinksBean> drinksList;

    //单例
    public static Fragment newInstans(String title) {
        subFragment = new HomeRightSubFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        subFragment.setArguments(args);
        return subFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_rightsub_fg_layout;
    }

    @Override
    protected void initView(View view) {
        Log.d("test", "initView: -----------9999999999999999999999999");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("listJson", Context.MODE_PRIVATE);
        String string = sharedPreferences.getString("drinksJson", "没有数据");
        drinksList = ParseJson2DrinksList.getDrinksList(string);
        Log.d("test", "initView: -----------" + drinksList);
        if (drinksList != null) {
            ListView rightLv = (ListView) view.findViewById(R.id.rightsub_fg_lv);
            Adapter adapter = new RightListViewAdapter(drinksList, getContext(), onClickListener);
            rightLv.setAdapter((RightListViewAdapter) adapter);

            rightLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    final DrinksBean drinksBean = drinksList.get(position);
                    new AlertDialog.Builder(getActivity()).setTitle("加入购物车？").setMessage(drinksBean.getDrinksName())
                            //                            .setView(textInput1)
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //点击是,弹出地址填写栏
                                    //                                    Log.d("test", "onClick: -----点击是");
                                    PopupWindow popupWindow = new PopupWindow(getContext(), R.style.my_pw_anim, new OnOrderInfoListener() {

                                        //数据回调
                                        @Override
                                        public void success(String address, String name, String phoneNum) {
                                            //                                            Log.d("test", "success: ----------"+address+name+phoneNum);
                                            final OrderBean order = new OrderBean();
                                            if (address.equals("") || name.equals("") || phoneNum.equals("")) {
                                                Toast.makeText(getContext(), "添加失败！填写信息不能为空！", Toast.LENGTH_LONG).show();
                                            } else {
                                                order.setImgUrl(drinksBean.getImgUrl());
                                                order.setOrderName(drinksBean.getDrinksName());
                                                order.setAddress(address);
                                                order.setOrderPrice(drinksBean.getPrice());
                                                order.setPhone(phoneNum);
                                                order.setUsername(name);
                                                order.save(new SaveListener<String>() {
                                                    @Override
                                                    public void done(String s, BmobException e) {
                                                        Log.d("test", "done: -------------" + order);
                                                        Runtime runtime = Runtime.getRuntime();
                                                        try {
                                                            runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
                                                        } catch (IOException e1) {
                                                            e1.printStackTrace();
                                                        }
                                                        Toast.makeText(getContext(), "已成功下单，美食即将送到您手中！", Toast.LENGTH_LONG).show();

                                                        try {
                                                            runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
                                                        } catch (IOException e1) {
                                                            e1.printStackTrace();
                                                        }

                                                    }
                                                });


                                            }

                                        }

                                        //返回错误信息
                                        @Override
                                        public void error(String e) {

                                        }
                                    });
                                    popupWindow.show();

                                }
                            })
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //点击否
                                    Log.d("test", "onClick: ------点击否");
                                }
                            }).show();
                }
            });
        }
    }

    /**
     * 这个onclicklistener 作为adapter的参数。
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) v;
            final int position = (int) floatingActionButton.getTag();
                    //点击弹出警告框
                    new AlertDialog.Builder(getContext()).setTitle("确认收藏？")
                            .setMessage(drinksList.get(position).getDrinksName())
                            .setNegativeButton("否 ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            })
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    doCollection(drinksList.get(position))//执行收藏操作
                                    ;
                                }
                            }).show();

                }



    };

    private void doCollection(DrinksBean drinksBean) {
        CollectionBean collectionBean = new CollectionBean();
        collectionBean.setImgUrl(drinksBean.getImgUrl());
        collectionBean.setOrderName(drinksBean.getDrinksName());
        collectionBean.setOrderPrice(drinksBean.getPrice());
        collectionBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                Snackbar.make(getView(), "收藏成功！", Snackbar.LENGTH_LONG)
                        //点击查看收藏！
                        .setAction("查看收藏", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(), StoreActivity.class));
                                Log.d("test", "onClick: --------------chakanshoucang ");
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    protected void loadData() {

    }

    /**
     * 传入查询到的foodbean的list
     *
     * @param list
     */
    public void upDateData(List<DrinksBean> list, SharedPreferences preference) {
        //        Log.d("test", "upDateData: ----------"+list);
        //将list对象转成JSON数据以存储在sharedpreference中
        Gson gson = new Gson();
        String listJson2 = gson.toJson(list);
        SharedPreferences.Editor edit = preference.edit();
        edit.putString("drinksJson", listJson2);
        edit.commit();

    }

}
