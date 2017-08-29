package com.singal.zy.optionitemview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.singal.zy.normal_libs.imagepicker.ImagePicker;
import com.singal.zy.normal_libs.imagepicker.bean.ImageItem;
import com.singal.zy.normal_libs.imagepicker.ui.ImageGridActivity;
import com.singal.zy.normal_libs.imagepicker.ui.ImagePreviewActivity;
import com.singal.zy.optionitemview.R;

import java.util.ArrayList;

public class ImagePickerActivity extends AppCompatActivity {

    public static final int IMAGE_PICKER = 100;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImagePickerActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
            }
        });

        mTv = (TextView) findViewById(R.id.show);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {//返回多张照片
            if (data != null) {
                //是否发送原图
                boolean isOrig = data.getBooleanExtra(ImagePreviewActivity.ISORIGIN, false);
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

                Log.e("ImagePickerActivity", isOrig ? "发原图" : "不发原图");//若不发原图的话，需要在自己在项目中做好压缩图片算法
                for (ImageItem imageItem : images) {
                    Log.e("ImagePickerActivity", imageItem.path);
                }

                mTv.setText(images.toString());
            }
        }
    }



}
