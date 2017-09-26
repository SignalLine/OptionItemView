package com.singal.zy.library.loadsir.callback;

import android.content.Context;
import android.view.View;

/**
 * Created by li on 2017/9/26.
 */

public class SuccessCallback extends Callback {

    public SuccessCallback(View view, Context context, OnReloadListener onReloadListener) {
        super(view, context, onReloadListener);
    }

    @Override
    protected int onCreateView() {
        return 0;
    }
}
