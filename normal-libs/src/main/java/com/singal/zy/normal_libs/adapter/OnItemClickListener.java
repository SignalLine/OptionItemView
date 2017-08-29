package com.singal.zy.normal_libs.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Project: OptionItemView
 * Author: zhouya
 *
 * 点击回调
 */

public interface OnItemClickListener {
    /**
     * @param helper
     * @param parent   如果是RecyclerView的话，parent为空
     * @param itemView
     * @param position
     */
    void onItemClick(YViewHolder helper, ViewGroup parent, View itemView, int position);
}
