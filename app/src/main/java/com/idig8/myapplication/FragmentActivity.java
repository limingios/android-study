package com.idig8.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FragmentActivity.this,StaticLoadFragmentActivity.class));

            }
        });


        ListFragment listFragment = new ListFragment();

        // 同一个listFragment 只能被用一次。

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.listContainer,listFragment)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.detailContainer,new ListFragment())
                .commit();


        getSupportFragmentManager()
                .beginTransaction()
                .remove(listFragment)
                .commit();
    }


}
