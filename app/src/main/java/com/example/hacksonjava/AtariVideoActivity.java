package com.example.hacksonjava;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

/***main動画から遷移してきて、動画が終わったらmainに戻す***/
public class AtariVideoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //画面を挿入
        VideoView videoView = findViewById(R.id.videoView);

        //動画のurlを挿入
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.atari_video);
        videoView.setVideoURI(videoUri);

        //メディアコントロール
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //動画スタート
        videoView.start();

        // 動画再生が終了したときのリスナーを設定
        videoView.setOnCompletionListener(mp -> {
            // 動画が終了したらMainActivityに遷移
            Intent intent = new Intent(AtariVideoActivity.this, MainActivity.class);
            startActivity(intent);

            // AtariVideoActivityを終了する
            finish();
        });
    }
}
