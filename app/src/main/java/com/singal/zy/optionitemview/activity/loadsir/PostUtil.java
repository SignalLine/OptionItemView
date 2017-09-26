package com.singal.zy.optionitemview.activity.loadsir;

import android.os.Handler;

import com.singal.zy.library.loadsir.callback.Callback;
import com.singal.zy.library.loadsir.core.LoadService;

/**
 * Created by li on 2017/9/26.
 */

public class PostUtil {

    public static final int DELAY_TIME = 1000;

    public static void postCallbackDelayed(final LoadService loadService, final Class<? extends Callback> clazz) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showCallback(clazz);
            }
        }, DELAY_TIME);
    }

    public static void postSuccessDelayed(final LoadService loadService) {
        postSuccessDelayed(loadService, DELAY_TIME);
    }

    public static void postSuccessDelayed(final LoadService loadService, long delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showSuccess();
            }
        }, delay);
    }

}
