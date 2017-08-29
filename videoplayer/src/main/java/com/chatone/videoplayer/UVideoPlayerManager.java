package com.chatone.videoplayer;

/**
 *
 * 视频播放器管理器
 *
 * Created by li on 2017/6/7.
 */

public class UVideoPlayerManager {
    private UVideoPlayer mVideoPlayer;

    private UVideoPlayerManager() {
    }

    private static UVideoPlayerManager sInstance;

    public static synchronized UVideoPlayerManager instance() {
        if (sInstance == null) {
            sInstance = new UVideoPlayerManager();
        }
        return sInstance;
    }

    public void setCurrentUVideoPlayer(UVideoPlayer videoPlayer) {
        mVideoPlayer = videoPlayer;
    }

    public void releaseUVideoPlayer() {
        if (mVideoPlayer != null) {
            mVideoPlayer.release();
            mVideoPlayer = null;
        }
    }

    public boolean onBackPressd() {
        if (mVideoPlayer != null) {
            if (mVideoPlayer.isFullScreen()) {
                return mVideoPlayer.exitFullScreen();
            } else if (mVideoPlayer.isTinyWindow()) {
                return mVideoPlayer.exitTinyWindow();
            } else {
                mVideoPlayer.release();
                return false;
            }
        }
        return false;
    }
}
