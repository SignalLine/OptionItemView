package com.singal.zy.optionitemview.activity.loadsir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.loadsir.target.FragmentA;
import com.singal.zy.optionitemview.activity.loadsir.target.FragmentB;

public class MultiFragmentActivity extends AppCompatActivity {
    private static final String TAG = "FragmentSingleActivity";
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_fragment);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragmentA).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragmentB).commit();
        getSupportFragmentManager().beginTransaction().show(fragmentA).hide(fragmentB).commit();
    }

    public void showFragmentA(View view) {
        getSupportFragmentManager().beginTransaction().show(fragmentA).hide(fragmentB).commit();
    }

    public void showFragmentB(View view) {
        getSupportFragmentManager().beginTransaction().show(fragmentB).hide(fragmentA).commit();
    }
}
