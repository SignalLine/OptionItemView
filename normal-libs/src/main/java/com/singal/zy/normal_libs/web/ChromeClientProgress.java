package com.singal.zy.normal_libs.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 *
 */

public class ChromeClientProgress extends WebChromeClient {

    private IndicatorController mIndicatorController = null;

    public ChromeClientProgress(IndicatorController indicatorController) {
        this.mIndicatorController = indicatorController;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {

        if (mIndicatorController != null)
            mIndicatorController.progress(view, newProgress);
    }

}
