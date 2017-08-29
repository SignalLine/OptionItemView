package com.singal.zy.optionitemview.manager;

import android.net.Uri;
import android.widget.ImageView;

import com.singal.zy.optionitemview.app.App;

/**
 * Project: OptionItemView
 * Author: zhouya
 *
 * 图片加载管理  对对universalimage工具的包装
 */

public class ImageLoaderManager {
    public static void LoadNetImage(String imgUrl, ImageView imageView) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(imgUrl, imageView, App.options);
    }

    public static void LoadLocalImage(String path, ImageView imageView) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(Uri.parse("file://" + path).toString(), imageView, App.options);
    }
}
