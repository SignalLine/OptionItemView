package com.singal.zy.normal_libs.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Project: OptionItemView
 * Author: zhouya
 *
 * ViewHolder的抽象类(是LQRViewHolderForAbsListView和LQRViewHolderForRecyclerView的同一父类)
 */

public class YViewHolder extends RecyclerView.ViewHolder{
    protected Context mContext;
    protected View mConvertView;
    protected SparseArray<View> mViews;
    protected int mMyPosition;//LQRViewHolderForAbsListView专用（另一个自带getPosition方法）

    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;
    protected OnItemTouchListener mOnItemTouchListener;

    public YViewHolder(View itemView){
        super(itemView);
    }

    /**
     * 根据id得到布局中的View(使用SparseArray保管，提高效率)
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 得到当前item对应的View
     */
    public View getConvertView() {
        return mConvertView;
    }

    public int getMyPosition() {
        return mMyPosition;
    }

    public void setMyPosition(int myPosition) {
        mMyPosition = myPosition;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public OnItemTouchListener getOnItemTouchListener() {
        return mOnItemTouchListener;
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        mOnItemTouchListener = onItemTouchListener;
    }

    //可能会用到的操作控件方法----------------------------start

    /**
     * 设置TextView文字 并返回this
     * @param viewId
     * @param text
     * @return
     */
    public YViewHolder setText(int viewId,String text){
        TextView tv = getView(viewId);
        tv.setText(text);

        return this;
    }

    /**
     * 设置TextView文字颜色 并返回this
     * @param viewId
     * @param colorId
     * @return
     */
    public YViewHolder setTextColor(int viewId,int colorId){
        TextView tv = getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(colorId));

        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public YViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public YViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public YViewHolder setImageFileResource(int viewId, String path) {
        ImageView iv = getView(viewId);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置背景颜色，并返回this
     */
    public YViewHolder setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(mContext.getResources().getColor(colorId));
        return this;
    }


    /**
     * 设置背景资源，并返回this
     */
    public YViewHolder setBackgrounResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置显隐，并返回this
     */
    public YViewHolder setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 设置是否可用，并返回this
     */
    public YViewHolder setEnabled(int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    /**
     * 设置是否可获取焦点，并返回this
     */
    public YViewHolder setFocusable(int viewId, boolean focusable) {
        View view = getView(viewId);
        view.setFocusable(focusable);
        return this;
    }
    //可能会用到的操作控件方法-----------------------------end

}
