package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.idig8.myapplication.receiver.IdigBroadcaseReceiver;

public class IdigBroadcaseActivity extends AppCompatActivity {
    public static final String MY_ACTION = "com.idig8.myapplication.afdsabfdaslj";
    public static final String BROADCAST_CONTENT = "broadcast_content";
    private IdigBroadcaseReceiver broadcaseReceiver;
    private EditText mInputEditText;
    private Button mSendBroadcastButton;
    private TextView mResultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idig_broadcase);


        // 用包名做title
        setTitle(getPackageName());

        mInputEditText = findViewById(R.id.inputEditText);
        mSendBroadcastButton = findViewById(R.id.sendBroadcastButton);
        mResultTextView = findViewById(R.id.resultTextView);



        // 新建广播接收器
        broadcaseReceiver = new IdigBroadcaseReceiver(mResultTextView);

        // 注册广播接收器

        // 为广播接收器添加Action
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction(MY_ACTION);

        // 注册广播接收器
        registerReceiver(broadcaseReceiver, intentFilter);


        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 新建广播
                Intent intent = new Intent(MY_ACTION);
                // 放入广播要携带的数据
                intent.putExtra(BROADCAST_CONTENT, mInputEditText.getText().toString());
                sendBroadcast(intent);
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcaseReceiver !=null){
            unregisterReceiver(broadcaseReceiver);
        }
    }
}
