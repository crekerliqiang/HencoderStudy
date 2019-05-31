package com.cerkerli.a10xfermode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demo.com.library.Library;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Library.init(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
