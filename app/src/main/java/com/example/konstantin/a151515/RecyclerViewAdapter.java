package com.example.konstantin.a151515;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Konstantin on 07.04.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ButtonViewHolders> {


    private List<SquareButton> mySquareButtonList;
    private Context context;

    public RecyclerViewAdapter(Context context,List<SquareButton> mySquareButtonList){
        this.mySquareButtonList = mySquareButtonList;
        this.context = context;

    }

    public static class ButtonViewHolders extends RecyclerView.ViewHolder{

        //инициализируем View, входящие в RecyclerView.

        public Button b;

        public ButtonViewHolders(View itemView) {
            super(itemView);

            b=(Button)itemView.findViewById(R.id.squareButton);

        }
    }


    @Override
    public ButtonViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.square_button,parent,false);//парсим layout square_button
        ButtonViewHolders BVH=new ButtonViewHolders(v);
        return  BVH;
    }

    @Override
    public void onBindViewHolder(ButtonViewHolders holder, int position) {

        //связываем ButtonViewHolders с myButtonList
        //holder.b.setBackgroundColor(myButtonList.get(position).bg);
        // holder.b.setText(myButtonList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mySquareButtonList.size();
    }


}
