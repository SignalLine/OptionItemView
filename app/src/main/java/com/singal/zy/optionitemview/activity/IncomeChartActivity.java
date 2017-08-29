package com.singal.zy.optionitemview.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.singal.zy.library.bean.IncomeModel;
import com.singal.zy.library.view.IncomeChart;
import com.singal.zy.optionitemview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IncomeChartActivity extends AppCompatActivity {

    private List<IncomeModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_chart);

        final IncomeChart chart = (IncomeChart) findViewById(R.id.incomeChart);
        list = new ArrayList<>();

        initData();

        chart.setDrawPoints(false).setFillArea(true).setPlayAnim(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chart.setDatas(list);
            }
        },1000);
    }

    private void initData() {
        Random random = new Random();
        for (int i = 0; i < 90; i++) {
            IncomeModel model=new IncomeModel();
            model.percent=-10+random.nextFloat()*21;//(-10,10)
            model.date=(int)(1+Math.random()*13)+"."+(int)(1+Math.random()*31);
            list.add(model);
        }
    }
}
