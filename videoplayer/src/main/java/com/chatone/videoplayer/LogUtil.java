package com.chatone.videoplayer;

import android.util.Log;

/**
 * Created by li on 2017/5/4.
 * log工具.
 */
public class LogUtil {

    private static final String TAG = "VideoPlayer";

    public static void d(String message) {
        Log.d(TAG, message);
    }

    public static void e(String message, Throwable throwable) {
        Log.e(TAG, message, throwable);
    }
}
