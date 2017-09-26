package com.singal.zy.library.supershapeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Edit编辑组件
 *
 * Created by li on 2017/9/26.
 */

public class SuperShapeEditText extends AppCompatEditText {

    public SuperShapeEditText(Context context) {
        super(context);
    }

    public SuperShapeEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initSuperShapeView(attrs);
    }


    public SuperShapeEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSuperShapeView(attrs);
    }


    private void initSuperShapeView(AttributeSet attrs) {
        new SuperConfig().beSuperView(attrs, this);
    }
}
