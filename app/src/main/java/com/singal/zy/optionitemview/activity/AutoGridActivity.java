package com.singal.zy.optionitemview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.singal.zy.library.adapter.FlowAdapter;
import com.singal.zy.library.view.AutoFlowLayout;
import com.singal.zy.optionitemview.R;

import java.util.Arrays;

public class AutoGridActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAddButton;
    private Button mDeleteButton;
    private AutoFlowLayout mNormalGridLayout;
    private int count =6;
    private String[] mData = {"Java", "C++", "Python", "JavaScript", "Ruby", "Swift"};
    private LayoutInflater mLayoutInflater;


    private AutoFlowLayout mSpecialFlowLayout;
    private String[] mData2 = {"Java", "C++", "Python", "JavaScript", "Ruby", "Swift","Swift","MATLAB","Scratch"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_grid);

        initNormalGrid();
        initSpecialGrid();

    }

    private void initNormalGrid() {
        mAddButton = (Button) findViewById(R.id.bt_add);
        mDeleteButton = (Button) findViewById(R.id.bt_delete);
        mAddButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
        mLayoutInflater = LayoutInflater.from(this);
        mNormalGridLayout = (AutoFlowLayout) findViewById(R.id.afl_content1);
        mNormalGridLayout.setAdapter(new FlowAdapter(Arrays.asList(mData)) {
            @Override
            public View getView(int position) {
                View item = mLayoutInflater.inflate(R.layout.layout_grid_item, null);
                return item;
            }
        });
    }

    private void initSpecialGrid() {
        mSpecialFlowLayout = (AutoFlowLayout) findViewById(R.id.afl_content2);
        mSpecialFlowLayout.setAdapter(new FlowAdapter(Arrays.asList(mData2)) {
            @Override
            public View getView(int position) {
                View item = mLayoutInflater.inflate(R.layout.layout_special_grid_item, null);
                return item;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:{
                count++;
                mNormalGridLayout.setRowNumbers(count%3 == 0 ? count/3 : count/3 + 1);
                View item = mLayoutInflater.inflate(R.layout.layout_grid_item, null);
                mNormalGridLayout.addView(item);
                break;
            }
            case R.id.bt_delete:{
                count--;
                mNormalGridLayout.setRowNumbers(count%3 == 0 ? count/3 : count/3 + 1);
                mNormalGridLayout.deleteView();
                break;
            }
        }
    }
}
