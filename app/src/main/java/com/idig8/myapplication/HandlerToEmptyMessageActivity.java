package com.idig8.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HandlerToEmptyMessageActivity extends AppCompatActivity {

    private static final String TAG = "HandlerToEmptyMessageActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_to_empty_message);

        final TextView hellowordTextView = findViewById(R.id.helloword);
        //创建 Handler 处理消息
        final Handler handler =new Handler(){
            @SuppressLint("LongLogTag")
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG,"handleMessage:"+msg.what);

                // 主线程接到子线程发出的消息，进行处理
                if(msg.what == 1001){
                    hellowordTextView.setText("handler通过子线程改变textView内容");
                }

                if(msg.what == 1002){
                    Log.d(TAG,"HandlerToEmptyMessageActivity:"+msg.arg1);
                    Log.d(TAG,"HandlerToEmptyMessageActivity:"+msg.arg2);
                }
            }
        };



        findViewById(R.id.handlerbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 有可能做大量的耗时操作
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // 通过UI更新
                        handler.sendEmptyMessage(1001);


                        // 发送多个内容
                        Message message = Message.obtain();
                        message.what=1002;
                        message.arg1=1003;
                        message.arg2=1004;
                        message.obj = HandlerToEmptyMessageActivity.this;
                        handler.sendMessage(message);

//                        // 发送定时
//                        handler.sendMessageAtTime(message, SystemClock.uptimeMillis()+3000);
//
//                        // 2秒之后发送
//                        handler.sendMessageDelayed(message,2000);
//
//                        //直接把这个进程发出去
//                        final Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                int a = 1+2+3;
//                            }
//                        };
//                        handler.post(runnable);
//                        handler.postDelayed(runnable,2000);
                    }
                }).start();
            }
        });

    }
}
