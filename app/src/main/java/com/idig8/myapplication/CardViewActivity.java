package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.idig8.myapplication.adapter.MsgAdapter;
import com.idig8.myapplication.model.Msg;
import com.idig8.myapplication.model.MsgLab;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity extends AppCompatActivity {

    private ListView mLvMsgList;
    private List<Msg> mDatas = new ArrayList<>();
    private MsgAdapter msgAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        mLvMsgList = findViewById(R.id.id_lv_msgList);

        mDatas.addAll(MsgLab.generateMockList());
        msgAdapter = new MsgAdapter(this,mDatas);
        mLvMsgList.setAdapter(msgAdapter);
    }
}
