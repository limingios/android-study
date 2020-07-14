package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void toRegister(View w){
        //注册界面
        Intent login = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(login);
    }
}
