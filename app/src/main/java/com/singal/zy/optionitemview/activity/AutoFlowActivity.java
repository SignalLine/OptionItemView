package com.singal.zy.optionitemview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.singal.zy.library.adapter.FlowAdapter;
import com.singal.zy.library.view.AutoFlowLayout;
import com.singal.zy.optionitemview.R;

import java.util.Arrays;

public class AutoFlowActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoFlowLayout mNormalFlowLayout;
    private String[] mData = {"Java","C++","Python","JavaScript","Ruby","Swift","MATLAB","Scratch","Drat","ABAP","COBOL","Fortran","Scala","Lisp",
            "Kotlin","Erlang","Groovy","Scheme","Rust","Logo","Prolog","LabVIEW"};
    private LayoutInflater mLayoutInflater;
    private Button mSingleButton;
    private Button mMultiLineButton;
    private Button mAddButton;
    private Button mDeleteButton;
    private Button mMultiSelectedButton;
    private Button mCenterButton;
    private int count = 10;

    //specialFlow
    private Button mChangeButton;
    private Button mLongClickButton;
    private AutoFlowLayout mSpecialFlowLayout;
    private String[] mData2 = {"Java", "C++", "Python", "JavaScript", "Ruby", "Swift", "MATLAB", "Scratch", "Drat", "ABAP", "COBOL", "Fortran", "Scala", "Lisp",
            "Kotlin", "Erlang", "Groovy", "Scheme", "Rust", "Logo", "Prolog", "LabVIEW"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_flow);

        initNormalView();

        initSpecialView();

    }

    private void initNormalView() {
        mNormalFlowLayout = (AutoFlowLayout) findViewById(R.id.afl_content1);
        mLayoutInflater = LayoutInflater.from(this);
        mSingleButton = (Button) findViewById(R.id.bt_single);
        mMultiLineButton = (Button) findViewById(R.id.bt_multi);
        mAddButton = (Button) findViewById(R.id.bt_add);
        mDeleteButton = (Button) findViewById(R.id.bt_delete);
        mMultiSelectedButton = (Button) findViewById(R.id.bt_checked);
        mCenterButton = (Button) findViewById(R.id.bt_center);
        mSingleButton.setOnClickListener(this);
        mMultiLineButton.setOnClickListener(this);
        mAddButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
        mMultiSelectedButton.setOnClickListener(this);
        mCenterButton.setOnClickListener(this);
        for (int i = 0; i< 10; i ++ ){
            View item = mLayoutInflater.inflate(R.layout.layout_sub_item, null);
            TextView tvAttrTag = (TextView) item.findViewById(R.id.tv_attr_tag);
            tvAttrTag.setText(mData[i]);
            mNormalFlowLayout.addView(item);
        }
    }

    private void initSpecialView() {
        mChangeButton = (Button) findViewById(R.id.bt_change);
        mLayoutInflater = LayoutInflater.from(this);
        mLongClickButton = (Button) findViewById(R.id.bt_long_click);
        mSpecialFlowLayout = (AutoFlowLayout) findViewById(R.id.afl_content2);
        mSpecialFlowLayout.setAdapter(new FlowAdapter(Arrays.asList(mData2)) {
            @Override
            public View getView(int position) {
                View item = mLayoutInflater.inflate(R.layout.layout_special_item, null);
                TextView tvAttrTag = (TextView) item.findViewById(R.id.tv_attr_tag);
                tvAttrTag.setText(mData2[position]);
                return item;
            }
        });
        mSpecialFlowLayout.setOnItemClickListener(new AutoFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(AutoFlowActivity.this, mData2[position], Toast.LENGTH_SHORT).show();
            }
        });
        mSpecialFlowLayout.setOnLongItemClickListener(new AutoFlowLayout.OnLongItemClickListener() {
            @Override
            public void onLongItemClick(int position, View view) {
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_delete);
                imageView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_single:{
                mNormalFlowLayout.setLineCenter(false);
                mNormalFlowLayout.setSingleLine(true);
                mNormalFlowLayout.setMaxLines(1);
                break;
            }
            case R.id.bt_multi:{
                mNormalFlowLayout.setLineCenter(false);
                mNormalFlowLayout.setSingleLine(false);
                mNormalFlowLayout.setMaxLines(2);
                break;
            }
            case R.id.bt_add:{
                mNormalFlowLayout.setLineCenter(false);
                if (count>=mData.length){
                    return;
                }
                View item = mLayoutInflater.inflate(R.layout.layout_sub_item, null);
                TextView tvAttrTag = (TextView) item.findViewById(R.id.tv_attr_tag);
                tvAttrTag.setText(mData[count]);
                mNormalFlowLayout.setSingleLine(false);
                mNormalFlowLayout.setMaxLines(Integer.MAX_VALUE);
                mNormalFlowLayout.addView(item);
                count++;
                break;
            }
            case R.id.bt_delete:{
                mNormalFlowLayout.setLineCenter(false);
                mNormalFlowLayout.deleteView();
                break;
            }

            case R.id.bt_checked:{
                mNormalFlowLayout.setLineCenter(false);
                mNormalFlowLayout.setMultiChecked(true);
                break;
            }
            case R.id.bt_center:{
                mNormalFlowLayout.setLineCenter(true);
                break;

                //specialFlow

            }
        }
    }
}
