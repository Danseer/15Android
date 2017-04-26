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
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity{

    Button start, setting;
    Intent intent;
    SoundPool sp;
    int soundIdExplosion;
    final int MAX_STREAMS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSoundPool();
        initButton();

        final ImageView iv=(ImageView)findViewById(R.id.imageView);

        final Animation kub = AnimationUtils.loadAnimation(this, R.anim.enlarge);
        final Animation kubShake=AnimationUtils.loadAnimation(this,R.anim.shake);

        final Animation inSt = AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_in_left);
        final Animation inStShake=AnimationUtils.loadAnimation(this,R.anim.shake);

        final Animation inSet = AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_in_left);
        final Animation inSetShake=AnimationUtils.loadAnimation(this,R.anim.shake);

        inSt.setDuration(300);
        inSet.setDuration(300);

        iv.startAnimation(kub);
        kub.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(kubShake);
                start.startAnimation(inSt);
                sp.play(soundIdExplosion, 1, 1, 0, 0, 1);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        inSt.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                start.setVisibility(VISIBLE);
                setting.startAnimation(inSet);
                sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
                start.startAnimation(inStShake);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        inSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setting.setVisibility(VISIBLE);
                sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
                setting.startAnimation(inSetShake);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });






    }

    public  void initButton(){
        start=(Button)findViewById(R.id.startGame);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intent=new Intent(MainActivity.this,Start_activity.class);
               startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_oud);
            }
        });
        setting=(Button)findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,settingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_oud);

            }
        });
    }

    //----------------------------  setSoundPool -----------------------

    public void setSoundPool(){
        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        //sp.setOnLoadCompleteListener(this);
        soundIdExplosion=sp.load(this,R.raw.bum2,0);
    }

    //----------------------------  onLoadComplete  ---------------------------
/*
    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

    }

*/



}


