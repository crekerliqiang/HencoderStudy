package demo.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import demo.com.library.view.SlideView;
import demo.com.library.view.SlideViewOnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlideView view = findViewById(R.id.button);

        view.setMenuOnClickListener(new SlideViewOnClickListener() {
            @Override
            public void onClick(int id) {
                Executor executor;
                //1
                executor = Executors.newCachedThreadPool();
            }
        });
    }
}
