package com.example.chenhuan.forgraduationdesign2.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lixu on 2017/3/30.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadData();
        return inflater.inflate(getLayoutId(),container,false);
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);

    }

    public abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void loadData();



}
