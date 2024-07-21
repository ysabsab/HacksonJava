package com.example.hacksonjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntoReachLoopVideoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_only_video);

        //()、取得する
        VideoView videoView = findViewById(R.id.videoView);
        //Button Button = findViewById(R.id.button);

        // 動画のURIを設定
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.reach_first_video);
        videoView.setVideoURI(videoUri);

        // メディアコントローラーを追加
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // 動画が終了したときに再生をループするためのリスナーを設定
        videoView.setOnCompletionListener(mp -> videoView.start());

        // 動画が終了したときに再生をループするためのリスナーを設定
        videoView.setOnCompletionListener(mp -> videoView.start());

        // 動画の再生を開始
        videoView.start();

        // 動画再生が終了したときのリスナーを設定
        videoView.setOnCompletionListener(mp -> {
            // 動画が終了したらMainActivityに遷移
            Intent intent = new Intent(IntoReachLoopVideoActivity.this, ReachLoopVideoActivity.class);
            startActivity(intent);
            // IntoReachVideoActivityを終了する
            finish();
        });
    }
}
