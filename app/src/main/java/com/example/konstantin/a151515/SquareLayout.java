package com.example.konstantin.a151515;

/**
 * Created by Konstantin on 06.04.2017.
 */

import android.content.Context;
import android.view.View;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by DanSeer on 01.04.2017.
 */

public class SquareLayout extends FrameLayout {

    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){

        int width=View.MeasureSpec.getSize(widthMeasureSpec);
        int height=View.MeasureSpec.getSize(heightMeasureSpec);

        if (width < (int) (height + 0.5)) {
            width = (int) (height + 0.5);
        } else {
            height = (int) (width + 0.5);
        }

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(width,MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY)
        );
    }
}