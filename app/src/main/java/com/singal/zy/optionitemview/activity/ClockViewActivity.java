package com.singal.zy.optionitemview.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.singal.zy.normal_libs.view.ClockView;
import com.singal.zy.optionitemview.R;

import java.util.Calendar;

public class ClockViewActivity extends AppCompatActivity {

    private TextView mTextTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_view);

        initView();
    }

    private void initView() {
        mTextTime = (TextView) findViewById(R.id.tv_time);
        ClockView c  = (ClockView) findViewById(R.id.clockView);

//        c.setRadius(c.getWidth() - 20);

        mHandler.sendEmptyMessage(0);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextTime.setText(getTime());

            mHandler.sendEmptyMessageDelayed(0,1000);
        }
    };

    public String getTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR)+":"+
                calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
    }

    private int getWidth(){
        WindowManager wm = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        return width;
    }
}
