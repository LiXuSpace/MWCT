package com.example.chenhuan.forgraduationdesign2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.util.LoginUtil;
import com.example.chenhuan.forgraduationdesign2.view.base.BaseFragment;

/**
 * Created by lixu on 2017/3/30.
 */

public class MineFragment extends BaseFragment {

    private TextView loginTv;
    private ImageView loginIv;
    private RelativeLayout storeLayout;
    private RelativeLayout conmentLayout;
    private RelativeLayout aboutLayout;
    private RelativeLayout serviceLayout;

    @Override
    public int getLayoutId() {
        return R.layout.mine_fg_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void initView(View view) {
        loginIv = (ImageView) view.findViewById(R.id.login_iv);
        loginTv = ((TextView) view.findViewById(R.id.login_tv));
        storeLayout = (RelativeLayout) view.findViewById(R.id.store_layout);
        conmentLayout = (RelativeLayout) view.findViewById(R.id.conment_layout);
        aboutLayout = (RelativeLayout) view.findViewById(R.id.about_layout);
        serviceLayout = ((RelativeLayout) view.findViewById(R.id.service_layout));


        //所有的点击事件都在MainActivity中处理
        loginTv.setOnClickListener((View.OnClickListener) getActivity());
        loginIv.setOnClickListener((View.OnClickListener) getActivity());//点击头像以登陆
        boolean loginStatus = LoginUtil.getLoginStatus(getContext());
        Log.d("test", "initView: ---------1111111111111111111111"+loginStatus);

        if (loginStatus) {
            Log.d("test", "initView: ---------1111111111111111111111");
            loginIv.setImageResource(R.drawable.touxiang);
            loginTv.setText(LoginUtil.getLoginUsername(getContext()));
        }
        storeLayout.setOnClickListener(((View.OnClickListener) getActivity()));
        conmentLayout.setOnClickListener((View.OnClickListener) getActivity());
        aboutLayout.setOnClickListener((View.OnClickListener) getActivity());
        serviceLayout.setOnClickListener((View.OnClickListener) getActivity());

    }

    @Override
    protected void loadData() {

    }
}
