package com.singal.zy.optionitemview.model;

import android.graphics.Bitmap;

/**
 *
 * 用于存放所拍照的多张bean
 *
 * Created by li on 2017/4/20.
 */

public class MoreImageBean {

    String path;
    Bitmap bitmap;

    public MoreImageBean(String path, Bitmap bitmap) {
        this.path = path;
        this.bitmap = bitmap;
    }

    public MoreImageBean(String path) {
        this.path = path;
    }

    public MoreImageBean() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
