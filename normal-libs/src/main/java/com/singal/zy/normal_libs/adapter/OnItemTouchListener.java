package com.singal.zy.normal_libs.adapter;

import android.view.MotionEvent;
import android.view.View;

/**
 * Project: OptionItemView
 * Author: zhouya
 *
 * item的触摸回调
 */

public interface OnItemTouchListener {
    boolean onItemTouch(YViewHolder helper, View childView, MotionEvent event, int position);
}
