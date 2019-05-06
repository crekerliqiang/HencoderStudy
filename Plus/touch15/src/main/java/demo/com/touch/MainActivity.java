package demo.com.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import demo.com.library.LLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int e = ev.getActionMasked();
        boolean b = super.dispatchTouchEvent(ev);
        LLog.d(LLog.C_TAG,"activity dispatchTouchEvent " + e + " return " + b);
        return b;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int e = event.getActionMasked();
        boolean b = super.onTouchEvent(event);
        LLog.d(LLog.C_TAG,"activity onTouchEvent " + e + " return " + b);
        return b;
    }

}
