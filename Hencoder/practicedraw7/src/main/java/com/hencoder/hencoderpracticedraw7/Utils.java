package com.hencoder.hencoderpracticedraw7;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class Utils {
    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
    public static void toast(final String s){
        Toast.makeText(MyApp.getContext(),s,Toast.LENGTH_LONG).show();
    }
}
