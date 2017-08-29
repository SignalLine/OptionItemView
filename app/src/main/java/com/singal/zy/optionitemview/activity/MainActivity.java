package com.singal.zy.optionitemview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.singal.zy.library.OptionItemView;
import com.singal.zy.optionitemview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        OptionItemView optionItemView = (OptionItemView) findViewById(R.id.optionItemView);
        
        optionItemView.setOnOptionItemClickListener(new OptionItemView.OnOptionItemClickListener() {
            @Override
            public void leftOnClick() {
                Toast.makeText(MainActivity.this, "点击了左边", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void centerOnClick() {
                Toast.makeText(MainActivity.this, "点击了中间", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightOnClick() {
                Toast.makeText(MainActivity.this, "点击了右边", Toast.LENGTH_SHORT).show();
            }
        });

        optionItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "整体点击", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
