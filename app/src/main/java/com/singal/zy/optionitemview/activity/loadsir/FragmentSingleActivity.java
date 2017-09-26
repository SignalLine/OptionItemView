package com.singal.zy.optionitemview.activity.loadsir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.loadsir.target.NormalFragment;

public class FragmentSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_single);

        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, new NormalFragment()).commit();
    }
}
