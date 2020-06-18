package com.idig8.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        if(getIntent()!=null){
            Log.d("DialogActivity",getIntent().getStringExtra("abc"));
            Bundle bundle  = getIntent().getBundleExtra("ccc");
            Log.d("DialogActivity",bundle.getString("aaa"));

            User u = (User)getIntent().getSerializableExtra("ddd");
            Log.d("DialogActivity",u.getUsername());
        }

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

    // 设置PopupWindow
    // 1. 实例化对象
    // 2. 设置
    // 3. 显示
    public void clickshowPopupWindowBtn(View v){
        // 参数1：用在弹窗中的view

        // 当整个这个布局，view来进行处理。findById是内部的一个btn

        View vv = LayoutInflater.from(this).inflate(R.layout.activity_popup,null);
        //  实例化对象
        //  参数1：用在弹窗中的view
        //  参数2，3弹窗的宽高
        //  参数4（focusable）
        final PopupWindow window = new PopupWindow(vv,400,100,true);

        // 2. 设置（背景、动画）
        // 设置背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 设置能响应外部的点击事件
        window.setOutsideTouchable(true);

        // 设置能响应点击事件
        window.setTouchable(true);

        // 创建动画资源，创建一个style应用动画资源，对当前弹窗的动画风格设置
        window.setAnimationStyle(R.style.translate_anim);

        // 3. 显示
        // 参数1（anchor）:锚
        // 参数2，3：相对于描在x，y方向上的偏移量
        window.showAsDropDown(v,100,50);

        //为弹窗中的文本添加点击事件
        vv.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DialogActivity.this,"您点击了全选",Toast.LENGTH_SHORT).show();
                window.dismiss();   //控制弹窗消失
            }
        });

        vv.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DialogActivity.this,"您点击了复制",Toast.LENGTH_SHORT).show();
                // 控制弹窗消失
                window.dismiss();
            }
        });

        vv.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DialogActivity.this,"您点击了粘贴",Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
    }



    public void clickshowDataDialogBtnBtn(View v){
        final String[] items = {"Java","Mysql","Android","HTML","C","JavaScript"};
        //数组适配器
        //参数1：环境
        //参数2：布局资源索引，指的是每一项数据所呈现的样式android.R.layout.xxx
        //参数3：数据源
//       ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,items);
     ArrayAdapter adapter = new ArrayAdapter(this,R.layout.array_item_layout,R.id.item_txt,items);
       AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择")
                //参数1：适配器对象（对数据显示样式的规则制定器）
                //参数2：监听器
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this,items[i],Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });
        builder.show();
    }

}
