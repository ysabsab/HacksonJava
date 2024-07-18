package com.example.hacksonjava;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    /***このクラスで利用する変数定義***/
    // 送信するリストの変数
    private List<String> selectedTexts = new ArrayList<>();

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

        /************↓activity_mainの各種機能を変数にするプール↓**********************/
        //チェックボックスに入れられた文字をまず変数に格納する
        CheckBox CheckBox_mozi = findVieById(R.id.checkbox);

        //ボタンが押されたら、入力された文字を判定
        Button sendButton = (Button) this.findViewById(R.id.button);

        /************↓activity_mainの各種機能を変数にするプール↓**********************/


        /***チェックボックスの状態遷移を監視しておき、チャックの有無でリストにチャックの文字を入力するか決める。***/
        //一個目のチェックボックス
        CheckBox_mozi .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            //チェックボックスで取り出した数値をリストに格納する。
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedTexts.add(CheckBox_mozi .getText().toString());
                } else {
                    selectedTexts.remove(CheckBox_mozi .getText().toString());
                }
            }
        });

        //二個目のチェックボックス
        //**ここにチェックボックスを取り出す機能を複数入れる**//

        //ボタンの動作設定
        sendButton.setOnClickListener(v -> {
            // リストの内容を送信する処理
            sendSelectedTexts();
        });

        //送信処理
        private void sendSelectedTexts() {
            // 送信するリストの内容をログに出力する
            for (String text : selectedTexts) {
                Log.d("Selected Text", text);
            }
            /***↓ここにリストの内容を送信する処理を実装↓***/


            /***↑ここにリストの内容を送信する処理を実装↑***/

            //送信処理が行われたら、Loop動画を流すための画面を遷移のためのインテントを作成
            Intent intent = new Intent(MainActivity.this, LoopVideoActivity.class);

            //作成したいんてんとを実行
            startActivity(intent);
        }
    //test
}