package com.singal.zy.optionitemview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.singal.zy.library.view.SwitchMultiButton;
import com.singal.zy.optionitemview.R;

public class SwitchMultiButtonActivity extends AppCompatActivity {

    private String[] tabTexts1 = {"才子1", "帅哥", "大湿", "猛将兄"};
    private String[] tabTexts4 = {"已经", "在家", "等你"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_multi_button);

        ((SwitchMultiButton) findViewById(R.id.switchmultibutton1)).setText(tabTexts1).setOnSwitchListener(onSwitchListener);
        ((SwitchMultiButton) findViewById(R.id.switchmultibutton2)).setText("点个Star", "狠心拒绝").setOnSwitchListener(onSwitchListener);
        ((SwitchMultiButton) findViewById(R.id.switchmultibutton3)).setOnSwitchListener(onSwitchListener).setSelectedTab(1);
        ((SwitchMultiButton) findViewById(R.id.switchmultibutton4)).setText(tabTexts4).setOnSwitchListener(onSwitchListener);

    }

    private SwitchMultiButton.OnSwitchListener onSwitchListener = new SwitchMultiButton.OnSwitchListener() {
        @Override
        public void onSwitch(int position, String tabText) {
            Toast.makeText(SwitchMultiButtonActivity.this, tabText, Toast.LENGTH_SHORT).show();
        }
    };
}
