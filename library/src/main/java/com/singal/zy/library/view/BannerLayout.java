package com.singal.zy.library.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.singal.zy.library.R;
import com.singal.zy.library.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 自定义轮播组件
 *
 * 更多自定义轮播组件样式
 *      https://github.com/youth5201314/banner
 *
 * Created by li on 2017/8/30.
 */

public class BannerLayout extends LinearLayout{

    private ViewPager viewPager;
    private LinearLayout pointLayout;
    private ScheduledExecutorService scheduler;
    private int mPosition = 0;
    private int mBannerCount = 1;
    private Context context;
    private Activity activity;
    private int bannerPointSize;
    private int bannerPointGravity;
    private int bannerPointDrawableSelected, bannerPointDrawableUnselected;
    private int bannerDelaySecond;
    public BannerLayout(Context context) {
        this(context, null);
    }
    public BannerLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public BannerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }
    private void initView(Context context, AttributeSet attrs) {
        this.context = context;
        activity = (Activity) context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerLayout);
        bannerPointSize = typedArray.getDimensionPixelSize(R.styleable.BannerLayout_bannerPointSize, 10);
        bannerPointGravity = typedArray.getInt(R.styleable.BannerLayout_bannerPointGravity, Gravity.CENTER);
        bannerDelaySecond = typedArray.getInt(R.styleable.BannerLayout_bannerDelaySecond, 5);
        bannerPointDrawableSelected = typedArray.getResourceId(R.styleable.BannerLayout_bannerPointDrawableSelected, R.drawable.banner_gray_radius);
        bannerPointDrawableUnselected = typedArray.getResourceId(R.styleable.BannerLayout_bannerPointDrawableUnselected, R.drawable.banner_white_radius);
        typedArray.recycle();
        View view = View.inflate(context, R.layout.banner_view_pager, null);
        addView(view);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        pointLayout = (LinearLayout) view.findViewById(R.id.pointLayout);
        pointLayout.setGravity(bannerPointGravity);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                addPointLayout(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    public void start(List<Object> bannerList) {
        bannerShutdown();
        mBannerCount = bannerList.size();
        BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(context, bannerList);
        viewPager.setAdapter(bannerPagerAdapter);
        addPointLayout(0);
        startScheduler();
    }
    private void addPointLayout(int position) {
        pointLayout.removeAllViews();
        for (int i = 0; i < mBannerCount; i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(bannerPointSize, bannerPointSize);
            layoutParams.setMargins(10, 0, 0, 0);
            imageView.setLayoutParams(layoutParams);
            if (position == i) {
                imageView.setImageResource(bannerPointDrawableSelected);
            } else {
                imageView.setImageResource(bannerPointDrawableUnselected);
            }
            pointLayout.addView(imageView);
        }
    }
    private void startScheduler() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                mPosition = viewPager.getCurrentItem();
                if (mPosition < mBannerCount - 1) {
                    mPosition++;
                } else {
                    mPosition = 0;
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(mPosition);
                    }
                });
            }
        }, 1, bannerDelaySecond, TimeUnit.SECONDS);
    }
    public void bannerShutdown() {
        if (scheduler != null)
            scheduler.shutdown();
    }
    private class BannerPagerAdapter extends PagerAdapter {
        private List<Object> bannerList = new ArrayList<>();
        private Context context;
        BannerPagerAdapter(Context context, List<Object> bannerList) {
            this.context = context;
            this.bannerList.clear();
            this.bannerList.addAll(bannerList);
        }
        @Override
        public int getCount() {
            return bannerList.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Object object = bannerList.get(position);
            //这里我封装了 Glide 4.0 的工具类，用于显示图片
            ImageLoaderUtil.load(context, object, imageView);
            container.addView(imageView);
            return imageView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    public int dp2px(float var0) {
        float var1 = context.getResources().getDisplayMetrics().density;
        return (int) (var0 * var1 + 0.5F);
    }
    public void setBannerPointSize(int bannerPointSize) {
        this.bannerPointSize = dp2px(bannerPointSize);
    }
    public void setBannerPointGravity(int bannerPointGravity) {
        this.bannerPointGravity = bannerPointGravity;
        pointLayout.setGravity(bannerPointGravity);
    }
    public void setBannerPointDrawableSelected(int bannerPointDrawableSelected) {
        this.bannerPointDrawableSelected = bannerPointDrawableSelected;
    }
    public void setBannerPointDrawableUnselected(int bannerPointDrawableUnselected) {
        this.bannerPointDrawableUnselected = bannerPointDrawableUnselected;
    }
    public void setBannerDelaySecond(int bannerDelaySecond) {
        this.bannerDelaySecond = bannerDelaySecond;
    }

}
