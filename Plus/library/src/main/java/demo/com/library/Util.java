package demo.com.library;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;
import android.widget.Toast;

public class Util {

    public static void toast(String s){
        Toast.makeText(Library.getmContext(),s,Toast.LENGTH_SHORT).show();
    }
    public static float dpToPixel(float dp) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
    public static Bitmap getBitmap(int id){
        return BitmapFactory.decodeResource(Library.getmContext().getResources(),id);
    }
    public static Bitmap getBitmap(){
        return getBitmap(R.drawable.crekerli_doraemon);
    }
    public static Bitmap getBitmap(int id, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(Library.getmContext().getResources(), id, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(Library.getmContext().getResources(),id, options);
    }

}
