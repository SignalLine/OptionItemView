package com.singal.zy.normal_libs.web;

import android.webkit.ValueCallback;



public interface QuickCallJs {
    void quickCallJs(String method, ValueCallback<String> callback, String... params);
    void quickCallJs(String method, String... params);
    void quickCallJs(String method);
}
