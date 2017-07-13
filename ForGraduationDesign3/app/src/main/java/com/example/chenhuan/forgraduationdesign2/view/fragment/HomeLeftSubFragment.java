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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.OnOrderInfoListener;
import com.example.chenhuan.forgraduationdesign2.model.bean.CollectionBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.FoodBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;
import com.example.chenhuan.forgraduationdesign2.model.util.ParseJson2FoodList;
import com.example.chenhuan.forgraduationdesign2.model.util.PopupWindow;
import com.example.chenhuan.forgraduationdesign2.view.StoreActivity;
import com.example.chenhuan.forgraduationdesign2.view.adapter.ListViewAdapter;
import com.example.chenhuan.forgraduationdesign2.view.base.BaseFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lixu on 2017/4/4.
 * 这是首页 主食 的fragment
 */

public class HomeLeftSubFragment extends BaseFragment {

    private static HomeLeftSubFragment subFragment;
    private List<FoodBean> foodBeenlist = new ArrayList<>();
    private ListView leftLv;
    private ArrayList<FoodBean> foodList;


    public interface OnUpDateDataListener{
        public void onUpdateData();
    }


    //单例模式
    public static Fragment newInstans(String title) {
        subFragment = new HomeLeftSubFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        subFragment.setArguments(args);
        return subFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_leftsub_fg_layout;
    }


    @Override
    protected void initView(View view) {
        leftLv = (ListView) view.findViewById(R.id.leftsub_fg_lv);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("listJson", Context.MODE_PRIVATE);
        String string = sharedPreferences.getString("json", "没有数据");
        foodList = ParseJson2FoodList.getFoodList(string);

        if (foodList != null) {
            Adapter adapter = new ListViewAdapter(foodList, getActivity(), onclickListener);
            leftLv.setAdapter((ListAdapter) adapter);

            //listview的点击事件在这里处理
            leftLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Log.d("test", "onItemClick: --------点击了---------" + foodList.get(position).getFoodName());
                    final FoodBean foodBean = foodList.get(position);
                    new AlertDialog.Builder(getActivity()).setTitle("加入购物车？").setMessage(foodList.get(position).getFoodName())
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //点击是
                                    //Log.d("test", "onClick: -----点击是");
                                    PopupWindow popupWindow = new PopupWindow(getContext(), R.style.my_pw_anim, new OnOrderInfoListener() {

                                        //数据回调
                                        @Override
                                        public void success(String address, String name, String phoneNum) {
                                            //                                            Log.d("test", "success: ----------"+address+name+phoneNum);
                                            final OrderBean order = new OrderBean();
                                            if (address.equals("") || name.equals("") || phoneNum.equals("")) {
                                                Toast.makeText(getContext(), "添加失败！填写信息不能为空！", Toast.LENGTH_LONG).show();
                                            } else {
                                                order.setImgUrl(foodBean.getImgUrl());
                                                order.setOrderName(foodBean.getFoodName());
                                                order.setAddress(address);
                                                order.setOrderPrice(foodBean.getPrice());
                                                order.setPhone(phoneNum);
                                                order.setUsername(name);
                                                order.save(new SaveListener<String>() {
                                                    @Override
                                                    public void done(String s, BmobException e) {
                                                        Log.d("test", "done: ==============1111111111111");
                                                        if (e == null) {
                                                            Log.d("test", "done: ------------qqqqqqqqqq-" + order);
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
                                                        } else {
                                                            Toast.makeText(getContext(), "网络好像断开了！请检查网络设置！", Toast.LENGTH_LONG).show();
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
     * floatingActionBtn的点击事件在这里处理
     * 这个onclicklistener 作为adapter的参数。
     * 点击跳转到收藏
     */
    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FloatingActionButton fab = (FloatingActionButton) v;
            final int position = (int) fab.getTag();
            new AlertDialog.Builder(getContext()).setTitle("确认收藏？")
                    .setMessage(foodList.get(position).getFoodName())
                    .setNegativeButton("否 ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            doCollection(foodList.get(position))//执行收藏操作
                            ;
                        }
                    }).show();
        }
    };

    private void doCollection(FoodBean foodBean) {
        CollectionBean collectionBean = new CollectionBean();
        collectionBean.setImgUrl(foodBean.getImgUrl());
        collectionBean.setOrderName(foodBean.getFoodName());
        collectionBean.setOrderPrice(foodBean.getPrice());
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
    public void upDateData(List<FoodBean> list, SharedPreferences preference) {

        //将list对象转成JSON数据以存储在sharedpreference中
        Gson gson = new Gson();
        String listJson = gson.toJson(list);
        SharedPreferences.Editor edit = preference.edit();
        edit.putString("json", listJson);
        edit.commit();

    }


}
