package com.singal.zy.optionitemview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import com.singal.zy.library.view.BannerLayout;
import com.singal.zy.optionitemview.R;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {

    private BannerLayout bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        initData();
    }

    private void initData() {
        bannerView = (BannerLayout) findViewById(R.id.bannerView);
        List<Object> bannerList = new ArrayList<>();
        bannerList.add(R.mipmap.default_image);
        bannerList.add("http://pic1.win4000.com/wallpaper/5/598161750eddb.jpg");
        bannerList.add("http://pic1.win4000.com/wallpaper/4/597efb5b6aae8.jpg");
        bannerView.setBannerPointSize(10);
        bannerView.setBannerPointGravity(Gravity.CENTER);
        bannerView.setBannerPointDrawableSelected(R.drawable.banner_gray_radius);
        bannerView.setBannerPointDrawableUnselected(R.drawable.banner_white_radius);
        bannerView.setBannerDelaySecond(5);
        //banner 设置方法完毕时最后调用 start 方法
        bannerView.start(bannerList);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerView.bannerShutdown();
    }
}
