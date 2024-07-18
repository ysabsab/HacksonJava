package com.example.hacksonjava;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class HazureVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //どの画面と対応づけるか。
        setContentView(R.layout.activity_video);

        //画面を作っているxmlファイルのやつを変数に落とし込み
        VideoView videoView = findViewById(R.id.videoView);

        //動画のurlを設定
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hazure_video);
        videoView.setVideoURI(videoUri);

        //メディアコントロール
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //動画開始
        videoView.start();

        //動画が終わったら、Loop動画を流すための画面を遷移のためのインテントを作成
        Intent intent = new Intent(HazureVideoActivity.this, LoopVideoActivity.class);
        //作成したいんてんとを実行
        startActivity(intent);
    }
}
