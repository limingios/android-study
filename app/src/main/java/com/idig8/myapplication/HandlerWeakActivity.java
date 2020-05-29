package com.idig8.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.lang.ref.WeakReference;


public class HandlerWeakActivity extends AppCompatActivity {

    TextView mCountDownTimeTextView;
    /**
     * 倒計時標記 handler code
     */
    public static final int COUNT_TIME_CODE=10004;
    /**
     * 倒計時間隔
     */
    public static final int DELAY_MILLIS=1000;
    /**
     * 倒計時最大值
     */
    public static final int MAX_COUNT=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_weak);

        mCountDownTimeTextView = (TextView)findViewById(R.id.textViewHandler);

        CountDownTimeHandler handler =new CountDownTimeHandler(this);

        Message message = Message.obtain();
        message.what = COUNT_TIME_CODE;
        message.arg1=MAX_COUNT;
        handler.sendMessageDelayed(message,DELAY_MILLIS);

    }

    public static class CountDownTimeHandler extends Handler{
        static final int MIN_COUNT = 0;
        final WeakReference<HandlerWeakActivity> mWeakReference;

        CountDownTimeHandler(HandlerWeakActivity handlerWeakActivity) {
            mWeakReference =new  WeakReference<>(handlerWeakActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            HandlerWeakActivity handlerWeakActivity =mWeakReference.get();

            switch (msg.what){
                case COUNT_TIME_CODE:
                    int value =msg.arg1;
                    handlerWeakActivity.mCountDownTimeTextView.setText(String.valueOf(value--));
                    if(value>=MIN_COUNT){
                        Message message = Message.obtain();
                        message.what = COUNT_TIME_CODE;
                        message.arg1=value;
                        sendMessageDelayed(message,DELAY_MILLIS);
                    }
                    break;
            }

        }
    }
}
