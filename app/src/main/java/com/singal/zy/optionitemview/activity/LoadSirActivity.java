package com.singal.zy.optionitemview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.loadsir.ConvertorActivity;
import com.singal.zy.optionitemview.activity.loadsir.FragmentSingleActivity;
import com.singal.zy.optionitemview.activity.loadsir.MultiFragmentActivity;
import com.singal.zy.optionitemview.activity.loadsir.MultiFragmentWithViewPagerActivity;
import com.singal.zy.optionitemview.activity.loadsir.NormalActivity;
import com.singal.zy.optionitemview.activity.loadsir.PlaceholderActivity;
import com.singal.zy.optionitemview.activity.loadsir.ViewTargetActivity;

public class LoadSirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_sir);
    }

    public void inActivity(View view) {
        startActivity(new Intent(this, NormalActivity.class));
    }

    public void inActivityWithConvertor(View view) {
        startActivity(new Intent(this, ConvertorActivity.class));
    }

    public void inFragment(View view) {
        startActivity(new Intent(this, FragmentSingleActivity.class));
    }

    public void inView(View view) {
        startActivity(new Intent(this,ViewTargetActivity.class));
    }

    public void inFragmentViewSirPager(View view) {
        startActivity(new Intent(this,MultiFragmentWithViewPagerActivity.class));
    }


    public void inFragmentMutil(View view) {
        startActivity(new Intent(this,MultiFragmentActivity.class));
    }

    public void showPlaceholder(View view) {
        startActivity(new Intent(this,PlaceholderActivity.class));
    }
}
