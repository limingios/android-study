
package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        //在android 4.0 不能直接线程中操作控件。一旦操作就会崩溃，但是进度条是一个特例。
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        progressBar.setProgress(30);
    }
}
