package com.singal.zy.normal_libs.web;

import android.view.ViewGroup;
import android.webkit.WebView;


public interface WebCreator extends ProgressManager {
    WebCreator create();

    WebView get();

    ViewGroup getGroup();
}
