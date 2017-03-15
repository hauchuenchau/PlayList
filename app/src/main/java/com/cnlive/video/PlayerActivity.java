package com.cnlive.video;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cnlive.libs.util.Config;
import com.cnlive.libs.video.video.base.IDataSource;

public class PlayerActivity extends AppCompatActivity {
    private static final String TAG = "PlayerActivity";

    private MyMediaPlayer mnViderPlayer;

    private String path;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);

        mnViderPlayer = (MyMediaPlayer) findViewById(R.id.mn_videoplayer);

        path = getIntent().getStringExtra("path");

        title = getIntent().getStringExtra("title");
        initPlayer();
    }

    private void initPlayer() {

        //播放视讯云视频
        mnViderPlayer.setDataSource(new IDataSource() {
            @Override
            public String getId() {
                return path;
            }

            @Override
            public String getType() {
                return Config.TYPE_VOD;
            }

            @Override
            public String getRate() {
                return Config.TYPE_PLAY_RATE_2;
            }
        }, title);

        //播放普通视频
//        mnViderPlayer.setDataSource("http://bvideo.spriteapp.cn/video/2016/0704/577a4c29e1f14_wpd.mp4", title);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mnViderPlayer.pauseVideo();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        //一定要记得销毁View
        if (mnViderPlayer != null) {
            mnViderPlayer.destroyVideo();
            mnViderPlayer = null;
        }
        super.onDestroy();
    }
}
