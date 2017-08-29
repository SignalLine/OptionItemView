package com.singal.zy.optionitemview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.singal.zy.normal_libs.imageshowpickerview.ImageShowPickerBean;
import com.singal.zy.normal_libs.imageshowpickerview.ImageShowPickerListener;
import com.singal.zy.normal_libs.imageshowpickerview.ImageShowPickerView;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.manager.Loader;
import com.singal.zy.optionitemview.model.ImageBean;

import java.util.ArrayList;
import java.util.List;

public class ImageShowPickerViewActivity extends AppCompatActivity {

    ImageShowPickerView mPickerView;
    private List<ImageBean> mBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show_picker_view);

        mPickerView = (ImageShowPickerView) findViewById(R.id.image_show_picker_view);

        mBeanList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mBeanList.add(new ImageBean("http://img2.imgtn.bdimg.com/it/u=819201812,3553302270&fm=214&gp=0.jpg"));
        }

        mPickerView.addData(mBeanList);
        mPickerView.setShowAnim(true);
        mPickerView.setImageLoaderInterface(new Loader());
        mPickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                Toast.makeText(ImageShowPickerViewActivity.this, "remainNum" + remainNum, Toast.LENGTH_SHORT).show();
                ImageBean bean = new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg");
                mBeanList.add(bean);
                mPickerView.addData(bean);
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {
                Toast.makeText(ImageShowPickerViewActivity.this, list.size() + "========" + position + "remainNum" + remainNum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {
                mBeanList.remove(position);
                Toast.makeText(ImageShowPickerViewActivity.this, "delOnClickListenerremainNum" + remainNum, Toast.LENGTH_SHORT).show();
            }
        });

        mPickerView.show();
    }
}
