package com.singal.zy.optionitemview.activity.loadsir;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.singal.zy.library.loadsir.callback.Callback;
import com.singal.zy.library.loadsir.core.LoadService;
import com.singal.zy.library.loadsir.core.LoadSir;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.loadsir.callback.EmptyCallback;
import com.singal.zy.optionitemview.activity.loadsir.callback.LoadingCallback;

public class NormalActivity extends AppCompatActivity {
    private LoadService loadService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        // Your can change the callback on sub thread directly.
        loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // Your can change the status out of Main thread.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadService.showCallback(LoadingCallback.class);
                        //do retry logic...
                        SystemClock.sleep(500);
                        //callback
                        loadService.showSuccess();
                    }
                }).start();
            }
        });
        PostUtil.postCallbackDelayed(loadService,EmptyCallback.class);
    }
}
