package com.singal.zy.normal_libs.web;

import android.support.v4.util.ArrayMap;

/**
 *
 */

public interface JsInterfaceHolder {

    JsInterfaceHolder addJavaObjects(ArrayMap<String, Object> maps);

    JsInterfaceHolder addJavaObject(String k, Object v);

    boolean checkObject(Object v) ;

}
