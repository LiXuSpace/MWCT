package com.example.chenhuan.forgraduationdesign2.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.view.base.BaseActivity;

/**
 * Created by lixu on 2017/4/4.
 * -------------欢迎页----------------
 */

public class WelComeActivity extends BaseActivity {


    @Override
    protected int getContentId() {
        return R.layout.welcome_activity_layout;
    }

    @Override
    protected void initView() {

        final ImageView welBg = (ImageView) findViewById(R.id.welcome_bg);
        TextView titleTv = (TextView) findViewById(R.id.welcome_title);
        TextView titleTvNum = (TextView) findViewById(R.id.welcome_title_num);


        //设置字体
        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "font/Lobster-1.4.otf");
        titleTv.setTypeface(fromAsset);
        titleTvNum.setTypeface(fromAsset);

//        //设置动画
//        welBg.animate()
//                .scaleXBy(1)
//                .scaleX(1.1f)
//                .scaleYBy(1)
//                .scaleY(1.1f)
//                .setDuration(2000)
//                //设置动画播放完成后的监听 注意listener是AnimatorListenerAdapter
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        finish();
//                    }
//                })
//                .start();

        //设置背景动画
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 1.2f);
        valueAnimator.setDuration(4000);
        valueAnimator.setInterpolator(new LinearInterpolator());//线性加速器
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float) animation.getAnimatedValue();
                welBg.setScaleX(f);
                welBg.setScaleY(f);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }
        });
        valueAnimator.start();

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void bindListener() {

    }
}
