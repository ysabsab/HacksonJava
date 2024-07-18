package com.example.hacksonjava;

public class LoopVideoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = findViewById(R.id.videoView);

        // 動画のURIを設定します。ローカルファイルやURLを使用できます。
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video);
        videoView.setVideoURI(videoUri);

        // メディアコントローラーを追加します。
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // 動画の再生を開始します。
        videoView.start();
    }
}
