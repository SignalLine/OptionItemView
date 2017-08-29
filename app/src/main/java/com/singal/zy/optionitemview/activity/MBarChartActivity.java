package com.singal.zy.optionitemview.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.model.MBarDataEntity;

import java.text.DecimalFormat;

public class MBarChartActivity extends AppCompatActivity {

    LinearLayout container;
    DecimalFormat format = new DecimalFormat("##.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbar_chart);

        bindData();
    }

    private void bindData() {
        container = (LinearLayout) findViewById(R.id.activity_mbar_chart);
        container.removeAllViews();
        MBarDataEntity data = new MBarDataEntity();
        data.parseData();
        if (data == null || data.getTypeList() == null) {
            return;
        }
        int color = Color.parseColor("#3FA0FF");
        double maxScale = 0;
        for (int i = 0; i < data.getTypeList().size(); i++) {
            if (data.getTypeList().get(i).getTypeScale() > maxScale)
                maxScale = data.getTypeList().get(i).getTypeScale();
        }
        for (int i = 0; i < data.getTypeList().size(); i++) {
            final View item = LayoutInflater.from(this).inflate(R.layout.layout_item_mbar, container, false);
            final MBarDataEntity.Type type = data.getTypeList().get(i);
            ((TextView) item.findViewById(R.id.index)).setText("");
            ((TextView) item.findViewById(R.id.name)).setText(type.getTypeName());
            ((TextView) item.findViewById(R.id.index)).setText("" + i);
            final View bar = item.findViewById(R.id.bar);
            bar.setBackgroundColor(color);
            ((TextView) item.findViewById(R.id.percent)).setText(format.format(type.getTypeScale() * 100) + "%");
            ((TextView) item.findViewById(R.id.percent)).setTextColor(color);
            final double finalMaxScale = maxScale;
            item.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    item.getViewTreeObserver().removeOnPreDrawListener(this);
                    int barContainerWidth = item.findViewById(R.id.bar_container).getWidth();
                    int percentTxtWidth = item.findViewById(R.id.percent).getWidth();
                    final int initWidth = barContainerWidth - percentTxtWidth;
                    final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) bar.getLayoutParams();
                    lp.width = (int) (initWidth * type.getTypeScale()/ finalMaxScale * 100 / 100);
                    bar.setLayoutParams(lp);
                    item.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final int initWidth = bar.getWidth();
                            final ObjectAnimator anim = ObjectAnimator.ofFloat(bar, "alpha", 0.0F, 1.0F).setDuration(1500);
                            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float cVal = (Float) anim.getAnimatedValue();
                                    lp.width = (int) (initWidth * cVal);
                                    bar.setLayoutParams(lp);
                                }
                            });
                            anim.start();
                        }
                    }, 0);
                    return false;
                }
            });
            container.addView(item);
        }
    }
}
