package demo.com.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.com.library.R;
import demo.com.library.Util;


public class MyTextView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    int width = (int) Util.dpToPixel(60);
    int height = (int)Util.dpToPixel(30);


    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        int color = typedArray.getInteger(R.styleable.MyTextView_my_view_color, Color.RED);
        typedArray.recycle();


        paint.setColor(color);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//        setMeasuredDimension(width, height);
//
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,width,height,paint);


    }
}
