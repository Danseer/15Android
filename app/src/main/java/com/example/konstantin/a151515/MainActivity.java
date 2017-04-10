package com.example.konstantin.a151515;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView gameBoard;
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutManager = new GridLayoutManager(MainActivity.this,4);

        gameBoard = (RecyclerView)findViewById(R.id.game_board);
        gameBoard.setHasFixedSize(true);
        gameBoard.setLayoutManager(layoutManager);

        List<SquareButton> rowListItem = getAllItemList();

        RecyclerViewAdapter RVA=new RecyclerViewAdapter(MainActivity.this,rowListItem);
        gameBoard.setAdapter(RVA);

    }

    private List<SquareButton> getAllItemList(){

        List<SquareButton> allItems = new ArrayList<>();
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        allItems.add(new SquareButton(MainActivity.this,null));
        return allItems;
    }
}
