package com.idig8.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class OptionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);

        //演示ContenxtMenu
        // 1. 注册
        // registerForContextMenu(findViewById(R.id.contentBtn));
        // 2. 创建 onCreateContextMenu
        // 3. 菜单项的操作 onOptionsItemSelected
        // 4. 为按钮设置上下文操作模式
        // ① 实现 ActionMode CallBack
        // ② 在实现view的长按事件中去启动上下文操作模式
        findViewById(R.id.contentBtn).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                 startActionMode(cb);
                 return false;
            }
        });

        final Button popupBtn = findViewById(R.id.popupMenu);
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //① 实例化popupmenu对象（参数2：被指定的view）
                PopupMenu menu = new PopupMenu(OptionMenuActivity.this,popupBtn);
                // 加载菜单资源：利用MenuInflater 将Menu 资源加载到PopupMenu.getMenu（）所返回的Menu对象中
                // 将R.menu.xx对应的菜单资源到弹出式菜单中。
                menu.getMenuInflater().inflate(R.menu.popup,menu.getMenu());
                //设置菜单项的点击监听器
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.copy:
                                Toast.makeText(OptionMenuActivity.this,"复制",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.paste:
                                Toast.makeText(OptionMenuActivity.this,"粘贴",Toast.LENGTH_LONG).show();
                                break;
                        }
                        return false;
                    }
                });

                // 千万不要忘记这一步
                menu.show();

            }
        });

    }

    ActionMode.Callback cb = new ActionMode.Callback() {
        // 创建，在启动上下文操作模式（startActionMode(Callback)）时调用
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.e("TAG","创建");
            getMenuInflater().inflate(R.menu.context,menu);
            return true;
        }


        // 在创建方法后进行调用
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.e("TAG","准备");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.e("TAG","点击");
            switch (item.getItemId()){
                case R.id.delete:
                    Toast.makeText(OptionMenuActivity.this,"删除",Toast.LENGTH_LONG).show();
                    break;
                case R.id.operator1:
                    Toast.makeText(OptionMenuActivity.this,"操作1",Toast.LENGTH_LONG).show();
                    break;
                case R.id.operator2:
                    Toast.makeText(OptionMenuActivity.this,"操作2",Toast.LENGTH_LONG).show();
                    break;
            }
            return true;
        }

        //上下文操作模式结束时被调用
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.e("TAG","结束");
        }
    };

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
