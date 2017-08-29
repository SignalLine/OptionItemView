package com.singal.zy.normal_libs.web;

import android.webkit.WebView;

/**
 *
 */

public interface IndicatorController {

    void progress(WebView v, int newProgress);

    BaseProgressSpec offerIndicator();
}
