package demo.com.scalable.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import demo.com.library.Util;


//方法关键方法：动画
//移动关键方法：translation
//惯性滑动关键方法：.OverScroller.fling();
public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String TAG = ScalableImageView.class.getSimpleName();
    private static final int PIC_WIDTH = (int) Util.dpToPixel(200);
    private Bitmap bitmap;
    private Paint paint;
    private float left;
    private float top;
    float smallScale;
    float bigScale;
    GestureDetectorCompat detector;
    public ScalableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = Util.getBitmap();
        detector = new GestureDetectorCompat(context,this);
        detector.setOnDoubleTapListener(this);
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
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.scale(smallScale,smallScale,
                getWidth()/2f,getHeight()/2f);
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

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    //双击
    @Override
    public boolean onDoubleTap(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
