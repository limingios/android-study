package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.idig8.myapplication.fragment.FindFragment;
import com.idig8.myapplication.fragment.MainFragment;
import com.idig8.myapplication.fragment.MeFragment;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    protected LinearLayout mMainLine;
    protected LinearLayout mFindLine;
    protected LinearLayout mMeLine;
    protected MainFragment mMainFragment = new MainFragment(); //首页
    protected FindFragment mFindFragment = new FindFragment(); //发现
    protected MeFragment mMeFragment = new MeFragment(); //我的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        // 获取管理类
        this.getSupportFragmentManager()
                .beginTransaction()
        // 事物添加 默认：显示首页，其他的页面隐藏
                .add(R.id.container_context,mMainFragment)
                .add(R.id.container_context,mFindFragment)
                .hide(mFindFragment)
                .add(R.id.container_context,mMeFragment)
                .hide(mMeFragment)
        // 提交
        .commit();

    }

    private void initView(){
        mMainLine = this.findViewById(R.id.menu_main);
        mFindLine = this.findViewById(R.id.menu_find);
        mMeLine = this.findViewById(R.id.menu_me);
        mMainLine.setOnClickListener(this);
        mFindLine.setOnClickListener(this);
        mMeLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_main://首页
                // 获取管理类
                this.getSupportFragmentManager()
                        .beginTransaction()
                        // 事物添加 默认：显示首页，其他的页面隐藏
                        .show(mMainFragment)
                        .hide(mFindFragment)
                        .hide(mMeFragment)
                        // 提交
                        .commit();
                break;
            case R.id.menu_find://发现
                this.getSupportFragmentManager()
                        .beginTransaction()
                        // 事物添加 默认：显示首页，其他的页面隐藏
                        .hide(mMainFragment)
                        .show(mFindFragment)
                        .hide(mMeFragment)
                        // 提交
                        .commit();
                break;
            case R.id.menu_me://我的
                this.getSupportFragmentManager()
                        .beginTransaction()
                        // 事物添加 默认：显示首页，其他的页面隐藏
                        .hide(mMainFragment)
                        .hide(mFindFragment)
                        .show(mMeFragment)
                        // 提交
                        .commit();
                break;
        }
    }
}
