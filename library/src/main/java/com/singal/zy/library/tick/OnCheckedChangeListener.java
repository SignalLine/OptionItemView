package com.singal.zy.library.tick;

/**
 * @author li
 *         Create on 2017/11/9.
 * @Description
 */

public interface OnCheckedChangeListener {

    /**
     * 点击状态改变监听
     *
     * @param tickView
     * @param isCheck
     */
    void onCheckedChanged(TickView tickView,boolean isCheck);

}
