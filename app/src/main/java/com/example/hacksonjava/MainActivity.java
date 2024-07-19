package com.example.hacksonjava;

import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    //private List<String> selectedTexts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /**　↓変数プール↓**　//
        /*
        //チェックボックスに入れられた文字をまず変数に格納する
        CheckBox CheckBox_mozi = findVieById(R.id.checkbox);

        //ボタンが押されたら、入力された文字を判定
        Button btnSend = (Button) this.findViewById(R.id.button);
        */

        //**　↑変数プール↑　**//


        /***チェックボックスの状態遷移を監視しておき、チャックの有無でリストにチャックの文字を入力するか決める。***/
        /*
        //一個目のチェックボックス
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedTexts.add(checkBox1.getText().toString());
                } else {
                    selectedTexts.remove(checkBox1.getText().toString());
                }
            }
        });

        //ボタンが押されたら、入力された文字を判定
        Button btnSend = (Button) this.findViewById(R.id.button);

        //ボタンの動作設定
        sendButton.setOnClickListener(v -> {
            // リストの内容を送信する処理
            sendSelectedTexts();
        });

        //送信処理
        private void sendSelectedTexts() {
        // ここにリストの内容を送信する処理を実装します。
        // 例えば、リストの内容をログに出力する場合：
        for (String text : selectedTexts) {
            Log.d("Selected Text", text);
        }
          */

        //動画再生プログラム
        playMovie();

    }

    private void playMovie(){
        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        //ビデオファイルの取得
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+ R.raw.jyanpati);

        //ビデオのコントトール
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //ビデオの再生
        videoView.start();
    }
    //test
}