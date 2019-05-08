package demo.com.multitouch.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import demo.com.library.Util;

public class MultipleTouch1 extends View {

    private Bitmap bitmap;
    private Paint paint;
    private float offsetX ;
    private float offsetY ;
    private float downX;
    private float downY;
    private float imageOffsetX;
    private float imageOffsetY;
    /**
     * 追踪手指的id
     * id在滑动过程中是不变的[这里的不变是指：AB手指在交替按下时，原放在屏幕上的手指的index会在新的手指按下时候被改变，但是id不会变]
     * 核心在于每次手指放下或者抬起时，将 id 赋值给 trackingPointerId ，然后在MOVE时，使用 trackingPointerId 去获取index，从而获取坐标
     */
    private int trackingPointerId = 0;
    public MultipleTouch1(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = Util.getBitmap(Util.dpToPixel(200));
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        correctXY();
        canvas.drawBitmap(bitmap,offsetX,offsetY,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX(0);
                downY = event.getY(0);
                trackingPointerId = event.getPointerId(0);
                break;
            case MotionEvent.ACTION_MOVE:
                //移动的时候判断监视的手指index是多少 [使用id获取index ]
                int index = event.findPointerIndex(trackingPointerId);
                offsetX = event.getX(index) - downX + imageOffsetX;
                offsetY = event.getY(index) - downY + imageOffsetY;
                invalidate();
                break;
                case MotionEvent.ACTION_UP:
                    imageOffsetX = offsetX;
                    imageOffsetY = offsetY;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    //另外的手指按下  获取手指的index，用index获取id
                    int actionIndex = event.getActionIndex();
                    trackingPointerId = event.getPointerId(actionIndex);
                    downX = event.getX(actionIndex);
                    downY = event.getY(actionIndex);
                    imageOffsetX = offsetX;
                    imageOffsetY = offsetY;
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    //非最后一根手指抬起 需要移交屏幕的所有权
                    actionIndex = event.getActionIndex();
                    int pointerId = event.getPointerId(actionIndex);
                    int newIndex;
                    if(pointerId == trackingPointerId){
                        if(actionIndex == event.getPointerCount() - 1){
                            newIndex = event.getPointerCount() - 2;
                        }else {
                            newIndex = event.getPointerCount() - 1;
                        }
                        //将所有权交给前一根手指
                        trackingPointerId = event.getPointerId(newIndex);
                        downX = event.getX(newIndex);
                        downY = event.getY(newIndex);
                        imageOffsetX = offsetX;
                        imageOffsetY = offsetY;
                    }
             default:break;
        }
        return true;
    }
    //矫正 offsetX Y 防止滑出
    private void correctXY(){
        //防止偏移出去
        int buf = getWidth() - bitmap.getWidth();
        offsetX = offsetX > buf ? buf : offsetX;
        buf = getHeight() - bitmap.getHeight();
        offsetY = offsetY > buf ? buf : offsetY;

        offsetX = offsetX < 0 ? 0 : offsetX;
        offsetY = offsetY < 0 ? 0 : offsetY;
    }
}
