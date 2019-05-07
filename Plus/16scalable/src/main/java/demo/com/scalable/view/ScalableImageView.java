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
import android.view.View;
import android.widget.OverScroller;

import demo.com.library.Util;


//方法关键方法：动画
//移动关键方法：translation
//惯性滑动关键方法：.OverScroller.fling();
public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {

    private static final String TAG = ScalableImageView.class.getSimpleName();
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
    private float scaleRate;//放大率
    GestureDetectorCompat detector;
    ObjectAnimator animation;
    OverScroller scroller;
    public ScalableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = Util.getBitmap();
        detector = new GestureDetectorCompat(context,this);
        detector.setOnDoubleTapListener(this);
        animation = ObjectAnimator.ofFloat(this,"scaleRate",0f,1f);
        scroller = new OverScroller(context);
    }

    public float getScaleRate() {
        return scaleRate;
    }
    public void setScaleRate(float scaleRate) {
        this.scaleRate = scaleRate;
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
        bigScale *= OVER_SCALE_RATE;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        reviseXY();
        canvas.translate(offsetX * scaleRate,offsetY * scaleRate);//scaleRate 保证缩小以后回到原位
        float scale = smallScale + (bigScale - smallScale) * scaleRate;
        canvas.scale(scale,scale,getWidth()/2f,getHeight()/2f);
        canvas.drawBitmap(bitmap,left,top,paint);
    }


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
        postOnAnimation(this);
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
