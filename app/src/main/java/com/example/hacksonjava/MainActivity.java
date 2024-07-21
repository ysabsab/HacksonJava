package com.example.hacksonjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.OutputStream;
import java.net.Socket;
import java.io.InputStream;

/****main****/
public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //動画再生プログラム
        playMovie();

        /**↓activity_mainの各種機能を変数にするプール↓**/
        Button Button = findViewById(R.id.button);      //ボタンが押されたら、入力された文字を判定
        TextView textView = findViewById(R.id.textView);


        /***buttonを押された時の処理***/
        Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /**↓ループの動画のアクティビティに遷移**/
                Intent intent = new Intent(MainActivity.this, LoopVideoActivity.class);
                //LoopVideoActivity.class
                startActivity(intent);
                // TextViewのテキストを変更
                //textView.setText("Reach!");
                /**↑ループの動画のアクティビティに遷移**/
            }
        });
    }

    /****画面レイアウトのビデオの設定****/
    private void playMovie()
    {
        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        //ビデオファイルの取得(ジャンパチの説明)
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+ R.raw.jyanpati);

        //ビデオのコントトール
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // 動画が終了したときに再生をループするためのリスナーを設定
        videoView.setOnCompletionListener(mp -> videoView.start());
        //ビデオの再生(ジャンパチの説明ビデオ)
        videoView.start();
    }
}
