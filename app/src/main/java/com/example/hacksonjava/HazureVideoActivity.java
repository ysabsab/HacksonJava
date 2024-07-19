package com.example.hacksonjava;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;


/***main動画から遷移してきて、外れ映像を流し、終わったらループを動画に戻す***/
public class HazureVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //どの画面と対応づけるか。
        setContentView(R.layout.activity_main);

        //画面を作っているxmlファイルのやつを変数に落とし込み
        VideoView videoView = findViewById(R.id.videoView);
        Button Button = findViewById(R.id.button);

        //動画のurlを設定
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hazure_video);
        videoView.setVideoURI(videoUri);

        //メディアコントロール
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //動画開始
        videoView.start();

        // 動画再生が終了したときのリスナーを設定
        videoView.setOnCompletionListener(mp -> {
            // 動画が終了したらMainActivityに遷移
            Intent intent = new Intent(HazureVideoActivity.this, LoopVideoActivity.class);
            startActivity(intent);

            // HazureVideoActivityを終了する
            finish();
        });

        //ボタンを押されたらループに戻る
        Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HazureVideoActivity.this, LoopVideoActivity.class);
                //LoopVideoActivity.class
                startActivity(intent);
            }
        });
    }
}
