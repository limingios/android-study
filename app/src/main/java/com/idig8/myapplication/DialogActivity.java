package com.idig8.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void clickShowmyAlertDialogBtn(View v){
        // 实例化一个builder
        // AlertDialog的构造方法时protected
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置对话框样式，标题
        builder.setTitle("提示");
        // 提示语句
        builder.setMessage("您确定退出程序吗？");
        // 按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });
        builder.setNegativeButton("取消",null);
        // 展示
        builder.show();
        // show(); 等于下面的代码
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }

    public void clickShowNormalDialogBtn(View v){
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setMessage("您确定退出程序吗？");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.show();
    }


    public void clickshowDialogBtn(View v){
        MyDialog md = new MyDialog(this,R.style.mydialog);
        md.show();
    }




}
