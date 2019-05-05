package demo.com.touch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
