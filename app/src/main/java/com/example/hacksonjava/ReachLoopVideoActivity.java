package com.example.hacksonjava;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.net.Uri;
import android.widget.Button;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.View;
import android.widget.VideoView;
import android.widget.MediaController;

/***リーチした時映像をループさせる***/
public class ReachLoopVideoActivity extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_two_button);

            //()、取得する
            VideoView videoView = findViewById(R.id.videoView);
            Button Button_Atari = findViewById(R.id.button1);
            Button Button_Hazure = findViewById(R.id.button2);

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

            /***Ataributtonを押された時の処理***/
            Button_Atari.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    /**↓ループの動画のアクティビティに遷移**/
                    Intent intent = new Intent(ReachLoopVideoActivity.this, AtariVideoActivity.class);
                    startActivity(intent);
                    finish();
                    /**↑ループの動画のアクティビティに遷移**/
                }
            });

            /***hazure buttonを押された時の処理***/
            Button_Hazure.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    /**↓ループの動画のアクティビティに遷移**/
                    Intent intent = new Intent(ReachLoopVideoActivity.this, HazureVideoActivity.class);
                    startActivity(intent);
                    finish();
                    /**↑ループの動画のアクティビティに遷移**/
                }
            });
    }
}
