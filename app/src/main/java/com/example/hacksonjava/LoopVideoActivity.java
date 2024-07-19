package com.example.hacksonjava;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;


/**メインから状態遷移をしてきて、ループ画像を流す処理**/
public class LoopVideoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        //()、取得する
        VideoView videoView = findViewById(R.id.videoView);

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
    }
}
