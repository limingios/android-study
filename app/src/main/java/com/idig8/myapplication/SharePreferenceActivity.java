package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SharePreferenceActivity extends AppCompatActivity {
    private EditText accEdt,pwdEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        accEdt = findViewById(R.id.acc_edt);
        pwdEdt = findViewById(R.id.pwd_edt);
        //①获取SharePreference对象(参数1：文件名  参数2：模式)
        SharedPreferences share =getSharedPreferences("myshare",MODE_PRIVATE);
        String account = share.getString("account","");
        String pwd = share.getString("pwd","");

        accEdt.setText(account);
        pwdEdt.setText(pwd);

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //1.获取两个输入框的内容
                String account = accEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                //2.验证(admin  123)
                if(account.equals("admin") && pwd.equals("123")){
                    //2.1存储信息到SharePreference
                    //①获取SharePreference对象(参数1：文件名  参数2：模式)
                    SharedPreferences share =getSharedPreferences("myshare",MODE_PRIVATE);

                    //②获取Editor对象
                    SharedPreferences.Editor edit = share.edit();
                    edit.putString("account",account);
                    edit.putString("pwd",pwd);
                    edit.commit();

                    Toast.makeText(SharePreferenceActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else {
                    //2.2验证失败，提示用户
                    Toast.makeText(SharePreferenceActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
