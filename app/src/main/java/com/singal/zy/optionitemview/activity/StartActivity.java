package com.singal.zy.optionitemview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.jsbridge.CustomWebViewActivity;
import com.singal.zy.optionitemview.activity.jsbridge.WebViewActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        init();
    }

    private void init() {
        findViewById(R.id.btn_optionItemView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,MainActivity.class));
            }
        });

        findViewById(R.id.btn_videoRecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,VideoRecordActivity.class));
            }
        });

        findViewById(R.id.btn_recycler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,RecycleActivity.class));
            }
        });

        findViewById(R.id.btn_ImagePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,ImagePickerActivity.class));
            }
        });

        findViewById(R.id.btn_Emoji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,EmojiActivity.class));
            }
        });

        findViewById(R.id.btn_NineGridImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,NineGridActivity.class));
            }
        });

        findViewById(R.id.btn_clockView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,ClockViewActivity.class));
            }
        });

        findViewById(R.id.btn_takePhotos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,MoreImagesActivity.class));
            }
        });

        findViewById(R.id.btn_imageshowpickerview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,ImageShowPickerViewActivity.class));
            }
        });

        findViewById(R.id.btn_uvideoplayer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,VideoPlayerActivity.class));
            }
        });

        findViewById(R.id.btn_agentWeb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,AgentWebActivity.class));
            }
        });

        findViewById(R.id.btn_searchview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,SearchViewActivity.class));
            }
        });

//        findViewById(R.id.btn_customView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(StartActivity.this,CustomViewActivity.class));
//            }
//        });
        //webview
        findViewById(R.id.btn_js_webview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,WebViewActivity.class));
            }
        });

        findViewById(R.id.btn_js_customweb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,CustomWebViewActivity.class));
            }
        });
        //recyclerView联动效果
        findViewById(R.id.btn_ganged).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,GangedRecycleActivity.class));
            }
        });

        findViewById(R.id.btn_ftp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,FTPActivity.class));
            }
        });

        findViewById(R.id.btn_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,PwdInputTextActivity.class));
            }
        });

        findViewById(R.id.btn_pieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,PieChartActivity.class));
            }
        });

        findViewById(R.id.btn_barChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,MBarChartActivity.class));
            }
        });

        findViewById(R.id.btn_weatherView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,WeatherViewActivity.class));
            }
        });

        findViewById(R.id.btn_incomeChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,IncomeChartActivity.class));
            }
        });

        findViewById(R.id.btn_autoFlow1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,AutoFlowActivity.class));
            }
        });

        findViewById(R.id.btn_autoFlow2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,AutoGridActivity.class));
            }
        });
    }
}
