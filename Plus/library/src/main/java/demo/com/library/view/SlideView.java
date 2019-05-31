package demo.com.library.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import demo.com.library.LLog;
import demo.com.library.Util;


public class SlideView extends View {

    private static final String TAG = SlideView.class.getSimpleName();

    Paint paintBackground1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paintBackground2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paintBackground3 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
    final int offsetX = (int) Util.dpToPixel(5);
    final int offsetY = (int)Util.dpToPixel(5);

    float scaleX = 1.0f;


    public SlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化背景色
        paintBackground1.setColor(0xFFDBC8FF);
        paintBackground2.setColor(0xFF9FFF3C);
        paintBackground3.setColor(0xFF5CFFD3);
        //文字规则
        paintText.setColor(Color.BLACK);
        paintText.setStrokeWidth(Util.dpToPixel(4));
        paintText.setTextSize(Util.dpToPixel(30));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth()/3;
        //绘制区域1
        canvas.save();
        canvas.scale(1 + scaleX * 2, 1,0,getHeight()>>1);
        canvas.drawRect(0,0,width,getHeight(),paintBackground1);
        canvas.restore();
        canvas.drawText("微信好友", offsetX, getHeight()-offsetY, paintText);
        //绘制区域2
        canvas.save();
        canvas.scale(1 - scaleX, 1,getWidth(),getHeight()>>1);
        canvas.drawRect(width,0,width * 2,getHeight(),paintBackground2);
        canvas.drawText("未读", offsetX + width, getHeight()-offsetY, paintText);
        canvas.restore();
        //绘制区域3
        canvas.save();
        canvas.scale(1 - scaleX, 1,getWidth(),getHeight()>>1);
        canvas.drawRect(width * 2,0,width * 3,getHeight(),paintBackground3);
        canvas.drawText("删除", offsetX + width * 2, getHeight()-offsetY, paintText);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){

            case MotionEvent.ACTION_MOVE:

                scaleX = event.getX()/getWidth();
                LLog.d(TAG,"scase X " + scaleX);
                invalidate();
                break;

        }



        return true;
    }
}
