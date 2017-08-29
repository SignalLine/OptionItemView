package com.singal.zy.optionitemview.activity.ganged;

/**
 * Created by li on 2017/7/18.
 */

public abstract class BasePresenter {

    protected ViewCallBack mViewCallBack;

    public void add(ViewCallBack viewCallBack) {
        this.mViewCallBack = viewCallBack;
    }

    public void remove() {
        this.mViewCallBack = null;
    }

    protected abstract void getData();
}
