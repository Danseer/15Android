package com.example.konstantin.a151515;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by Konstantin on 07.04.2017.
 */

public class myButton extends View{

    String name;
    int bg;
    //int width;
    //int height;

    public myButton(Context context, AttributeSet attrs,String name,int bg) {
        super(context, attrs);
        this.name = name;
        this.bg = bg;
        // TODO Auto-generated constructor stub
    }

    //public myButton(String name,int bg) {

        //this.name = name;
        //this.bg = bg;

    //}


}


