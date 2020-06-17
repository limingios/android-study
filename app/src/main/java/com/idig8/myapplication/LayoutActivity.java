package com.idig8.myapplication;

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
        startActivity(intent);
        LayoutActivity.this.finish();
    }
}
