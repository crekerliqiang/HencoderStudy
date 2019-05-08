package demo.com.multitouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.com.library.Library;
import demo.com.library.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Library.init(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
