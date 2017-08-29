package com.singal.zy.normal_libs.web;

import android.view.View;
import android.webkit.WebChromeClient;

/**
 *
 */

public interface IVideo {


    void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);


    void onHideCustomView();


    boolean isVideoState();

}
