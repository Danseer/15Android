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

        List<myButton> rowListItem = getAllItemList();

        RecyclerViewAdapter RVA=new RecyclerViewAdapter(MainActivity.this,rowListItem);
        gameBoard.setAdapter(RVA);

    }

    private List<myButton> getAllItemList(){

        List<myButton> allItems = new ArrayList<>();
        allItems.add(new myButton(MainActivity.this,null,"0",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"1",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"2",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"3",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"4",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"5",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"6",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"7",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"8",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"9",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"10",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"11",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"12",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"13",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"14",R.color.colorAccent));
        allItems.add(new myButton(MainActivity.this,null,"15",R.color.colorAccent));

        return allItems;
    }
}
