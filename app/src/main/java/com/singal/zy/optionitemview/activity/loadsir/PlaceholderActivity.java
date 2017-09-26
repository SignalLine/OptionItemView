package com.singal.zy.optionitemview.activity.loadsir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.singal.zy.library.loadsir.callback.Callback;
import com.singal.zy.library.loadsir.core.LoadService;
import com.singal.zy.library.loadsir.core.LoadSir;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.loadsir.callback.PlaceholderCallback;

public class PlaceholderActivity extends AppCompatActivity {
    private LoadService loadService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);

        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new PlaceholderCallback())
                .setDefaultCallback(PlaceholderCallback.class)
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                //do retry logic...
            }
        });
        PostUtil.postSuccessDelayed(loadService, 1000);
    }
}
