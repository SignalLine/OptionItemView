package com.singal.zy.normal_libs.web;

import android.webkit.WebView;

/**
 * 自定义WebSettings接口 继承 link{android.webkit.WebSettings}
 *
 * Created by li on 2017/6/15.
 */

public interface WebSettings <T extends android.webkit.WebSettings> {

    WebSettings toSetting(WebView webView);

    T getWebSettings();
}
