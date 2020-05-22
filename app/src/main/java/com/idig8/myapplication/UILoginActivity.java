package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class UILoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_i_login);
    }

    public void register(View v){
        //1. 判断姓名和密码是否为null
        //2. 如果都不空，则提示
        //3. 都不为空，则出现进度条
        EditText nameEdt = findViewById(R.id.username);
        EditText pwEdt = findViewById(R.id.password);
        final ProgressBar bar = findViewById(R.id.progess_bar);
        String nameText = nameEdt.getText().toString();
        String pwdText = pwEdt.getText().toString();


        if(("").equals(nameText)&&("").equals(pwdText)){
            Toast.makeText(this,"姓名和密码不为空",Toast.LENGTH_SHORT).show();
        }else {
            bar.setVisibility(View.VISIBLE);
            new Thread(){
                @Override
                public void run() {
                    for (int i=0;i<100;i++){
                        bar.setProgress(i);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

    }
}
