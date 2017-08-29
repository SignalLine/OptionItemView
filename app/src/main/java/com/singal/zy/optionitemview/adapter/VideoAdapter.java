package com.singal.zy.optionitemview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatone.videoplayer.UVideoPlayerController;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.adapter.holder.VideoViewHolder;
import com.singal.zy.optionitemview.model.Video;

import java.util.List;

/**
 * Created by li on 2017/6/7.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder>{

    private Context mContext;
    private List<Video> mVideoList;

    public VideoAdapter(Context context, List<Video> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_video, parent, false);
        VideoViewHolder holder = new VideoViewHolder(itemView);
        UVideoPlayerController controller = new UVideoPlayerController(mContext);
        holder.setController(controller);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = mVideoList.get(position);
        holder.bindData(video);
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }
}
