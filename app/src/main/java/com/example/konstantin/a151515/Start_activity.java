package com.example.konstantin.a151515;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Start_activity extends AppCompatActivity implements OnLoadCompleteListener{

    RecyclerView gameBoard;


    TextView step;
    TextView nameUser;
    GridLayoutManager layoutManager;
    LinearLayout l;
    SoundPool sp;
    Chronometer chron;
    final int MAX_STREAMS = 2;
    int matrix[][] = new int[4][4];
    int Etalon[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    int countStep=0;
    int soundIdExplosion;
    final String TAG = "States";
    long time;
    Intent intent;
    boolean stateTheme, stateSound, stateMusic, stateClock, stateStep, changeStep, changeVol,changeBg, changeChron, changeMusic;
    MediaPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        getIntentFromSetting();
        setSoundPool();
        setLayoutManager();
        setGameBoard();
        changeBg();
        step=(TextView)findViewById(R.id.step);
        Generate();
        Redraw();
        setChronometr(changeChron);



        mPlayer=MediaPlayer.create(this, R.raw.en);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mp) {
                                                //mPlayer.stop();
                                            }
        });

    }

    //------------------------  getIntentFromSettingActivity  -------------------------------

    public void  getIntentFromSetting(){

        intent=getIntent();
        stateTheme=intent.getBooleanExtra("theme",false);
        stateSound=intent.getBooleanExtra("sound",true);
        stateMusic=intent.getBooleanExtra("music",true);
        stateClock=intent.getBooleanExtra("clock",true);
        stateStep=intent.getBooleanExtra("step",true);

        changeStep=stateStep;
        changeVol=stateSound;
        changeMusic=stateMusic;
        changeBg=stateTheme;
        changeChron=stateClock;
    }

    //--------------------------------- changeBg  -------------------------------------

    public void changeBg(){
        l=(LinearLayout)findViewById(R.id.llStart);
        if(!changeBg){
            l.setBackgroundColor(getResources().getColor(R.color.black));
            gameBoard.setBackgroundColor(getResources().getColor(R.color.black));
        }
        else{
            l.setBackgroundColor(getResources().getColor(R.color.white));
            gameBoard.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    //--------------------------  setSoundParam  -----------------------------------------
    public  void setSoundParam(){
        if(changeVol)
            sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
        else
            sp.play(soundIdExplosion, 0, 0, 0, 0, 1);
    }

    //----------------------------  setSoundPool -----------------------

    public void setSoundPool(){
        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);
        soundIdExplosion=sp.load(this,R.raw.sdvig,0);
    }

    //--------------------  setLayoutManager -----------------------

    public void setLayoutManager(){
        layoutManager = new GridLayoutManager(Start_activity.this, 4);
    }

    //------------------------  setGameBoard  -------------------------

    public void setGameBoard(){
        gameBoard = (RecyclerView)findViewById(R.id.game_board);
        gameBoard.setHasFixedSize(true);
        gameBoard.setLayoutManager(layoutManager);

    }

    //----------------------------  onLoadComplete  ---------------------------

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

    }

    //------------------------ setChronometr  -------------------------------------

    public void setChronometr(boolean changeChron){
        if(changeChron)
            chron=(Chronometer)findViewById(R.id.chron);
        else
            chron=(Chronometer)findViewById(R.id.chron2);
    }

    //---------------------- onStartActivity  --------------------------------

    @Override
    protected void onStart() {
        super.onStart();
        chron.setBase(SystemClock.elapsedRealtime()-time);
        chron.start();
        if(changeMusic) mPlayer.start();

    }

    //------------------------------  onStopActivity  -----------------------------

    @Override
    protected void onStop() {
        super.onStop();
        time=SystemClock.elapsedRealtime()-chron.getBase();
        chron.stop();
        mPlayer.stop();
    }

    //-----------------------------   Generate   --------------------------

    public void Generate() {

        Random generator = new Random();
        int k;
        int[] RandomArr = new int[16];

        for (int a = 0; a < 16; a++) RandomArr[a] = -1;//init array arr -1

        do {
            for (int b = 0; b < 16; b++) {

                do {
                    k = generator.nextInt(16);// random numb 0-15
                }

                while (RandomArr[k] != -1);

                RandomArr[k] = b;
            }
        }
        while (!Opportunity(RandomArr));


        int c = 0;
        for (int i = 0; i < 4; i++) {//fill field from rand array
            for (int j = 0; j < 4; j++) {

                matrix[i][j] = RandomArr[c];
                c++;
            }
        }

    }

    //----------------------  Opportunity  ---------------------
    public boolean Opportunity(int Array[]) {
        return true;//if possible fold
        //return false if impossible
    }

    //----------------------  Redraw  ---------------------

    private void Redraw() {
        List<SquareButton> rowListItem = getAllItemList();
        RecyclerViewAdapter RVA = new RecyclerViewAdapter(Start_activity.this, rowListItem);
        gameBoard.setAdapter(RVA);

        // onClick for items(with Adapter) //

        RVA.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position, String s) {
                //Toast.makeText(MainActivity.this, "Name " + s + " position " + position, Toast.LENGTH_SHORT).show();
                spin(Integer.valueOf(s));

            }
        });
    }

    //------------------------------------ getAllItemList --------------------

    private List<SquareButton> getAllItemList() {

        List<SquareButton> allItems = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                SquareButton sb = new SquareButton(Start_activity.this, matrix[i][j]);
                allItems.add(sb);
            }
        }

        return allItems;
    }


    //------------------------- SPIN ----------------------------------
    private void spin(int num) { //change button on the field

        int i = 0, j = 0;

        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                if (matrix[k][l] == num) {
                    i = k;
                    j = l;

                }
            }
        }

        if (i > 0) {
            if (matrix[i - 1][j] == 0) {
                matrix[i - 1][j] = num;
                matrix[i][j] = 0;
                changeCounter(changeStep);
                setSoundParam();
                Redraw();
            }
        }

        if (i < 3) {
            if (matrix[i + 1][j] == 0) {
                matrix[i + 1][j] = num;
                matrix[i][j] = 0;
                changeCounter(changeStep);
                setSoundParam();
                Redraw();
            }
        }

        if (j > 0) {
            if (matrix[i][j - 1] == 0) {
                matrix[i][j - 1] = num;
                matrix[i][j] = 0;
                changeCounter(changeStep);
                setSoundParam();
                Redraw();

            }
        }
        if (j < 3) {
            if (matrix[i][j + 1] == 0) {
                matrix[i][j + 1] = num;
                matrix[i][j] = 0;
                changeCounter(changeStep);
                setSoundParam();
                Redraw();
            }
        }


    }

    //-------------------- Check Win -------------------------------
    public boolean CheckWin() {
        int e = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j] != Etalon[e]) {
                    return false;
                } else e++;
            }
        }
        return true;
    }

    //------------------------- Win --------------------------------

    //----------------------- Step Counter ------------------------------
    public void changeCounter(boolean changeStep){
        countStep++;
        if(changeStep)
        step.setText(Integer.toString(countStep) + " Step" );

    }


}
