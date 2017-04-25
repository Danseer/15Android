package com.example.konstantin.a151515;


import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    Button start, setting;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initButton();

    }

    public  void initButton(){
        start=(Button)findViewById(R.id.startGame);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intent=new Intent(MainActivity.this,Start_activity.class);
               startActivity(intent);
            }
        });
        setting=(Button)findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,settingActivity.class);

                startActivity(intent);
            }
        });
    }





}


