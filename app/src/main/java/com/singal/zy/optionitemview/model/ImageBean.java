package com.singal.zy.optionitemview.model;

import com.singal.zy.normal_libs.imageshowpickerview.ImageShowPickerBean;

/**
 * Created by li on 2017/4/26.
 */

public class ImageBean extends ImageShowPickerBean {

    public ImageBean(String url) {
        this.url = url;
    }

    public ImageBean(int resId) {
        this.resId = resId;
    }

    private String url;

    private int resId;


    @Override
    public String setImageShowPickerUrl() {
        return url;
    }

    @Override
    public int setImageShowPickerDelRes() {
        return resId;
    }

}
