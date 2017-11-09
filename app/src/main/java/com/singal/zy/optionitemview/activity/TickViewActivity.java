package com.singal.zy.optionitemview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.singal.zy.library.tick.OnCheckedChangeListener;
import com.singal.zy.library.tick.TickAnimatorListener;
import com.singal.zy.library.tick.TickView;
import com.singal.zy.optionitemview.R;

/**
 * 自定义打钩动画
 *
 * @author li
 */
public class TickViewActivity extends AppCompatActivity {
    private static final String TAG = TickViewActivity.class.getSimpleName();

    TickView tickView;
    TickView tickViewAccent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick_view);

        tickView = (TickView) findViewById(R.id.tick_view);
        tickView.getConfig().setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(TickView tickView, boolean isCheck) {

            }
        }).setTickAnimatorListener(new TickAnimatorListener.TickAnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(TickView tickView) {
                super.onAnimationStart(tickView);
            }
        });


        tickViewAccent = (TickView) findViewById(R.id.tick_view_accent);
        findViewById(R.id.check_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tickView.setChecked(true);
                tickViewAccent.setChecked(true);
            }
        });
        findViewById(R.id.uncheck_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tickView.setChecked(false);
                tickViewAccent.setChecked(false);
            }
        });

    }
}
