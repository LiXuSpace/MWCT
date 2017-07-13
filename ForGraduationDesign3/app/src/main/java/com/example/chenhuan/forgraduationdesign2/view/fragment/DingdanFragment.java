package com.example.chenhuan.forgraduationdesign2.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.OnOrderInfoListener;
import com.example.chenhuan.forgraduationdesign2.model.bean.FoodBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;
import com.example.chenhuan.forgraduationdesign2.model.util.ParseJson2OrderList;
import com.example.chenhuan.forgraduationdesign2.model.util.PopupWindow;
import com.example.chenhuan.forgraduationdesign2.view.adapter.RecyclerViewAdapter;
import com.example.chenhuan.forgraduationdesign2.view.base.BaseFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lixu on 2017/3/30.
 */

public class DingdanFragment extends BaseFragment {


    private List<FoodBean> list ;
    private ArrayList<OrderBean> orderList;

    @Override
    public int getLayoutId(){
        return R.layout.dingdan_fg_layout;
    }
    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("listJson", Context.MODE_PRIVATE);
        String orderJson = sharedPreferences.getString("orderJson", "没有数据");
        orderList = ParseJson2OrderList.getOrderList(orderJson);
        if (orderList != null) {
            RecyclerView.Adapter adapter = new RecyclerViewAdapter(getContext(), orderList, onClickListener);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);


        }


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final OrderBean orderBean = orderList.get(((int) v.getTag()));
            new AlertDialog.Builder(getContext()).setTitle("再下一单？")
                    .setMessage(orderList.get(((int) v.getTag())).getOrderName())
                    .setNegativeButton("否 ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
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
                                        order.setImgUrl(orderBean.getImgUrl());
                                        order.setOrderName(orderBean.getOrderName());
                                        order.setAddress(address);
                                        order.setOrderPrice(orderBean.getOrderPrice());
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
                    }).show();

        }
    };
    @Override
    protected void loadData() {
//        list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            FoodBean fb = new FoodBean();
//            fb.setFoodName("zhangsan");
//            fb.setMonthSaleNum(100);
//            fb.setPraiseNum(15);
//            list.add(fb);
//        }
    }
    /**
     * 传入查询到的foodbean的list
     *
     * @param list
     */
    public void upDateData(List<OrderBean> list, SharedPreferences preference) {

        //将list对象转成JSON数据以存储在sharedpreference中
        Gson gson = new Gson();
        String listJson = gson.toJson(list);
        SharedPreferences.Editor edit = preference.edit();
        edit.putString("orderJson", listJson);
        edit.commit();

    }

}
