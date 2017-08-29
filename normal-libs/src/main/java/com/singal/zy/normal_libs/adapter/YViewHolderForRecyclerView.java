package com.singal.zy.normal_libs.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * Project: OptionItemView
 * Author: zhouya
 *
 *
 * RecyclerView通用的ViewHolder
 */

public class YViewHolderForRecyclerView extends YViewHolder{

    public YViewHolderForRecyclerView(Context context,View itemView){
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();

        mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(YViewHolderForRecyclerView.this,null,view,getPosition());
                }
            }
        });

        mConvertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    return mOnItemLongClickListener.onItemLongClick(YViewHolderForRecyclerView.this, null, v, getPosition());
                }
                return false;
            }
        });

        mConvertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mOnItemTouchListener != null) {
                    return mOnItemTouchListener.onItemTouch(YViewHolderForRecyclerView.this, v, event, getPosition());
                }
                return false;
            }
        });
    }

}
