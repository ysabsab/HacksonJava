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

/***リーチした時のリーチ***/
public class ReachLoopVideoActivity extends AppCompatActivity{
        private boolean isSecondVideo = false; //動画の変数

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_video);

            //()、取得する
            VideoView videoView = findViewById(R.id.videoView);
            //Button Button = findViewById(R.id.button);

            // 動画のURIを設定
            Uri videoUri1 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.reach_first_video);
            Uri videoUri2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.reach_second_video);

            // メディアコントローラーを追加
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            // 動画が終了したときのリスナーを設定
            videoView.setOnCompletionListener(mp ->
            {
                if (!isSecondVideo) {
                    // 一本目の動画が終了したら二本目の動画を再生
                    videoView.setVideoURI(videoUri2);
                    videoView.start();
                    isSecondVideo = true;
                } else {
                    // 二本目の動画をループで再生
                    videoView.start();
                }
            });

            // 最初の動画の再生を開始
            videoView.setVideoURI(videoUri1);
            videoView.start();

    }
}
