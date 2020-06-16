package com.idig8.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class OptionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);

        //演示ContenxtMenu
        // 1. 注册
        registerForContextMenu(findViewById(R.id.contentBtn));
        // 2. 创建 onCreateContextMenu
        // 3. 菜单项的操作 onOptionsItemSelected


    }

    // 创建OptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载菜单资源
        getMenuInflater().inflate(R.menu.option,menu);
        return true;
    }

    //选中某个item后的效果
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                Toast.makeText(this,"保存",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"设置",Toast.LENGTH_LONG).show();
                break;
            case R.id.exit:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.context,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(this,"删除",Toast.LENGTH_LONG).show();
                break;
            case R.id.operator1:
                Toast.makeText(this,"操作1",Toast.LENGTH_LONG).show();
                break;
            case R.id.operator2:
                Toast.makeText(this,"操作2",Toast.LENGTH_LONG).show();
                break;
        }
       return true;
    }
}
