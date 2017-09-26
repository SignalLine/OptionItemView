package com.singal.zy.optionitemview.activity.loadsir.callback;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.singal.zy.library.loadsir.callback.Callback;
import com.singal.zy.optionitemview.R;


/**
 */

public class TimeoutCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.loadsir_layout_timeout;
    }

    @Override
    protected boolean onRetry(Context context, View view) {
        Toast.makeText(context.getApplicationContext(),"Connecting to the network again!",Toast.LENGTH_SHORT).show();
        return false;
    }
}
