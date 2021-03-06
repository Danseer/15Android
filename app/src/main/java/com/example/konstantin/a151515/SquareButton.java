package com.example.konstantin.a151515;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by Konstantin on 09.04.2017.
 */


public class SquareButton extends android.support.v7.widget.AppCompatButton {

    public String getName() {
        return name;
    }

    String name;
    int visibility;


    public SquareButton(Context context,int Name) {

        super(context);
        if(Name==0) {
            visibility=INVISIBLE;
            this.setVisibility(visibility);

        }

        else {
                this.name=String.valueOf(Name);
                this.setText(name);
                visibility=VISIBLE;
                this.setVisibility(visibility);
        }

    }

    public SquareButton(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public SquareButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // вызываем метод onMeasure класса ImageButton, чтобы расcчитать размеры
        // кнопки стандартным образом
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // сейчас наша кнопка имеет такие же размеры как если бы
        // она была экземпляром класса ImageButton

        // начинаем добавлять новую логику расчета размера

        // получаем рассчитанные размеры кнопки
        final int height = getMeasuredHeight();	// высота
        final int width = getMeasuredWidth();	// ширина

        // теперь задаем новый размер
        // ширину оставляем такую же как у стандартной кнопки
        // высоту выбираем как максимум между стандартной высотой и шириной
        setMeasuredDimension(width, Math.max(width, height));
    }

}
