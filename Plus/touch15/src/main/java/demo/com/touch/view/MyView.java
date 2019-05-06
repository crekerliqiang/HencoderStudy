package demo.com.touch.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import demo.com.library.LLog;
import demo.com.library.Util;

public class MyView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.RED);
        setBackgroundColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle((float) getWidth()/2,(float) getHeight()/2,
                (float)getWidth()/2,mPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        LLog.d(LLog.C_TAG,"view dispatchTouchEvent " + ev.getActionMasked() + " return " + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        LLog.d(LLog.C_TAG,"view onTouchEvent " + event.getActionMasked() + " return " + b);
        return b;
    }


}
