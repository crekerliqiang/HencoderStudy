package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {
    
    private Paint mPaint = new Paint();

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        //黑色、填满、去除毛边
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);//
        canvas.drawCircle(300, 200, 200, mPaint);
        //空心圆
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(800, 200, 200, mPaint);
        //蓝色实心圆
        mPaint.reset();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(300,600,200,mPaint);
        //线宽为20的空心圆
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);//空心
        mPaint.setColor(Color.BLACK);//黑色
        mPaint.setStrokeWidth(60f);//线宽
        mPaint.setAntiAlias(true);//去除毛边
        canvas.drawCircle(800,600,200,mPaint);
    }
}
