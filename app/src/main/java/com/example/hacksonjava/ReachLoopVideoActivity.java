package com.example.hacksonjava;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.widget.VideoView;
import android.widget.MediaController;

/***リーチした時映像をループさせる***/
public class ReachLoopVideoActivity extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_video);

            //()、取得する
            VideoView videoView = findViewById(R.id.videoView);
            //Button Button = findViewById(R.id.button);

            // 動画のURIを設定
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.reach_second_video);
            videoView.setVideoURI(videoUri);

            // メディアコントローラーを追加
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            // 動画が終了したときに再生をループするためのリスナーを設定
            videoView.setOnCompletionListener(mp -> videoView.start());

            // 最初の動画の再生を開始
            videoView.start();
    }
}
