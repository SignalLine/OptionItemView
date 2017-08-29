package com.singal.zy.normal_libs.web;

import android.view.KeyEvent;

/**
 *
 */

public interface IEventHandler {

    boolean onKeyDown(int keyCode, KeyEvent event);


    boolean back();
}
