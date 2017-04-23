package com.example.konstantin.a151515;

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
    SoundPool sp;
    Chronometer chron;
    final int MAX_STREAMS = 2;
    int matrix[][] = new int[4][4];
    int Etalon[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    int countStep=0;
    int soundIdExplosion;
    final String TAG = "States";
    long time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        setSoundPool();
        setLayoutManager();
        setGameBoard();
        step=(TextView)findViewById(R.id.step);
        Generate();
        Redraw();
        setChronometr();

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

    //------------------------ setChronometr  -------------

    public void setChronometr(){
        chron=(Chronometer)findViewById(R.id.chron);
        //chron.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        chron.setBase(SystemClock.elapsedRealtime()-time);
        chron.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        time=SystemClock.elapsedRealtime()-chron.getBase();
        chron.stop();
    }

    //-----------------   Generate   --------------------------

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

    //----------------- getAllItemList --------------------

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
                changeCounter();
                sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
                Redraw();
            }
        }

        if (i < 3) {
            if (matrix[i + 1][j] == 0) {
                matrix[i + 1][j] = num;
                matrix[i][j] = 0;
                changeCounter();
                sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
                Redraw();
            }
        }

        if (j > 0) {
            if (matrix[i][j - 1] == 0) {
                matrix[i][j - 1] = num;
                matrix[i][j] = 0;
                changeCounter();
                sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
                Redraw();

            }
        }
        if (j < 3) {
            if (matrix[i][j + 1] == 0) {
                matrix[i][j + 1] = num;
                matrix[i][j] = 0;
                changeCounter();
                sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
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
    public void changeCounter(){
        countStep++;
        step.setText(Integer.toString(countStep) + " Step" );

    }


}
