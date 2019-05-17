package demo.com.library.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import demo.com.library.Constants;
import demo.com.library.LLog;
import demo.com.library.Util;

import static demo.com.library.Constants.SCALE_RATIO_LEFT_X_THRESHOLD;
import static demo.com.library.Constants.SCALE_RATIO_RIGHT_X_THRESHOLD;


public class SlideView extends View {

    private static final String TAG = SlideView.class.getSimpleName();

    private Paint paintBackground1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintBackground2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintBackground3 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
    //触屏监听
    private MyGestureListener gestureListener;
    //触屏检测
    private GestureDetectorCompat detectorCompat;
    //滑动效果的动画
    ObjectAnimator animator = ObjectAnimator.ofFloat(this,"scaleRatioX",1f);
    boolean isAnimatorStart = false;
    //结束监听

    //水平方向的缩放比例
    float scaleRatioX = 1.0f;
    public float getScaleRatioX() {
        return scaleRatioX;
    }
    public void setScaleRatioX(float scaleRatioX) {
        this.scaleRatioX = scaleRatioX;
        invalidate();
    }


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
        //初始化点击监听
        gestureListener = new MyGestureListener();
        detectorCompat = new GestureDetectorCompat(context,gestureListener);
        //动画结束监听
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimatorStart = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth()/5;
        //绘制区域1
        canvas.save();
        canvas.scale(1 + scaleRatioX * 2, 1,0,getHeight()>>1);
        canvas.drawRect(0,0,width*3,getHeight(),paintBackground1);
        canvas.restore();
        canvas.drawText("微信好友", Constants.TEXT_OFFSET_X,
                getHeight()- Constants.TEXT_OFFSET_Y, paintText);
        //绘制区域2
        canvas.save();
        canvas.scale(1 - scaleRatioX, 1,getWidth(),getHeight()>>1);
        canvas.drawRect(width*3,0,width * 4,getHeight(),paintBackground2);
        canvas.drawText("未读", Constants.TEXT_OFFSET_X + width * 3,
                getHeight()- Constants.TEXT_OFFSET_Y, paintText);
        canvas.restore();
        //绘制区域3
        canvas.save();
        canvas.scale(1 - scaleRatioX, 1,getWidth(),getHeight()>>1);
        canvas.drawRect(width * 4,0,width * 5,getHeight(),paintBackground3);
        canvas.drawText("删除", Constants.TEXT_OFFSET_X + width * 4,
                getHeight()- Constants.TEXT_OFFSET_Y, paintText);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detectorCompat.onTouchEvent(event);
    }

    public class MyGestureListener implements GestureDetector.OnGestureListener{

        public boolean onDown(MotionEvent e) {
            LLog.d(TAG,"down " + e.getX());
            //返回true 表示接收该事件
            return true;
        }

        public void onShowPress(MotionEvent e) {

        }

        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        //滑动
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float s = scaleRatioX;
            scaleRatioX = scaleRatioX - distanceX/getWidth();
            if(scaleRatioX < 0f)scaleRatioX = 0f;
            if(scaleRatioX > 1f)scaleRatioX = 1f;
            LLog.d(TAG,"dx " + distanceX + " scale " + s + ":" + scaleRatioX );

            //1.(scaleX > hold && 右滑) --> 动画：scaleX = hold:1
            if(scaleRatioX > SCALE_RATIO_RIGHT_X_THRESHOLD && distanceX < 0 && !isAnimatorStart && Math.abs(scaleRatioX - 1.0f) > 0.1f){
                isAnimatorStart = true;
                animator.setFloatValues(SCALE_RATIO_RIGHT_X_THRESHOLD,1f);
                animator.start();
            }else  if(scaleRatioX < SCALE_RATIO_LEFT_X_THRESHOLD && distanceX > 0 && !isAnimatorStart && Math.abs(scaleRatioX - 0f) > 0.1f){
            //2.(scaleX < hold && 左滑) --> 动画：scaleX = hold : 0
                isAnimatorStart = true;
                animator.setFloatValues(SCALE_RATIO_LEFT_X_THRESHOLD,0f);
                animator.start();
            }else{
                invalidate();
            }
            return false;
        }

        public void onLongPress(MotionEvent e) {

        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

}
