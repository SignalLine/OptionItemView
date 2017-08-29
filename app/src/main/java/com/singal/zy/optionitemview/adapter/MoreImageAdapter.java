package com.singal.zy.optionitemview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.singal.zy.normal_libs.util.BitmapHelper;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.model.MoreImageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li on 2017/4/20.
 */

public class MoreImageAdapter extends BaseAdapter {
    private Context context = null;
    private List<MoreImageBean> list = new ArrayList<>();
    private int imgId[] = null;
    private LayoutInflater inflater;

    public MoreImageAdapter(Context context, List<MoreImageBean> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    private class Holder {


        public ImageView item_img;

        public ImageView getItem_img() {
            return item_img;
        }

        public void setItem_img(ImageView item_img) {
            this.item_img = item_img;
        }
    }


    @Override
    public int getCount() {


        return list.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_item_more_image, null);
            holder = new Holder();
            holder.item_img = (ImageView) view.findViewById(R.id.item_img);

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        MoreImageBean myBitmap = list.get(i);
        if(myBitmap.getBitmap() != null){
            holder.item_img.setImageBitmap(myBitmap.getBitmap());
        }else if(myBitmap.getPath() != null){
            holder.item_img.setImageBitmap(BitmapHelper.convertToBitmap(myBitmap.getPath(),160,60));
        }

        return view;
    }
}
