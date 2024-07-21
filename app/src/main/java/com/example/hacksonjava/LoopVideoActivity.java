package com.example.hacksonjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;


/**メインから状態遷移をしてきて、ループ画像を流す処理**/
public class LoopVideoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //()、取得する
        VideoView videoView = findViewById(R.id.videoView);
        Button Button = findViewById(R.id.button);

        // 動画のURIを設定
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.loop_video);
        videoView.setVideoURI(videoUri);

        // メディアコントローラーを追加
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // 動画が終了したときに再生をループするためのリスナーを設定
        videoView.setOnCompletionListener(mp -> videoView.start());

        // 動画の再生を開始
        videoView.start();

        /***buttonを押された時の処理***/
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**↓ループの動画のアクティビティに遷移**/
                Intent intent = new Intent(LoopVideoActivity.this, IntoReachLoopVideoActivity.class);
                startActivity(intent);
                finish();
                /**↑ループの動画のアクティビティに遷移**/
            }
        });
    }
}
