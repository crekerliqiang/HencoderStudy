package demo.com.library;

import android.content.res.Resources;
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
}
