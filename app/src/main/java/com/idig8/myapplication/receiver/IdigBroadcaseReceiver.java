package com.idig8.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.idig8.myapplication.IdigBroadcaseActivity;

public class IdigBroadcaseReceiver extends BroadcastReceiver {

    private static final String TAG = "IdigBroadcaseReceiver";

    TextView mTextView;
    public IdigBroadcaseReceiver() {
    }

    public IdigBroadcaseReceiver(TextView textView) {
        mTextView = textView;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent!=null){
            String action = intent.getAction();
            Log.d(TAG,"onRecive:"+action);
            // 判断是什么广播（是不是我们自己发送的自定义广播）
            if(TextUtils.equals(action, IdigBroadcaseActivity.MY_ACTION)){
                // 获取广播携带的内容， 可自定义的数据
                String content = intent.getStringExtra(IdigBroadcaseActivity.BROADCAST_CONTENT);
                if(mTextView != null){
                    mTextView.setText("接收到的action是："+ action + "\n接收到的内容是：\n" + content);
                }
            }
        }
    }
}
