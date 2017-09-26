package com.singal.zy.library.loadsir.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by li on 2017/9/26.
 */

public class TargetContext {

    private Context context;
    private ViewGroup parentView;
    private View oldContent;
    private int childIndex;

    public TargetContext(Context context, ViewGroup parentView, View oldContent, int childIndex) {
        this.context = context;
        this.parentView = parentView;
        this.oldContent = oldContent;
        this.childIndex = childIndex;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ViewGroup getParentView() {
        return parentView;
    }

    public void setParentView(ViewGroup parentView) {
        this.parentView = parentView;
    }

    public View getOldContent() {
        return oldContent;
    }

    public void setOldContent(View oldContent) {
        this.oldContent = oldContent;
    }

    public int getChildIndex() {
        return childIndex;
    }

    public void setChildIndex(int childIndex) {
        this.childIndex = childIndex;
    }
}
