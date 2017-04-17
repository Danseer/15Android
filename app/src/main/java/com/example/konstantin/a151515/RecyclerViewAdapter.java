package com.example.konstantin.a151515;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by Konstantin on 07.04.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ButtonViewHolders> {


    private List<SquareButton> mySquareButtonList;
    private Context context;

//------------------------- interface onClick (main activity(redraw())-----------------------------

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position, String s);
    }
    public static OnItemClickListener listener;


    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
 //----------------------------------------------------------------------------------

    public RecyclerViewAdapter(Context context,List<SquareButton> mySquareButtonList){
        this.mySquareButtonList = mySquareButtonList;
        this.context = context;

    }

//---------------------------------------------------------------------------------
    public static class ButtonViewHolders extends RecyclerView.ViewHolder{

        //инициализируем View, входящие в RecyclerView.

        public SquareButton b;


        public ButtonViewHolders(View itemView) {

            super(itemView);
            b=(SquareButton) itemView.findViewById(R.id.squareButton);

        }
    }

//--------------------------------------onCreate----------------------------------------------
    @Override
    public ButtonViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.square_button,parent,false);//парсим layout square_button
        ButtonViewHolders BVH=new ButtonViewHolders(v);
        return  BVH;
    }
//---------------------------------------onBind-----------------------------------------------
    @Override
    public void onBindViewHolder(final ButtonViewHolders holder, final int position) {

        holder.b.setText(mySquareButtonList.get(position).name);
        holder.b.setVisibility(mySquareButtonList.get(position).visibility);
        final String s=mySquareButtonList.get(position).name;
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecyclerViewAdapter.listener.onItemClick(v,position,s);
            }
        });

    }
//--------------------------------------------------------------------------------------
    @Override
    public int getItemCount() {

        return mySquareButtonList.size();
    }


}
