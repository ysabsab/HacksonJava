package com.example.hacksonjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.OutputStream;
import java.net.Socket;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
{
    /***このクラスで利用する変数定義***/
    // 送信するテキストの設定
    private String selectedText = "";

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

        /************↓activity_mainの各種機能を変数にするプール↓**********************/
        //ボタンが押されたら、入力された文字を判定
        Button sendButton = findViewById(R.id.button);

        /************↑activity_mainの各種機能を変数にするプール↑**********************/

        //buttonを押された時の処理
        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //送信する処理の関数に投げる。
                sendSelectedText();

                /*****↓テスト項目(本来ない機能)*****/
                Intent intent = new Intent(MainActivity.this, HazureVideoActivity.class);
                //LoopVideoActivity.class
                startActivity(intent);
                /*****↑テスト項目(本来ない機能)*****/
            }
        });
    }

    //画面レイアウトのビデオの設定
    private void playMovie()
    {
        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        //ビデオファイルの取得(ジャンパチの説明)
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+ R.raw.jyanpati);

        //ビデオのコントトール
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //ビデオの再生(ジャンパチの説明ビデオ)
        videoView.start();
    }

    /****送信処理****/
    private void sendSelectedText()
    {
        selectedText = "start";                     //送信するテキスト

        Log.d("Selected Text", selectedText);       // 送信する文字のログを出力する

        // サーバーにデータを送信するスレッドを立てる。
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Log.d("TCP", "Connecting to server...");
                    // サーバーのIPアドレスとポート番号を指定してソケットを作成
                    Socket socket = new Socket("10.0.0.102", 5000);

                    Log.d("TCP", "Connected to server");
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(selectedText.getBytes());
                    outputStream.flush();
                    Log.d("TCP", "Data sent");

                    // サーバーからのレスポンスを受信
                    InputStream inputStream = socket.getInputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    StringBuilder responseBuilder = new StringBuilder();

                    socket.setSoTimeout(1000000); // 1000秒の読み取りタイムアウト

                    try
                    {
                        //サーバーとの接続を立てておく
                        while ((bytesRead = inputStream.read(buffer)) != -1)
                        {
                            String chunk = new String(buffer, 0, bytesRead);
                            responseBuilder.append(chunk);

                            /***reach!の文字列が含まれているし、画面遷移させる、バックグラウンドでmain動かす***/
                            if (responseBuilder.toString().contains("reach!"))
                            {
                                String response = responseBuilder.toString();
                                Log.d("TCP", "Received from server: " + response);

                                // UIスレッドでのアクション、バックグラウンドでmainを動かしたいため
                                runOnUiThread(() -> {
                                    Intent intent = new Intent(MainActivity.this, LoopVideoActivity.class);
                                    startActivity(intent);
                                });
                            }
                            /***zannnen！の文字列が含まれていたら、画面遷移させる、バックグラウンドでmain動かす***/
                            if (responseBuilder.toString().contains("za"))
                            {
                                String response = responseBuilder.toString();
                                Log.d("TCP", "Received from server: " + response);

                                // UIスレッドでのアクション、バックグラウンドでmainを動かしたいため
                                runOnUiThread(() ->
                                {
                                    Intent intent = new Intent(MainActivity.this, HazureVideoActivity.class);
                                    startActivity(intent);
                                });
                            }

                            /***Hit！の文字列が含まれていたらソケットを閉じて、遷移させる***/
                            if (responseBuilder.toString().contains("transition"))
                            {
                                String response = responseBuilder.toString();
                                Log.d("TCP", "Received from server: " + response);

                                // UIスレッドでのアクション
                                runOnUiThread(() ->
                                {
                                    Intent intent = new Intent(MainActivity.this, AtariVideoActivity.class);
                                    startActivity(intent);
                                });
                                break; //サーバーとの接続を続けるwhileを抜ける
                            }
                        }
                    }
                    catch (java.net.SocketTimeoutException e)
                    {
                        Log.d("TCP", "Read timeout reached");
                        //エラー処理
                    }

                    // ソケットを閉じる
                    socket.close();
                    Log.d("TCP", "Socket closed");

                }
                catch (Exception e)
                {
                    Log.e("TCP", "Error: " + e.getMessage(), e);
                }
            }
        }).start();
    }
}
