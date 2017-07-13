package com.example.chenhuan.forgraduationdesign2.view;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.bean.DrinksBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.FoodBean;
import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;
import com.example.chenhuan.forgraduationdesign2.model.util.LoginUtil;
import com.example.chenhuan.forgraduationdesign2.presenter.FoodBeanPresenter;
import com.example.chenhuan.forgraduationdesign2.view.base.BaseActivity;
import com.example.chenhuan.forgraduationdesign2.view.fragment.DingdanFragment;
import com.example.chenhuan.forgraduationdesign2.view.fragment.HomeFragment;
import com.example.chenhuan.forgraduationdesign2.view.fragment.HomeLeftSubFragment;
import com.example.chenhuan.forgraduationdesign2.view.fragment.HomeRightSubFragment;
import com.example.chenhuan.forgraduationdesign2.view.fragment.MineFragment;

import java.util.List;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, IFoodBeanView ,HomeLeftSubFragment.OnUpDateDataListener{

    private RadioGroup radioGroup;
    private RadioButton homeRdBtn;
    private RadioButton preRdBtn;
    private RadioButton dingDanRdBtn;
    private RadioButton mineRdBtn;
    private ImageView loginIv;
    private FoodBeanPresenter foodBeanPresenter = new FoodBeanPresenter(this);
    private HomeLeftSubFragment leftSubFragment = new HomeLeftSubFragment();
    private HomeRightSubFragment rightSubFragment = new HomeRightSubFragment();
    private DingdanFragment dingdanFragment = new DingdanFragment();
    private RelativeLayout storeLayout;
    private RelativeLayout serviceLayout;
    private RelativeLayout aboutLayout;
    private RelativeLayout conmentLayout;
    private ListView leftListView;
    private Context context = this;

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        startActivity(new Intent(MainActivity.this, WelComeActivity.class));
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_btn);
        homeRdBtn = (RadioButton) findViewById(R.id.home_rd_button);
        dingDanRdBtn = (RadioButton) findViewById(R.id.dingdan_rd_button);
        mineRdBtn = ((RadioButton) findViewById(R.id.mine_rd_button));
//        loginIv = (ImageView) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.radio_group_btn + ":2").getView().findViewById(R.id.login_iv);
//       loginIv = (ImageView) getFragmentManager().findFragmentByTag("com.example.chenhuan.forgraduationdesign2.view.fragment.MineFragment").getView().findViewById(R.id.login_iv);
        storeLayout = ((RelativeLayout) findViewById(R.id.store_layout));
        serviceLayout = ((RelativeLayout) findViewById(R.id.service_layout));
        aboutLayout = ((RelativeLayout) findViewById(R.id.about_layout));
        conmentLayout = ((RelativeLayout) findViewById(R.id.conment_layout));
        //调用presenter中的show方法，将数据传递到activity。
        foodBeanPresenter.show();

    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void bindListener() {

        radioGroup.setOnCheckedChangeListener(this);
        //模拟点击第一个button
        preRdBtn = homeRdBtn;
        radioGroup.getChildAt(0).performClick();

    }

    //    radioBtn 和 fragment的联动
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home_rd_button:
                preRdBtn.setSelected(false);
                preRdBtn.setTextColor(getResources().getColor(R.color.textColorBlack));
                homeRdBtn.setSelected(true);
                homeRdBtn.setTextColor(getResources().getColor(R.color.textcolor));
                preRdBtn = homeRdBtn;
                showFragment(R.id.frame_layout, new HomeFragment());
                break;
            case R.id.dingdan_rd_button:
                preRdBtn.setSelected(false);
                preRdBtn.setTextColor(getResources().getColor(R.color.textColorBlack));
                dingDanRdBtn.setSelected(true);
                dingDanRdBtn.setTextColor(getResources().getColor(R.color.textcolor));
                preRdBtn = dingDanRdBtn;
                showFragment(R.id.frame_layout, new DingdanFragment());
                break;
            case R.id.mine_rd_button:
                preRdBtn.setSelected(false);
                preRdBtn.setTextColor(getResources().getColor(R.color.textColorBlack));
                mineRdBtn.setSelected(true);
                mineRdBtn.setTextColor(getResources().getColor(R.color.textcolor));
                preRdBtn = mineRdBtn;
                showFragment(R.id.frame_layout, new MineFragment());
                break;
        }
    }

    /*
     *这里处理所有控件的点击事件
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_iv://点击头像跳转到登陆页面
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0版本以上支持专场动画
                    //共享元素动画
                    startActivity(new Intent(MainActivity.this, LoginActivity.class), ActivityOptions.makeSceneTransitionAnimation(this, v, "trans_img").toBundle());
                } else {//兼容5.0以下版本
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                break;
            case R.id.login_tv://点击登陆 跳转到登陆页面
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0版本以上支持专场动画
                    //共享元素动画
                    startActivity(new Intent(MainActivity.this, LoginActivity.class), ActivityOptions.makeSceneTransitionAnimation(this, findViewById(R.id.login_iv), "trans_img").toBundle());
                } else {//兼容5.0以下版本
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                break;


            case R.id.store_layout://点击收藏 跳转到收藏页面
                startActivity(new Intent(MainActivity.this, StoreActivity.class));
                break;
            case R.id.conment_layout://  退出登录
                new AlertDialog.Builder(this).setTitle("确定注销并退出？").setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoginUtil.setLoginStatus(context,false);
                                System.exit(0);
//                                loginIv.setImageResource(R.drawable.unlogin);
                            }
                        }).show();
                break;
            case R.id.service_layout://点击客服 跳转到客服页面
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
                break;
            case R.id.about_layout://点击关于 跳转到关于页面
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;

        }
    }

    /**
     * 这里处理登陆后跳转到主页面的头像、订单的更新
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("okok", "onResume: ----" + LoginUtil.getLoginStatus(this));
        if (LoginUtil.getLoginStatus(this)) {
//            loginIv.setImageResource(R.drawable.is_login);
        }
    }

    /**
     *
     * @param foodBeanList 数据成功回调的方法
     *
     */
    @Override
    public void show(List<FoodBean> foodBeanList) {
//        Log.d("test", "show: ----------"+foodBeanList);

        leftSubFragment.upDateData(foodBeanList, getSharedPreferences("listJson", MODE_PRIVATE));


    }

    /**
     *
     * @param drinksBeanList 数据成功回调的方法
     *
     */
    @Override
    public void show2(List<DrinksBean> drinksBeanList) {
//        Log.d("test", "show2: -----------"+drinksBeanList);
        rightSubFragment.upDateData(drinksBeanList,getSharedPreferences("listJson", MODE_PRIVATE));
    }

    @Override
    public void show3(List<OrderBean> orderBeanList) {
        dingdanFragment.upDateData(orderBeanList,getSharedPreferences("listJson",MODE_PRIVATE));
    }


    /**
     * 数据回掉失败的方法
     */
    @Override
    public void error(String e) {


    }

    /**
     * 更新历史订单数据
     */
    @Override
    public void onUpdateData() {


    }
}
