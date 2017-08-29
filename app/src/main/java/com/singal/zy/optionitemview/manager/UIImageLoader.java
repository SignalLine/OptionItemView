package com.singal.zy.optionitemview.manager;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.singal.zy.normal_libs.imagepicker.loader.ImageLoader;

/**
 * Project: OptionItemView
 * Author: zhouya
 * Data: 2017/3/16
 */

public class UIImageLoader  implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        ImageSize size = new ImageSize(width, height);
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(Uri.parse("file://" + path).toString(), imageView, size);
    }

    @Override
    public void clearMemoryCache() {
    }
}
