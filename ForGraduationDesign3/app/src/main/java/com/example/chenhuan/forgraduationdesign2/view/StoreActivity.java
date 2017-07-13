package com.example.chenhuan.forgraduationdesign2.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.OnOrderInfoListener;
import com.example.chenhuan.forgraduationdesign2.model.bean.CollectionBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;
import com.example.chenhuan.forgraduationdesign2.model.util.ParseJson2CollectionList;
import com.example.chenhuan.forgraduationdesign2.model.util.PopupWindow;
import com.example.chenhuan.forgraduationdesign2.presenter.CollectionPresenter;
import com.example.chenhuan.forgraduationdesign2.view.adapter.CollectionRecyclerViewAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class StoreActivity extends AppCompatActivity implements ICollectionView {
    private List<CollectionBean> collectionBeanList = new ArrayList<>();
    private CollectionPresenter presenter = new CollectionPresenter(this);
    private ArrayList<CollectionBean> collectionList;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        presenter.show();
        SharedPreferences sharedPreferences = getSharedPreferences("listJson", Context.MODE_PRIVATE);
        String string = sharedPreferences.getString("collectionJson", "[]");
        Log.d("test", "onCreate: ---------"+string);
        collectionList = ParseJson2CollectionList.getCollectionList(string);


        RecyclerView storeRv = (RecyclerView) findViewById(R.id.store_rv);
        RecyclerView.Adapter adapter = new CollectionRecyclerViewAdapter(this, collectionList,onClickListener);
        storeRv.setLayoutManager(new LinearLayoutManager(this));
        storeRv.setAdapter(adapter);

    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            final CollectionBean collectionBean = collectionList.get(position);
            Log.d("test", "onClick: ----------"+collectionBean.getOrderName());
            new AlertDialog.Builder(context).setTitle("再次购买？")
                    .setMessage(collectionBean.getOrderName())
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //点击是
                    //Log.d("test", "onClick: -----点击是");
                    PopupWindow popupWindow = new PopupWindow(context, R.style.my_pw_anim, new OnOrderInfoListener() {

                        //数据回调
                        @Override
                        public void success(String address, String name, String phoneNum) {
                            //Log.d("test", "success: ----------"+address+name+phoneNum);
                            final OrderBean order = new OrderBean();
                            if (address.equals("") || name.equals("") || phoneNum.equals("")) {
                                Toast.makeText(context, "添加失败！填写信息不能为空！", Toast.LENGTH_LONG).show();
                            } else {
                                order.setImgUrl(collectionBean.getImgUrl());
                                order.setOrderName(collectionBean.getOrderName());
                                order.setAddress(address);
                                order.setOrderPrice(collectionBean.getOrderPrice());
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
                                        Toast.makeText(context, "已成功下单，美食即将送到您手中！", Toast.LENGTH_LONG).show();
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
    public void showCollection(List<CollectionBean> collectionBeanList) {
        //将list对象转成JSON数据以存储在sharedpreference中
        Log.d("test", "showCollection: -===============" + collectionBeanList.toString());
        Gson gson = new Gson();
        String listJson = gson.toJson(collectionBeanList);
        Log.d("test", "showCollection: --------------------"+listJson);
        SharedPreferences.Editor edit = getSharedPreferences("listJson", MODE_PRIVATE).edit();
        edit.putString("collectionJson", listJson);
        edit.commit();
    }

    @Override
    public void error(String e) {

    }
}
