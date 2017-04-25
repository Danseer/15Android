package com.example.konstantin.a151515;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class settingActivity extends AppCompatActivity {

    Switch theme,sound, clock, step,music;
    boolean stateTheme, stateSound, stateClock, stateStep,stateMusic;
    Button apply;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initSwitch();
        initApply();
    }

    //------------------------  initApply  -------------------------------------

    public void initApply(){
        apply=(Button)findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(settingActivity.this,Start_activity.class);
                intent.putExtra("theme",stateTheme);
                intent.putExtra("sound",stateSound);
                intent.putExtra("music",stateMusic);
                intent.putExtra("step",stateStep);
                intent.putExtra("clock",stateClock);
                startActivity(intent);
            }
        });
    }

    //-------------------  initSwitch  ---------------------------------------

    public void initSwitch(){

        theme=(Switch)findViewById(R.id.themeSwitch);
        stateTheme=theme.isChecked();
        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                stateTheme=isChecked;
            }
        });
        sound=(Switch)findViewById(R.id.soundEffSwitch);
        stateSound=sound.isChecked();
        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                stateSound=isChecked;
            }
        });
        music=(Switch)findViewById(R.id.musicSwitch);
        stateMusic=music.isChecked();
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                stateMusic=isChecked;
            }
        });
        clock=(Switch)findViewById(R.id.clockSwitch);
        stateClock=clock.isChecked();
        clock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                stateClock=isChecked;
            }
        });
        step=(Switch)findViewById(R.id.stepSwitch);
        stateStep=step.isChecked();
        step.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                stateStep=isChecked;
            }
        });

    }


}
