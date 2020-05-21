package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.idig8.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        linearLayoutShow();
        //文件存在 R找不到也可能是缓存的问题，AS点file->invalidate Caches
//        setContentView(R.layout.chatting_linearlayout);
        setContentView(R.layout.login_linearlayout);
    }

    /**
     * 代码演示线性布局，代码编写不直观，用到的代码量大。
     * 界面效果和逻辑控制需要分开管理，用来设置界面的需要专门放到界面的文件中
     * 用来写文件控制的就专门写逻辑控制的。
     *
     * 如果将界面代码和逻辑控制代码都丢到这一个java中，那以后需要维护就代码就非常的大。
     * 因此单独的控制就用xml来控制。最好还是使用xml来控制。
     */
    private void linearLayoutShow(){
        //1.根布局为线性布局
        LinearLayout ll = new LinearLayout(this);
        //2.设置宽高
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        //3.背景设为红色
        ll.setBackgroundColor(Color.RED);
        setContentView(ll);
    }
}
