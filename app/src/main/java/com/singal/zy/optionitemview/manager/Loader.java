package com.singal.zy.optionitemview.manager;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.singal.zy.normal_libs.imageshowpickerview.ImageLoader;

/**
 * Created by li on 2017/4/26.
 */

public class Loader extends ImageLoader {

    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

    @Override
    public void displayImage(Context context, @DrawableRes Integer resId, ImageView imageView) {
        imageView.setImageResource(resId);
    }

    @Override
    public ImageView createImageView(Context context) {
        return super.createImageView(context);
    }
}
