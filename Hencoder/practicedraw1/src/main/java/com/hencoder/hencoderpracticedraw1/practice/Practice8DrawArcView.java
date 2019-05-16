package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    private Paint mPaint = new Paint();
    private RectF mRectF = new RectF(200, 100, 700, 400);


    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.ianshi() 方法画弧形和扇形
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(mRectF, -110, 100, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawArc(mRectF, 20, 140, false, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(mRectF, 180, 60, false, mPaint);
    }
}
