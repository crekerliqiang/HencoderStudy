package demo.com.scalable.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;
import android.widget.Toast;

import demo.com.library.LLog;
import demo.com.library.Util;

//TODO 加入双指缩放以后，变得不是很流畅，需要改进地方
public class ScalableImageView extends View{
    private static final float OVER_SCALE_RATE = 3f;
    private Bitmap bitmap;
    private Paint paint;
    private float left;
    private float top;
    private float offsetX ;
    private float offsetY ;
    boolean isBig;
    private float smallScale;
    private float bigScale;
    private float scaleMultiple;//
    //点击检测
    private GestureDetectorCompat detector;
    //属性动画
    private ObjectAnimator animation;
    //惯性滑动
    private OverScroller scroller;
    //点击检测和惯性滑动的监听
    private MyGestureListener myGestureListener;
    //
    private MyRunnable runnable;
    //双指缩放
    ScaleGestureDetector scaleGestureDetector;
    public ScalableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = Util.getBitmap();
        myGestureListener = new MyGestureListener();
        runnable = new MyRunnable();
        detector = new GestureDetectorCompat(context, myGestureListener);
        detector.setOnDoubleTapListener(myGestureListener);
        animation = ObjectAnimator.ofFloat(this,"scaleMultiple",1);
        scroller = new OverScroller(context);
        scaleGestureDetector = new ScaleGestureDetector(context,new ScaleListener());
    }

    public float getScaleMultiple() {
        return scaleMultiple;
    }
    public void setScaleMultiple(float scaleMultiple) {
        this.scaleMultiple = scaleMultiple;
        invalidate();
    }
    //说明：使用该方法，可以在获取View的宽度，相当于在onDraw里面使用getWidth
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        left = (getWidth() - bitmap.getWidth()) / 2f;
        top = (getHeight() - bitmap.getHeight()) / 2f;
        if(getHeight()/bitmap.getHeight() > getWidth()/bitmap.getWidth()){
            //图片比较胖
            smallScale = (float) getWidth() / (float)bitmap.getWidth();
            bigScale = (float) getHeight() / (float)bitmap.getHeight();
        }else{
            smallScale = (float) getHeight() / (float)bitmap.getHeight();
            bigScale = (float) getWidth() / (float)bitmap.getWidth();
        }
        scaleMultiple = smallScale;
        animation.setFloatValues(smallScale,bigScale);
        bigScale *= OVER_SCALE_RATE;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //发生冲突时，优先使用 scaleGestureDetector
        boolean result = scaleGestureDetector.onTouchEvent(event);
        if(!scaleGestureDetector.isInProgress()){
            result = detector.onTouchEvent(event);
        }
        return result;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        reviseXY();
        float rate =  (scaleMultiple-smallScale)/(bigScale-smallScale);
        canvas.translate(offsetX * rate,offsetY * rate);//scaleRate 保证缩小以后回到原位
        canvas.scale(scaleMultiple,scaleMultiple,getWidth()/2f,getHeight()/2f);
        canvas.drawBitmap(bitmap,left,top,paint);
    }

    //点击检测以及惯性滑动
    private class MyGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
        //DOWN
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        //滑动
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(isBig){
                offsetX = offsetX - distanceX;
                offsetY = offsetY - distanceY;
                invalidate();
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        //惯性滑动
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            scroller.fling((int)offsetX,(int)offsetY,(int)velocityX,(int)velocityY,
                    -(int)(bitmap.getWidth()*bigScale - getWidth()),
                    (int)(bitmap.getWidth()*bigScale - getWidth()),
                    -(int)(bitmap.getHeight()*bigScale - getHeight()),
                    (int)(bitmap.getHeight()*bigScale - getHeight()));
            postOnAnimation(runnable);
            return false;
        }

        //确认单击[第一次按下后，一段时间内(1500ms貌似)没有第二次点击]
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        //双击
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            isBig = !isBig;
            if(isBig){
                //点击放大时，点到哪里就从哪里放大
                //可以假设Y的偏移为0 辅助理解
                offsetX = (getWidth() / 2f - e.getX()) * bigScale / smallScale - (getWidth() / 2f - e.getX());
                offsetY = (getHeight() / 2f - e.getY()) * bigScale / smallScale - (getHeight() / 2f - e.getY());
                animation.start();
            }else{
                animation.reverse();
            }
            return false;
        }

        //双击 同时还检测双击后的事件
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }

    }
    //惯性滑动的辅助类
    private class MyRunnable implements Runnable{
        @Override
        public void run() {
            if(scroller.computeScrollOffset()){
                offsetX = scroller.getCurrX();
                offsetY = scroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }
        }
    }

    //双指缩放
    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener{
        private float preScaleRate;
        //缩放过程
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float f = detector.getScaleFactor();
            scaleMultiple = preScaleRate * f;
            isBig = f > 1f ;
            invalidate();
            return false;
        }

        //缩放开始
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            //要return 才能调用 onScale
            preScaleRate = scaleMultiple;
            return true;
        }
        //缩放结束
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
    /**
     * 给x y 的偏移做限制，防止图片划出屏幕
     */
    private void reviseXY(){
        final float over = Util.dpToPixel(0);
        //往右滑[负数取最大值 正数取最小值 往中间走]
        offsetX = Math.min(offsetX ,(bitmap.getWidth()*bigScale - getWidth()) / 2 + over);
        //往右滑
        offsetX = Math.max(offsetX ,-(bitmap.getWidth()*bigScale - getWidth()) / 2 - over);
        //
        offsetY = Math.min(offsetY ,(bitmap.getHeight()*bigScale - getHeight()) / 2 + over);
        //
        offsetY = Math.max(offsetY ,-(bitmap.getHeight()*bigScale - getHeight()) / 2 - over);
    }


}
