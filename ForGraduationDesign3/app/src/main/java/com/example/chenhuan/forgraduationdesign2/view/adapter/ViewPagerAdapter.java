package com.example.chenhuan.forgraduationdesign2.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by lixu on 2017/4/4.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<ImageView> imageViews;

    public ViewPagerAdapter(List<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return 5000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //添加一个页面到容器中
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imageViews.get(position%5);
        container.addView(imageView);
        return imageView;
    }
    //从容器中移除一个页面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position%5));
    }
}
