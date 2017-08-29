package com.singal.zy.normal_libs.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Project: OptionItemView
 * Author: zhouya
 *
 *
 * ListView和GridView通用的ViewHolder
 */

public class YViewHolderForAbsListView extends YViewHolder{

    public YViewHolderForAbsListView(Context context, int defaultLayoutId, final ViewGroup parent){
        super(parent);
        mContext = context;
        mViews = new SparseArray<>();
        mConvertView = View.inflate(context,defaultLayoutId,null);
        mConvertView.setTag(this);

        mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(YViewHolderForAbsListView.this, parent, v, getPosition());
                }
            }
        });

        mConvertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    return mOnItemLongClickListener.onItemLongClick(YViewHolderForAbsListView.this, parent, v, getPosition());
                }
                return false;
            }
        });

        mConvertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mOnItemTouchListener != null) {
                    return mOnItemTouchListener.onItemTouch(YViewHolderForAbsListView.this, v, event, getPosition());
                }
                return false;
            }
        });
    }

    /**
     *
     * 得到ViewHolder
     * <p>
     * 如果之前对应的convertView没有Viewholder就创建一个新的，否则直接从convertView中获取。
     *
     * @param context
     * @param defaultLayoutId
     * @param pos
     * @param convertView
     * @param viewGroup
     * @return
     */
    public static YViewHolderForAbsListView get(Context context,int defaultLayoutId,int pos,View convertView,ViewGroup viewGroup){
        YViewHolderForAbsListView viewHolder;

        if(convertView == null){
            viewHolder = new YViewHolderForAbsListView(context,defaultLayoutId,viewGroup);
        }else {
            viewHolder = (YViewHolderForAbsListView) convertView.getTag();
        }

        viewHolder.setMyPosition(pos);

        return viewHolder;
    }
}
