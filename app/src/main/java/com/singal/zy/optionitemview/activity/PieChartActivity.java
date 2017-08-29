package com.singal.zy.optionitemview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.singal.zy.library.view.MyPieChart;
import com.singal.zy.optionitemview.R;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    MyPieChart mPieChart;
    private List<MyPieChart.PieEntry> mPieEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        mPieChart = (MyPieChart) findViewById(R.id.mypiechart);
        mPieEntries = new ArrayList<>();
        mPieEntries.add(new MyPieChart.PieEntry(1, R.color.chart_orange, true));
        mPieEntries.add(new MyPieChart.PieEntry(2, R.color.chart_green, false));
        mPieEntries.add(new MyPieChart.PieEntry(3, R.color.chart_blue, false));
        mPieEntries.add(new MyPieChart.PieEntry(4, R.color.chart_purple, false));
        mPieEntries.add(new MyPieChart.PieEntry(5, R.color.chart_mblue, false));
        mPieEntries.add(new MyPieChart.PieEntry(6, R.color.chart_turquoise, false));

        mPieChart.setPieEntries(mPieEntries);

        mPieChart.setOnItemClickListener(new MyPieChart.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(PieChartActivity.this, "点击了-->" + position, Toast.LENGTH_SHORT).show();
                Log.i("tag", "pieEntry------>" + mPieEntries.toString());
            }
        });
    }
}
