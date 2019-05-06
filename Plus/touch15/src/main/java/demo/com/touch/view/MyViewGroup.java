package demo.com.touch.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import demo.com.library.LLog;

public class MyViewGroup extends ViewGroup {

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i = 0;i < getChildCount();i++){
            View child = getChildAt(i);
            if(i ==0)child.layout(l,t,r/2,b/2);
            if(i ==1)child.layout(r/2,t,r,b/2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        LLog.d(LLog.C_TAG,"group dispatchTouchEvent " + ev.getActionMasked() + " return " + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        LLog.d(LLog.C_TAG,"group onTouchEvent " + event.getActionMasked() + " return " + b);

        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);
        LLog.d(LLog.C_TAG,"group onInterceptTouchEvent " + ev.getActionMasked() + " return " + b);
        return b;
    }
}
