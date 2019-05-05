package demo.com.taglayout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.Random;

import demo.com.library.Util;
import demo.com.taglayout.R;

public class ColorTextView extends AppCompatTextView {


    private Paint mPaint;
    private static final Random mRandom = new Random();
    private static final float RADIUS = Util.dpToPixel(4);
    private static final int TEXT_SIZE_M = (int)Util.dpToPixel(20);
    private static final int TEXT_SIZE_H = (int)Util.dpToPixel(28);

    boolean mIsUnderline;//是否有下划线
    //背景颜色
    private static final int[] COLORS = {
            Color.parseColor("#E91E63"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548")
    };
    //对外提供的参数
    private boolean isSelected = false;
    private String text;
    public boolean getSelected() {
        return isSelected;
    }

    public ColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public void initView(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ColorTextView);
        mIsUnderline = typedArray.getBoolean(R.styleable.ColorTextView_isUnderline,false);
        typedArray.recycle();
        //设置下划线
        getPaint().setUnderlineText(mIsUnderline);
        //设置圆弧背景
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(0);
        //获取文本
        text = getText().toString();
        getPaint().setTextSize(TEXT_SIZE_M);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
            return true;
        }
        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            if(!isSelected){
                isSelected = true;
                //修改背景颜色
                mPaint.setColor(COLORS[mRandom.nextInt(COLORS.length)]);
                Util.toast("select " + text);
            }else {
                isSelected = false;
                mPaint.setColor(Color.WHITE);
                Util.toast("deselect " + text);
            }
            invalidate();
        }
        return false;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), RADIUS, RADIUS, mPaint);
        super.onDraw(canvas);
    }
}
