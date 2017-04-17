package com.example.konstantin.a151515;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView gameBoard;
    TextView step;
    TextView nameUser;

    GridLayoutManager layoutManager;

    int matrix[][] = new int[4][4];
    private int Etalon[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    private int countStep=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutManager = new GridLayoutManager(MainActivity.this, 4);

        gameBoard = (RecyclerView)findViewById(R.id.game_board);
        gameBoard.setHasFixedSize(true);
        gameBoard.setLayoutManager(layoutManager);

        step=(TextView)findViewById(R.id.step);

        Generate();
        Redraw();

    }

    //-----------------Random Generate --------------------------

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
        RecyclerViewAdapter RVA = new RecyclerViewAdapter(MainActivity.this, rowListItem);
        gameBoard.setAdapter(RVA);

        // -----------  onClick for items(with Adapter)   ----------------

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

                SquareButton sb = new SquareButton(MainActivity.this, matrix[i][j]);
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
                Redraw();
            }
        }

        if (i < 3) {
            if (matrix[i + 1][j] == 0) {
                matrix[i + 1][j] = num;
                matrix[i][j] = 0;
                changeCounter();
                Redraw();
            }
        }

        if (j > 0) {
            if (matrix[i][j - 1] == 0) {
                matrix[i][j - 1] = num;
                matrix[i][j] = 0;
                changeCounter();
                Redraw();

            }
        }
        if (j < 3) {
            if (matrix[i][j + 1] == 0) {
                matrix[i][j + 1] = num;
                matrix[i][j] = 0;
                changeCounter();
                CheckWin();
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


