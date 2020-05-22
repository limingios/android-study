package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        //被点击的时候触发的事件
        // 内部方法
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new MyClickListener());

        // 匿名内部类，方便查看
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在控制台输出一条语句
                Log.e("TAG","刚点击的按钮时，注册了【匿名内部类】监听器对象的按钮");
            }
        });

        // 接口的方式，通过this引入自身的方式
        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
            // 在控制台输出一条语句
            Log.e("TAG","刚点击的按钮时，通过【实现接口的方式】监听器对象的按钮");

    }

    public void myClick(View v){
        switch (v.getId()){
            case R.id.btn5:
                Log.e("TAG","onclick名称相同，id===5");
                break;
            case R.id.btn6:
                Log.e("TAG","onclick名称相同，id===6");
                break;
        }
    }

    //有点击事件的不光是按钮，文本也是可以有点击事件的，被点击的控件对象。
    public void xmlClick(View v){
        // 在控制台输出一条语句
        Log.e("TAG","刚点击的按钮时，通过【xml定义方法名的方式】监听器对象的按钮");
    }

    class  MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // 在控制台输出一条语句
            Log.e("TAG","刚点击的按钮时，注册了【内部类】监听器对象的按钮");
        }
    }
}
