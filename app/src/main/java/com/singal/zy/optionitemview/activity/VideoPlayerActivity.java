package com.singal.zy.optionitemview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chatone.videoplayer.UVideoPlayer;
import com.chatone.videoplayer.UVideoPlayerController;
import com.chatone.videoplayer.UVideoPlayerManager;
import com.singal.zy.optionitemview.R;

public class VideoPlayerActivity extends AppCompatActivity {

    private UVideoPlayer mVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        init();

    }

    private void init() {
        mVideoPlayer = (UVideoPlayer) findViewById(R.id.u_video_player);
        mVideoPlayer.setPlayerType(UVideoPlayer.PLAYER_TYPE_IJK);
        mVideoPlayer.setUp("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4",null);
        UVideoPlayerController controller = new UVideoPlayerController(this);
        controller.setTitle("在办公室开澡堂！老板还点赞？");
        controller.setImage("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-30-43.jpg");
        mVideoPlayer.setController(controller);
    }

    @Override
    public void onBackPressed() {
        if (UVideoPlayerManager.instance().onBackPressd()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        UVideoPlayerManager.instance().releaseUVideoPlayer();
        super.onStop();
    }

    public void enterTinyWindow(View view) {
        if (mVideoPlayer.isPlaying()
                || mVideoPlayer.isBufferingPlaying()
                || mVideoPlayer.isPaused()
                || mVideoPlayer.isBufferingPaused()) {
            mVideoPlayer.enterTinyWindow();
        } else {
            Toast.makeText(this, "要播放后才能进入小窗口", Toast.LENGTH_SHORT).show();
        }
    }

    public void showVideoList(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }
}
