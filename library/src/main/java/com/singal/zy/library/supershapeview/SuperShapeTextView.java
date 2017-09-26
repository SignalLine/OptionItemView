package com.singal.zy.library.supershapeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 文本框
 *
 * Created by li on 2017/9/26.
 */

public class SuperShapeTextView extends AppCompatTextView{
    public SuperShapeTextView(Context context) {
        super(context);
    }

    public SuperShapeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initSuperShapeView(attrs);
    }


    public SuperShapeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSuperShapeView(attrs);
    }


    private void initSuperShapeView(AttributeSet attrs) {
        new SuperConfig().beSuperView(attrs, this);
    }
}
