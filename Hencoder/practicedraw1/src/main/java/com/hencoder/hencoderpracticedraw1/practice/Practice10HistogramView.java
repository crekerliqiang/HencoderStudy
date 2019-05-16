package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    private Paint mPaint = new Paint();

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        mPaint.setAntiAlias(true);

        //背景
        canvas.drawColor(Color.GRAY);
        //画坐标轴
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(100, 20, 100, 501, mPaint);
        canvas.drawLine(100, 501, 900, 501, mPaint);


        //画条形
        mPaint.setColor(Color.GREEN | Color.BLUE);
        canvas.drawRect(140, 498, 220, 500, mPaint);
        canvas.drawRect(240, 585, 320, 500, mPaint);
        canvas.drawRect(340, 585, 420, 500, mPaint);
        canvas.drawRect(440, 270, 520, 500, mPaint);
        canvas.drawRect(540, 200, 620, 500, mPaint);
        canvas.drawRect(640, 150, 720, 500, mPaint);
        canvas.drawRect(740, 290, 820, 500, mPaint);

        //画条形下方
        mPaint.setTextSize(30);
        canvas.drawText("Froyo", 140, 530, mPaint);
        canvas.drawText("GB", 260, 530, mPaint);
        canvas.drawText("ICS", 355, 530, mPaint);
        canvas.drawText("JB", 460, 530, mPaint);
        canvas.drawText("KitKat", 540, 530, mPaint);
        canvas.drawText("L", 670, 530, mPaint);
        canvas.drawText("M", 770, 530, mPaint);


        //画文字
        mPaint.setTextSize(50);
        canvas.drawText("直方图", 450, 670, mPaint);
    }
}
