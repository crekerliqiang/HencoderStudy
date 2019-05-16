package demo.com.library.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import demo.com.library.LLog;

public class MyViewGroup extends ViewGroup {

    private static ArrayList<Rect> mChildBounds = new ArrayList<>();
    private static Rect mRect;

    private static final String TAG = MyViewGroup.class.getSimpleName();

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int lineWidthUsed = 0;
        int lineHeightUsed = 0;
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        for(int i = 0;i < getChildCount();i++){
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, lineWidthUsed, heightMeasureSpec,0);

            //写入数据
            mRect = new Rect();
            mRect.set(lineWidthUsed,0,
                    lineWidthUsed + child.getMeasuredWidth(),child.getMeasuredHeight());
            mChildBounds.add(mRect);

            LLog.d(TAG,mRect);

            lineHeightUsed = Math.max(lineHeightUsed,child.getMeasuredHeight());

            lineWidthUsed += child.getMeasuredWidth();


        }


        setMeasuredDimension(lineWidthUsed, lineHeightUsed);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for(int i = 0;i < getChildCount();i++){
            View child = getChildAt(i);
            Rect childBound = mChildBounds.get(i);
            child.layout(childBound.left, childBound.top, childBound.right, childBound.bottom);
        }


    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
