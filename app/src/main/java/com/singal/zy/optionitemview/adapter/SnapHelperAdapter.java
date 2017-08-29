package com.singal.zy.optionitemview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.singal.zy.optionitemview.R;

import java.util.ArrayList;

/**
 *
 * SnapHelper这个辅助类，用于辅助RecyclerView在滚动结束时将Item对齐到某个位置。
 * 特别是列表横向滑动时，很多时候不会让列表滑到任意位置，而是会有一定的规则限制，
 * 这时候就可以通过SnapHelper来定义对齐规则了
 *
 * Created by li on 2017/8/29.
 */

public class SnapHelperAdapter extends RecyclerView.Adapter<SnapHelperAdapter.GalleryViewHoler> {
    LayoutInflater mInflater;
    ArrayList<String> mData;
    int[] imgs = new int[]{R.drawable.jdzz, R.drawable.ccdzz, R.drawable.dfh, R.drawable.dlzs, R.drawable.sgkptt, R.drawable.ttxss, R.drawable.zmq, R.drawable.zzhx};


    public SnapHelperAdapter(Context context, ArrayList<String> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;

    }

    @Override
    public GalleryViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_gallery_item, parent, false);

        return new GalleryViewHoler(view);
    }

    @Override
    public void onBindViewHolder(final GalleryViewHoler holder, int position) {
        holder.mImageView.setImageResource(imgs[position % 8]);
        holder.mTextView.setText(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class GalleryViewHoler extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public GalleryViewHoler(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mTextView = (TextView) itemView.findViewById(R.id.tv_num);
        }
    }
}

