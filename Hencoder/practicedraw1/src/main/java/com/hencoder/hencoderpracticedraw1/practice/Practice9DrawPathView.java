package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private Paint mPaint = new Paint();
    private Path mPath = new Path();

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);

        mPath.addArc(300, 200, 500, 400, 135, 225);
        mPath.lineTo(500, 600);
        mPath.addArc(500, 200, 700, 400, 180, 220);
        mPath.lineTo(500, 600);
        canvas.drawPath(mPath, mPaint);
    }
}
