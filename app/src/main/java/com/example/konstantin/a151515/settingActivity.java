package com.example.konstantin.a151515;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class settingActivity extends AppCompatActivity {

    Switch theme,sound, clock, step;
    boolean stateTheme, stateSound, stateClock, stateStep;
    Button apply;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initSwitch();
        apply=(Button)findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(settingActivity.this,Start_activity.class);
                intent.putExtra("theme",stateTheme);
                intent.putExtra("sound",stateSound);
                intent.putExtra("clock",stateClock);
                intent.putExtra("step",stateStep);
                startActivity(intent);
            }
        });
    }


    public void initSwitch(){

        theme=(Switch)findViewById(R.id.themeSwitch);
        stateTheme=theme.isChecked();
        sound=(Switch)findViewById(R.id.soundEffSwitch);
        stateSound=sound.isChecked();
        clock=(Switch)findViewById(R.id.clockSwitch);
        stateClock=clock.isChecked();
        step=(Switch)findViewById(R.id.stepSwitch);
        stateStep=step.isChecked();
    }


}
