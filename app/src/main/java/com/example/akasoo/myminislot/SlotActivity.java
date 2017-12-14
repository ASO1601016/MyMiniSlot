package com.example.akasoo.myminislot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.akasoo.myminislot.R.id.one;
import static com.example.akasoo.myminislot.R.id.slot_one;

public class SlotActivity extends AppCompatActivity {
    boolean oneHan = false;
    boolean twoHan = false;
    boolean threeHan = false;
    boolean hantei = false;
    int oneG=0;
    int twoG=0;
    int threeG=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot);

        TextView temo =  (TextView)findViewById(R.id.temo);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        temo.setText(String.valueOf(pref.getInt("TEMOTI",0)));

        TextView kake =  (TextView)findViewById(R.id.kake);
        Intent intent = getIntent();
        kake.setText(String.valueOf(intent.getIntExtra("kakekin",0)));

    }




    public void onStop(View view){

        ImageView slot=null;
        int r = 0;
        switch (view.getId()){
            case one:
                if(!oneHan){
                    slot = (ImageView) findViewById(slot_one);
                    oneHan=true;
                    r=1;
                }
                break;
            case R.id.two:
                if(!twoHan) {
                    slot = (ImageView) findViewById(R.id.slot_two);
                    twoHan = true;
                    r=2;
                }
                break;
            case R.id.three:
                if(!threeHan){
                    slot = (ImageView) findViewById(R.id.slot_three);
                    threeHan=true;
                    r=3;
                }
                break;
        }


        //デバッグ用
//        TextView aa =  (TextView)findViewById(R.id.aa);
//        aa.setText(String.valueOf(oneHan));
//
//        TextView bb =  (TextView)findViewById(R.id.bb);
//        bb.setText(String.valueOf(twoHan));
//
//        TextView cc =  (TextView)findViewById(R.id.cc);
//        cc.setText(String.valueOf(threeHan));
//          Githubかくにん

        int h = 0;

        if(slot!=null){
            int nya = (int)(Math.random()*5);
            switch(nya){
                case 0:
                    slot.setImageResource(R.drawable.bar);
                    h = R.drawable.bar;
                    break;
                case 1:
                    slot.setImageResource(R.drawable.cherry);
                    h = R.drawable.cherry;
                    break;
                case 2:
                    slot.setImageResource(R.drawable.seven);
                    h = R.drawable.seven;
                    break;
                case 3:
                    slot.setImageResource(R.drawable.suika);
                    h = R.drawable.suika;
                    break;
                case 4:
                    slot.setImageResource(R.drawable.goruden);
                    h = R.drawable.goruden;
                    break;
                default:
                    slot.setImageResource(R.drawable.ison);
                    break;
            }

        }



        switch (r){
            case 1:
                oneG = h;
//                Toast.makeText(this, String.valueOf(oneG), Toast.LENGTH_LONG).show();
                break;
            case 2:
                twoG = h;
//                Toast.makeText(this, String.valueOf(twoG), Toast.LENGTH_LONG).show();
                break;
            case 3:
                threeG = h;
//                Toast.makeText(this, String.valueOf(threeG), Toast.LENGTH_LONG).show();
                break;
        }





        zenbu();


    }

    public void zenbu(){
        int kakeru = 1;
        int bai=1;


        if(oneHan&&twoHan&&threeHan){
            if(!hantei){
                hantei=true;



                //bar
                if((oneG==R.drawable.bar&&twoG==R.drawable.bar)||(twoG==R.drawable.bar&&threeG==R.drawable.bar)) {

                    kakeru=150;
                    if(oneG==R.drawable.bar&&twoG==R.drawable.bar&&threeG==R.drawable.bar) {
                        kakeru=200;
                    }

                }

                //cherry

                if(oneG==R.drawable.cherry&&twoG==R.drawable.cherry&&threeG==R.drawable.cherry) {
                    kakeru=100;
                }


                //suika


                if(oneG==R.drawable.suika||twoG==R.drawable.suika||threeG==R.drawable.suika){
                    bai=5;
                    if((oneG==R.drawable.suika&&twoG==R.drawable.suika)||(twoG==R.drawable.suika&&threeG==R.drawable.suika)){
                        kakeru=50;
                        bai=1;
                        if(oneG==R.drawable.suika&&twoG==R.drawable.suika&&threeG==R.drawable.suika) {
                            kakeru=500;
                            bai=1;
                        }
                    }
                }




                //seven
                if((oneG==R.drawable.seven&&twoG==R.drawable.seven)||(twoG==R.drawable.seven&&threeG==R.drawable.seven)) {

                    kakeru=10000;
                    if(oneG==R.drawable.seven&&twoG==R.drawable.seven&&threeG==R.drawable.seven) {
                        kakeru=1000;
                    }

                }


                //goruden
                if((oneG==R.drawable.goruden&&twoG==R.drawable.goruden)||(twoG==R.drawable.goruden&&threeG==R.drawable.goruden)) {
                    //二つ
                    kakeru=2500;
                    if(oneG==R.drawable.goruden&&twoG==R.drawable.goruden&&threeG==R.drawable.goruden) {
                        //三つ
                        kakeru=5000;
                    }

                }
                int a = 0;
                if(kakeru==1&&bai==1){
                    a=-1;
                }else if(kakeru==1&&bai!=1){
                    a=bai;
                }else if(kakeru!=1&&bai==1){
                    a=kakeru;
                }else if(kakeru!=1&&bai!=1){
                    a=kakeru * bai;
                }

                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = pref.edit();

                Intent intent = getIntent();
                int k=intent.getIntExtra("kakekin",0);

                a=k*a;



                int n=pref.getInt("TEMOTI",0);

                if(n+a<0){
                    n=999999999;
                    a=0;
                }else{
                    n = n + a;
                }

                if(n>=999999999){

                    n=999999999;
                    a=0;

                }


                Toast.makeText(this, String.valueOf(a), Toast.LENGTH_LONG).show();

                TextView temo =  (TextView)findViewById(R.id.temo);
                temo.setText(String.valueOf(n));

                editor.putInt("TEMOTI",n);
                editor.commit();




                intent = new Intent(this,SlotStartActivity.class);
                TextView textView =  (TextView)findViewById(R.id.kake);
                int kake = Integer.parseInt(textView.getText().toString());
                intent.putExtra("kakekin",kake);
                startActivity(intent);







            }
        }
    }

    public void onButtonReturn(View view){
//        finish();

        Intent intent = new Intent(this,SlotStartActivity.class);
        TextView textView =  (TextView)findViewById(R.id.kake);
        int kake = Integer.parseInt(textView.getText().toString());
        intent.putExtra("kakekin",kake);
        startActivity(intent);
    }


}
