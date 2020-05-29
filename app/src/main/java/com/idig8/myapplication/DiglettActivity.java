package com.idig8.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Random;

public class DiglettActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    public static final int CODE = 10;
    public static final int RANDOM_NUM = 500;
    private TextView mResultTextView;
    private ImageView mDiglettImageView;
    private Button mStartButton;
    public static int mTotalCount = 0;
    public static int MAX_COUNT = 10;
    public static int mSuccessCount = 0;

    private DiglettHandler mHandler = new DiglettHandler(this);

    public int[][] mPosition = new int[][]{
            {340,180},{432,880},
            {240,380},{232,280},
            {140,280},{12,88},
            {40,80},{832,380}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diglett);

        mResultTextView = findViewById(R.id.textView_play);
        mDiglettImageView = findViewById(R.id.imageView_play);
        mStartButton = findViewById(R.id.button_play);
        mStartButton.setOnClickListener(this);

        setTitle("打地鼠");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_play:
                start();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.setVisibility(View.GONE);
        mSuccessCount++;
        mResultTextView.setText("打到了"+mSuccessCount+"只，共"+MAX_COUNT+"只.");


        return false;
    }

    private void start() {
        //发送消息
        mResultTextView.setText("开始啦");
        mStartButton.setText("游戏中");
        mStartButton.setEnabled(false);
        next(0);
    }

    private void next(int delayTime) {
        //下一个
        int position=new Random().nextInt(mPosition.length);
        Message message = Message.obtain();
        message.what = CODE;
        message.arg1 = position;
        mHandler.sendMessageDelayed(message,delayTime);
        mTotalCount++;

    }



    public static class DiglettHandler extends Handler {
        final WeakReference<DiglettActivity> mWeakReference;

        DiglettHandler(DiglettActivity activity) {
            mWeakReference =new  WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            DiglettActivity handlerWeakActivity =mWeakReference.get();
            switch (msg.what){
                case CODE:

                    if(handlerWeakActivity.mTotalCount>MAX_COUNT){
                        handlerWeakActivity.clear();
                        Toast.makeText(handlerWeakActivity,"地鼠打完了",Toast.LENGTH_LONG).show();
                        return;
                    }
                    int position =msg.arg1;
                    handlerWeakActivity.mDiglettImageView.setX(handlerWeakActivity.mPosition[position][0]);
                    handlerWeakActivity.mDiglettImageView.setY(handlerWeakActivity.mPosition[position][1]);
                    handlerWeakActivity.mDiglettImageView.setVisibility(View.VISIBLE);
                    int randomTime = new Random().nextInt(RANDOM_NUM) + RANDOM_NUM;

                    handlerWeakActivity.next(randomTime);
                    break;
            }
        }
    }

    private void clear() {
        mTotalCount=0;
        mSuccessCount=0;
        mDiglettImageView.setVisibility(View.GONE);
        mStartButton.setText("点击开始");
        mStartButton.setEnabled(true);
    }
}
