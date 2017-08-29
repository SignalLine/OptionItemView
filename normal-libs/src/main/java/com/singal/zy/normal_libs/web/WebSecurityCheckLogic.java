package com.singal.zy.normal_libs.web;


import android.support.v4.util.ArrayMap;
import android.webkit.WebView;

/**
 *
 *
 * Created by li on 2017/6/15.
 */

public interface WebSecurityCheckLogic {

    void dealHoneyComb(WebView view);

    void dealJsInterface(ArrayMap<String,Object> objects, AgentWeb.SecurityType securityType);

}
