package com.example.hacksonjava;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Vibrator;
import android.view.View;
import android.content.Context;
import android.widget.Toast;
import android.util.Log;

/***main動画から遷移してきて、動画が終わったらmainに戻す***/
public class AtariVideoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_video);

        //画面を挿入
        VideoView videoView = findViewById(R.id.videoView);
        Button sendButton = findViewById(R.id.button);

        //動画のurlを挿入
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.atari_video);
        videoView.setVideoURI(videoUri);

        //メディアコントロール
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //動画スタート
        videoView.start();

        // バイブレーターのインスタンスを取得
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // バイブレーターが存在するか？
        if (vibrator != null && vibrator.hasVibrator()) {
            // バイブレーションスタート（50ミリ秒の休止、バイブレーション、200ミリ秒）
            vibrator.vibrate(new long[]{50, 2000, 50, 2000,100,2000,16000,2000,50,2000,200,3000}, -1); // -1はパターンを繰り返さないことを意味します
            Log.d("VibrationTest", "Vibration started");
        }
        else {
            // Toastメッセージ
            Toast.makeText(AtariVideoActivity.this, "Device does not support vibration", Toast.LENGTH_SHORT).show();
            Log.d("VibrationTest", "Device does not support vibration");
        }

        //((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(1000);

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
