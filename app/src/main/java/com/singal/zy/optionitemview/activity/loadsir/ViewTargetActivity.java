package com.singal.zy.optionitemview.activity.loadsir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.singal.zy.library.loadsir.callback.Callback;
import com.singal.zy.library.loadsir.core.LoadService;
import com.singal.zy.library.loadsir.core.LoadSir;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.loadsir.callback.LoadingCallback;
import com.singal.zy.optionitemview.activity.loadsir.callback.TimeoutCallback;

public class ViewTargetActivity extends AppCompatActivity {
    private LoadService loadService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_target);


        ImageView imageView = (ImageView) findViewById(R.id.iv_img);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new TimeoutCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)
                .build();
        loadService = loadSir.register(imageView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showCallback(LoadingCallback.class);
                //do retry logic...

                //callback
                PostUtil.postSuccessDelayed(loadService);
            }
        });
        PostUtil.postCallbackDelayed(loadService, TimeoutCallback.class);
    }
}
