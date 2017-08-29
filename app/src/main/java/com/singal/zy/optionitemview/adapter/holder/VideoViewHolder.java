package com.singal.zy.optionitemview.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chatone.videoplayer.UVideoPlayer;
import com.chatone.videoplayer.UVideoPlayerController;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.model.Video;

/**
 * holder
 *
 * Created by li on 2017/6/7.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder {
    private UVideoPlayerController mController;
    private UVideoPlayer mVideoPlayer;

    public VideoViewHolder(View itemView) {
        super(itemView);
        mVideoPlayer = (UVideoPlayer) itemView.findViewById(R.id.u_video_player);
    }

    public void setController(UVideoPlayerController controller) {
        mController = controller;
    }

    public void bindData(Video video) {
        mVideoPlayer.setController(mController);
        mVideoPlayer.setUp(video.getVideoUrl(), null);
        mController.setTitle(video.getTitle());
        mController.setImage(video.getImageUrl());
    }
}
