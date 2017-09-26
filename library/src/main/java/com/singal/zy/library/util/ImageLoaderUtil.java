package com.singal.zy.library.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 图片加载
 *
 * Created by li on 2017/8/30.
 */

public class ImageLoaderUtil {

    public static void load(Context context, Object path, ImageView imageView){
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .crossFade()
                .into(imageView);
    }

}
