package com.idig8.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
    }

    public void toFrameActivity(View v){
        Intent intent = new Intent(LayoutActivity.this,FrameActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toTableActivity(View v){
        Intent intent = new Intent(LayoutActivity.this,TableActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toGridActivity(View v){
        Intent intent = new Intent(LayoutActivity.this,GridActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toConstraintActivity(View v){
        Intent intent = new Intent(LayoutActivity.this,ConstraintActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toButtonActivity(View v){
        Intent intent = new Intent(LayoutActivity.this,CheckButtonActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }


    public void toMenuActivity(View v) {
        Intent intent = new Intent(LayoutActivity.this, UiMenuActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toGetPostActivity(View v) {
        Intent intent = new Intent(LayoutActivity.this, GetPostActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toHandlerEmptyMessageActivity(View v) {
        Intent intent = new Intent(LayoutActivity.this, HandlerToEmptyMessageActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toDownHandlerActivity(View v) {
        Intent intent = new Intent(LayoutActivity.this, DownHandlerActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toCountHandlerActivity(View v) {
        Intent intent = new Intent(LayoutActivity.this, HandlerWeakActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toDiglettActivity(View v){
        Intent intent = new Intent(LayoutActivity.this, DiglettActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toAsyncTaskActivity(View v){
        Intent intent = new Intent(LayoutActivity.this, AsyncTaskActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toOptionMenuActivity(View v){
        Intent intent = new Intent(LayoutActivity.this, OptionMenuActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }

    public void toDialogActivity(View v){
        Intent intent = new Intent(LayoutActivity.this, DialogActivity.class);
        intent.putExtra("abc","aaaa");

        Bundle bundle = new Bundle();
        bundle.putString("aaa", "bbb");
        intent.putExtra("ccc", bundle);
        User u = new User();
        u.setUsername("liming");
        u.setPassword("123456");
        intent.putExtra("ddd", u);
        startActivity(intent);
        // 如果需要回调的话，可以通过下面的方式
//        startActivityForResult(intent,9999) ;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==9999 && resultCode ==123){
            setTitle("前一个页面回来了");
        }
    }





    public void toFragmentActivity(View v){
        Intent intent = new Intent(LayoutActivity.this, FragmentActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }


    public void toViewPagerActivity(View v){
        Intent intent = new Intent(LayoutActivity.this, ViewPagerActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }


    public void toViewPagerFragmentActivity(View v){
        Intent intent = new Intent(LayoutActivity.this, TabViewPagerActivity.class);
        startActivity(intent);
        LayoutActivity.this.finish();
    }
}
