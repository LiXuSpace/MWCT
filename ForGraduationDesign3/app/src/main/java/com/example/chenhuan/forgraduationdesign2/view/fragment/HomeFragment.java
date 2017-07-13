package com.example.chenhuan.forgraduationdesign2.view.fragment;

import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.view.adapter.TabFragmentAdapter;
import com.example.chenhuan.forgraduationdesign2.view.adapter.ViewPagerAdapter;
import com.example.chenhuan.forgraduationdesign2.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixu on 2017/3/30.
 */

public class HomeFragment extends BaseFragment {

    private List<Fragment> fragments;
    private List<String> titles;
    private int[] imgs = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p6};
    private List<ImageView> imageViews;
    private LinearLayout dotLayout;
    private int prePosition = 0;
    private boolean isRunning = true;

    @Override
    public int getLayoutId() {
        return R.layout.home_fg_layout;
    }

    @Override
    protected void initView(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.home_tb_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_vp);
        ViewPager headVp = (ViewPager) view.findViewById(R.id.home_vp_head);
        dotLayout = (LinearLayout) view.findViewById(R.id.dot_layout);
        initTabView(tabLayout, viewPager);
        initViewPager(headVp);
    }

    private void initViewPager(final ViewPager headVp) {
        imageViews = new ArrayList<>();
        //获取屏幕宽度
        WindowManager wm = (WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        initViewPager();//初始化viewpager中的图片及小圆点视图
        ViewPagerAdapter adapter = new ViewPagerAdapter(imageViews);
        dotLayout.getChildAt(0).setEnabled(false);
        headVp.setAdapter(adapter);
        headVp.setCurrentItem(1000);
        //添加轮播图与小圆点联动的监听
        headVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                dotLayout.getChildAt(prePosition).setEnabled(true);
                dotLayout.getChildAt(position % 5).setEnabled(false);
                prePosition = position % 5;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        /**
         * 当程序运行时启动子线程的自动轮播
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    SystemClock.sleep(5000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            headVp.setCurrentItem(headVp.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();
    }

    private void initViewPager() {
        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(getActivity());
//            Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromResource(getResources(), imgs[i], width, 200);
            imageView.setImageResource(imgs[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            //给轮播图添加小圆点
            View view = new View(getActivity());
            //将px转为dp
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
            //设置原点左边距
            params.leftMargin = 10;
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.dot_background);
            dotLayout.addView(view);
        }
    }

    /**
     * 初始化点菜和饮料的view
     */
    private void initTabView(TabLayout tabLayout, ViewPager viewPager) {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        titles.add("主食");
        titles.add("饮品");
        //初始化数据
        fragments.add(HomeLeftSubFragment.newInstans(titles.get(0)));
        fragments.add(HomeRightSubFragment.newInstans(titles.get(1)));
        TabFragmentAdapter adapter = new TabFragmentAdapter(getChildFragmentManager(), titles, fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected void loadData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        isRunning = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
