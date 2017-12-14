package com.example.akasoo.myminislot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SlotStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_start);


        //手持ちコイン
        TextView te =  (TextView)findViewById(R.id.te);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();

        if(pref.getInt("TEMOTI",-1)!=1000){
            editor.putInt("TEMOTI",1000);
            te.setText(String.valueOf(pref.getInt("TEMOTI",-1)));
        }

        Intent intent = getIntent();
        TextView textView =  (TextView)findViewById(R.id.kake);
        if(intent.getIntExtra("kakekin",-1)==-1){

            textView.setText(String.valueOf(10));
        }else{
            textView.setText(String.valueOf(intent.getIntExtra("kakekin",0)));
        }



        if(pref.getInt("TEMOTI",-1)<intent.getIntExtra("kakekin",0)){
            textView.setText(String.valueOf(0));
        }

        te.setText(String.valueOf(pref.getInt("TEMOTI",0)));

//        te.setText(pref.getInt("TEMOTI",1000));
//        ボツ案↑
//        理由：setTextの引数がStringなため、"TEMOTI"に値が格納されてなかった場合1000というint型が引数になるため
    }


    public void Max(View view){
        TextView textView =  (TextView)findViewById(R.id.kake);
        int kake = Integer.parseInt(textView.getText().toString());
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int genkin=pref.getInt("TEMOTI",0);
        if(kake<genkin) {
            kake = genkin;
        }
        String kakekin=String.valueOf(kake);
        textView.setText(kakekin);
    }





    public void Up(View view){
        TextView textView =  (TextView)findViewById(R.id.kake);
        int kake = Integer.parseInt(textView.getText().toString());
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int genkin=pref.getInt("TEMOTI",0);
        if(kake<genkin) {
            kake = kake + 10;
        }
        String kakekin=String.valueOf(kake);
        textView.setText(kakekin);
    }

    public void down(View view){
        TextView textView =  (TextView)findViewById(R.id.kake);
        int kake = Integer.parseInt(textView.getText().toString());
        if(kake>10) {
            kake = kake - 10;
        }
        String kakekin=String.valueOf(kake);
        textView.setText(kakekin);
    }


    public void onButtonNext(View view){
        TextView textView =  (TextView)findViewById(R.id.kake);
        int kake = Integer.parseInt(textView.getText().toString());


        Intent intent = new Intent(this,SlotActivity.class);
        intent.putExtra("kakekin",kake);
        startActivity(intent);
    }

    public void onClickReset(View view){
        TextView te =  (TextView)findViewById(R.id.te);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        //共有プリファレンスの削除をしないとアプリ終了後も手持ちコインを保持し続ける
        editor.clear();
        editor.putInt("TEMOTI",1000);

        editor.commit();

        te.setText(String.valueOf(pref.getInt("TEMOTI",0)));

        TextView textView =  (TextView)findViewById(R.id.kake);
        textView.setText(String.valueOf(10));
    }

//    private void save(){
//        TextView temoti =  (TextView)findViewById(R.id.temoti);
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = pref.edit();
//
//    }



}
