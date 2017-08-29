package com.singal.zy.optionitemview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.singal.zy.normal_libs.emoji.EmoticonPickerView;
import com.singal.zy.normal_libs.emoji.IEmoticonSelectedListener;
import com.singal.zy.optionitemview.R;

public class EmojiActivity extends AppCompatActivity implements IEmoticonSelectedListener {

    private EmoticonPickerView mEpv;
    private EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);


        messageEditText = (EditText) findViewById(R.id.messageEditText);
        mEpv = (EmoticonPickerView) findViewById(R.id.epv);
        mEpv.setWithSticker(true);//开启贴图功能
        mEpv.show(this);//显示表情视图并设置监听
        mEpv.attachEditText(messageEditText);//把EditText交给EmoticonPickerView控制
    }


    /**
     * 表情选择的监听
     * *************** IEmojiSelectedListener ***************
     */
    @Override
    public void onEmojiSelected(String key) {
    }

    //选择贴图
    @Override
    public void onStickerSelected(String catalog, String chartlet) {
        Log.i("LQR", "onStickerSelected, catalog =" + catalog
                + ", chartlet =" + chartlet);
        Toast.makeText(EmojiActivity.this, chartlet + "---表情", Toast.LENGTH_SHORT).show();
    }
}
