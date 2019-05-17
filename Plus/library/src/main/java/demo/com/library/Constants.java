package demo.com.library;

public class Constants {
    //文字中x y的偏移量
    public static final int TEXT_OFFSET_X = (int) Util.dpToPixel(5);
    public static final int TEXT_OFFSET_Y = (int)Util.dpToPixel(5);
    //文字滑动的阈值
    //从右往左滑动时，scaleRatioX 的范围是 1--0 ；当 scaleRatioX 小于该阈值时，开启动画
    public static final float SCALE_RATIO_LEFT_X_THRESHOLD = 0.8f;
    public static final float SCALE_RATIO_RIGHT_X_THRESHOLD = 0.2f;
}
