package com.hencoder.hencoderpracticedraw7;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    public static Context getContext() {
        return context;
    }
    private static Context context;
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
    }

}
