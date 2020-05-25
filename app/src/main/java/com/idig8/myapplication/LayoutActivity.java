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
}
