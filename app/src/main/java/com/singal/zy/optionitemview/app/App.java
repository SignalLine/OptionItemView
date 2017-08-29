package com.singal.zy.optionitemview.app;

import android.app.Application;

import com.apkfuns.jsbridge.JsBridgeConfig;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.singal.zy.normal_libs.emoji.LQRUIKit;
import com.singal.zy.normal_libs.imagepicker.ImagePicker;
import com.singal.zy.normal_libs.imagepicker.view.CropImageView;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.manager.UIImageLoader;
import com.singal.zy.optionitemview.model.js.MultiLayerModule;
import com.singal.zy.optionitemview.model.js.MultiLayerModule2;
import com.singal.zy.optionitemview.model.js.MultiLayerModule3;
import com.singal.zy.optionitemview.model.js.NativeModule;
import com.singal.zy.optionitemview.model.js.ServiceModule;
import com.singal.zy.optionitemview.model.js.StaticModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Project: OptionItemView
 * Author: zhouya
 * Data: 2017/3/16
 */

public class App extends Application {

    private static App instance;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        initUniversalImageLoader();
        initImagePicker();

        LQRUIKit.init(getApplicationContext());


        //webview
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        JsBridgeConfig.getSetting().setProtocol("MyBridge").registerDefaultModule(
                ServiceModule.class, StaticModule.class, NativeModule.class,
                MultiLayerModule.class, MultiLayerModule2.class, MultiLayerModule3.class
        ).debugMode(true);
    }

    public static App getInstance() {
        return instance;
    }

    private void initUniversalImageLoader() {
        //初始化ImageLoader
        ImageLoader.getInstance().init(
                ImageLoaderConfiguration.createDefault(getApplicationContext()));
    }

    /**
     * 初始化仿微信控件ImagePicker
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new UIImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    public static DisplayImageOptions options = new DisplayImageOptions.Builder()//
            .showImageOnLoading(R.mipmap.ic_holding)         //设置图片在下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.ic_error)       //设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.ic_error)            //设置图片加载/解码过程中错误时候显示的图片
            .cacheInMemory(true)                                //设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)                                  //设置下载的图片是否缓存在SD卡中
            .build();
}
