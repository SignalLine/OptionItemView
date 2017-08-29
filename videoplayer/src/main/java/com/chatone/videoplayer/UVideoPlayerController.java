package com.chatone.videoplayer;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * 播放器控制器
 *
 * Created by li on 2017/6/7.
 */

public class UVideoPlayerController extends FrameLayout implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener{

    private Context mContext;
    private UVideoPlayerControl mUVideoPlayer;
    private ImageView mImage;
    private ImageView mCenterStart;
    private LinearLayout mTop;
    private ImageView mBack;
    private TextView mTitle;
    private LinearLayout mBottom;
    private ImageView mRestartPause;
    private TextView mPosition;
    private TextView mDuration;
    private SeekBar mSeek;
    private ImageView mFullScreen;
    private LinearLayout mLoading;
    private TextView mLoadText;
    private LinearLayout mError;
    private TextView mRetry;
    private LinearLayout mCompleted;
    private TextView mReplay;
    private TextView mShare;

    private Timer mUpdateProgressTimer;
    private TimerTask mUpdateProgressTimerTask;
    private boolean topBottomVisible;
    private CountDownTimer mDismissTopBottomCountDownTimer;

    public UVideoPlayerController(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.u_video_player_controller, this, true);
        mCenterStart = (ImageView) findViewById(R.id.center_start);
        mImage = (ImageView) findViewById(R.id.image);

        mTop = (LinearLayout) findViewById(R.id.top);
        mBack = (ImageView) findViewById(R.id.back);
        mTitle = (TextView) findViewById(R.id.title);

        mBottom = (LinearLayout) findViewById(R.id.bottom);
        mRestartPause = (ImageView) findViewById(R.id.restart_or_pause);
        mPosition = (TextView) findViewById(R.id.position);
        mDuration = (TextView) findViewById(R.id.duration);
        mSeek = (SeekBar) findViewById(R.id.seek);
        mFullScreen = (ImageView) findViewById(R.id.full_screen);

        mLoading = (LinearLayout) findViewById(R.id.loading);
        mLoadText = (TextView) findViewById(R.id.load_text);

        mError = (LinearLayout) findViewById(R.id.error);
        mRetry = (TextView) findViewById(R.id.retry);

        mCompleted = (LinearLayout) findViewById(R.id.completed);
        mReplay = (TextView) findViewById(R.id.replay);
        mShare = (TextView) findViewById(R.id.share);

        mCenterStart.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mRestartPause.setOnClickListener(this);
        mFullScreen.setOnClickListener(this);
        mRetry.setOnClickListener(this);
        mReplay.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mSeek.setOnSeekBarChangeListener(this);
        this.setOnClickListener(this);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setImage(String imageUrl) {
        Glide.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.img_default)
                .crossFade()
                .into(mImage);
    }

    public void setImage(@DrawableRes int resId) {
        mImage.setImageResource(resId);
    }

    public void setUVideoPlayer(UVideoPlayerControl playerControl){
        mUVideoPlayer = playerControl;
        if(mUVideoPlayer.isIdle()){
            mBack.setVisibility(View.GONE);
            mTop.setVisibility(View.VISIBLE);
            mBottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mCenterStart) {
            if (mUVideoPlayer.isIdle()) {
                mUVideoPlayer.start();
            }
        } else if (v == mBack) {
            if (mUVideoPlayer.isFullScreen()) {
                mUVideoPlayer.exitFullScreen();
            } else if (mUVideoPlayer.isTinyWindow()) {
                mUVideoPlayer.exitTinyWindow();
            }
        } else if (v == mRestartPause) {
            if (mUVideoPlayer.isPlaying() || mUVideoPlayer.isBufferingPlaying()) {
                mUVideoPlayer.pause();
            } else if (mUVideoPlayer.isPaused() || mUVideoPlayer.isBufferingPaused()) {
                mUVideoPlayer.restart();
            }
        } else if (v == mFullScreen) {
            if (mUVideoPlayer.isNormal()) {
                mUVideoPlayer.enterFullScreen();
            } else if (mUVideoPlayer.isFullScreen()) {
                mUVideoPlayer.exitFullScreen();
            }
        } else if (v == mRetry) {
            mUVideoPlayer.release();
            mUVideoPlayer.start();
        } else if (v == mReplay) {
            mRetry.performClick();
        } else if (v == mShare) {
            Toast.makeText(mContext, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == this) {
            if (mUVideoPlayer.isPlaying()
                    || mUVideoPlayer.isPaused()
                    || mUVideoPlayer.isBufferingPlaying()
                    || mUVideoPlayer.isBufferingPaused()) {
                setTopBottomVisible(!topBottomVisible);
            }
        }
    }

    private void setTopBottomVisible(boolean visible) {
        mTop.setVisibility(visible ? View.VISIBLE : View.GONE);
        mBottom.setVisibility(visible ? View.VISIBLE : View.GONE);
        topBottomVisible = visible;
        if (visible) {
            if (!mUVideoPlayer.isPaused() && !mUVideoPlayer.isBufferingPaused()) {
                startDismissTopBottomTimer();
            }
        } else {
            cancelDismissTopBottomTimer();
        }
    }

    public void setControllerState(int playerState, int playState) {
        switch (playerState) {
            case UVideoPlayer.PLAYER_NORMAL:
                mBack.setVisibility(View.GONE);
                mFullScreen.setVisibility(View.VISIBLE);
                mFullScreen.setImageResource(R.drawable.ic_player_enlarge);
                break;
            case UVideoPlayer.PLAYER_FULL_SCREEN:
                mBack.setVisibility(View.VISIBLE);
                mFullScreen.setVisibility(View.VISIBLE);
                mFullScreen.setImageResource(R.drawable.ic_player_shrink);
                break;
            case UVideoPlayer.PLAYER_TINY_WINDOW:
                mFullScreen.setVisibility(View.GONE);
                break;
        }
        switch (playState) {
            case UVideoPlayer.STATE_IDLE:
                break;
            case UVideoPlayer.STATE_PREPARING:
                // 只显示准备中动画，其他不显示
                mImage.setVisibility(View.GONE);
                mLoading.setVisibility(View.VISIBLE);
                mLoadText.setText("正在准备...");
                mError.setVisibility(View.GONE);
                mCompleted.setVisibility(View.GONE);
                mTop.setVisibility(View.GONE);
                mCenterStart.setVisibility(View.GONE);
                break;
            case UVideoPlayer.STATE_PREPARED:
                startUpdateProgressTimer();
                break;
            case UVideoPlayer.STATE_PLAYING:
                mLoading.setVisibility(View.GONE);
                mRestartPause.setImageResource(R.drawable.ic_player_pause);
                startDismissTopBottomTimer();
                break;
            case UVideoPlayer.STATE_PAUSED:
                mLoading.setVisibility(View.GONE);
                mRestartPause.setImageResource(R.drawable.ic_player_start);
                cancelDismissTopBottomTimer();
                break;
            case UVideoPlayer.STATE_BUFFERING_PLAYING:
                mLoading.setVisibility(View.VISIBLE);
                mRestartPause.setImageResource(R.drawable.ic_player_pause);
                mLoadText.setText("正在缓冲...");
                startDismissTopBottomTimer();
                break;
            case UVideoPlayer.STATE_BUFFERING_PAUSED:
                mLoading.setVisibility(View.VISIBLE);
                mRestartPause.setImageResource(R.drawable.ic_player_start);
                mLoadText.setText("正在缓冲...");
                cancelDismissTopBottomTimer();
                break;
            case UVideoPlayer.STATE_COMPLETED:
                cancelUpdateProgressTimer();
                setTopBottomVisible(false);
                mImage.setVisibility(View.VISIBLE);
                mCompleted.setVisibility(View.VISIBLE);
                if (mUVideoPlayer.isFullScreen()) {
                    mUVideoPlayer.exitFullScreen();
                }
                if (mUVideoPlayer.isTinyWindow()) {
                    mUVideoPlayer.exitTinyWindow();
                }
                break;
            case UVideoPlayer.STATE_ERROR:
                cancelUpdateProgressTimer();
                setTopBottomVisible(false);
                mTop.setVisibility(View.VISIBLE);
                mError.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void startUpdateProgressTimer() {
        cancelUpdateProgressTimer();
        if (mUpdateProgressTimer == null) {
            mUpdateProgressTimer = new Timer();
        }
        if (mUpdateProgressTimerTask == null) {
            mUpdateProgressTimerTask = new TimerTask() {
                @Override
                public void run() {
                    UVideoPlayerController.this.post(new Runnable() {
                        @Override
                        public void run() {
                            updateProgress();
                        }
                    });
                }
            };
        }
        mUpdateProgressTimer.schedule(mUpdateProgressTimerTask, 0, 300);
    }

    private void updateProgress() {
        long position = mUVideoPlayer.getCurrentPosition();
        long duration = mUVideoPlayer.getDuration();
        int bufferPercentage = mUVideoPlayer.getBufferPercentage();
        mSeek.setSecondaryProgress(bufferPercentage);
        int progress = (int) (100f * position / duration);
        mSeek.setProgress(progress);
        mPosition.setText(PlayerUtil.formatTime(position));
        mDuration.setText(PlayerUtil.formatTime(duration));
    }

    private void cancelUpdateProgressTimer() {
        if (mUpdateProgressTimer != null) {
            mUpdateProgressTimer.cancel();
            mUpdateProgressTimer = null;
        }
        if (mUpdateProgressTimerTask != null) {
            mUpdateProgressTimerTask.cancel();
            mUpdateProgressTimerTask = null;
        }
    }

    private void startDismissTopBottomTimer() {
        cancelDismissTopBottomTimer();
        if (mDismissTopBottomCountDownTimer == null) {
            mDismissTopBottomCountDownTimer = new CountDownTimer(8000, 8000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    setTopBottomVisible(false);
                }
            };
        }
        mDismissTopBottomCountDownTimer.start();
    }


    private void cancelDismissTopBottomTimer() {
        if (mDismissTopBottomCountDownTimer != null) {
            mDismissTopBottomCountDownTimer.cancel();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        cancelDismissTopBottomTimer();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mUVideoPlayer.isBufferingPaused() || mUVideoPlayer.isPaused()) {
            mUVideoPlayer.restart();
        }
        int position = (int) (mUVideoPlayer.getDuration() * seekBar.getProgress() / 100f);
        mUVideoPlayer.seekTo(position);
        startDismissTopBottomTimer();
    }

    /**
     * 控制器恢复到初始状态
     */
    public void reset() {
        topBottomVisible = false;
        cancelUpdateProgressTimer();
        cancelDismissTopBottomTimer();
        mSeek.setProgress(0);
        mSeek.setSecondaryProgress(0);

        mCenterStart.setVisibility(View.VISIBLE);
        mImage.setVisibility(View.VISIBLE);

        mBottom.setVisibility(View.GONE);
        mFullScreen.setImageResource(R.drawable.ic_player_enlarge);

        mTop.setVisibility(View.VISIBLE);
        mBack.setVisibility(View.GONE);

        mLoading.setVisibility(View.GONE);
        mError.setVisibility(View.GONE);
        mCompleted.setVisibility(View.GONE);
    }

}
