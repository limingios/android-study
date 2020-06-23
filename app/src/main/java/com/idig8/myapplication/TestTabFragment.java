package com.idig8.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestTabFragment extends Fragment
{

    private static  final  String TITLE="title";
    private String mTitle;

    public static TestTabFragment newInstance(String title){
        TestTabFragment testFragment = new TestTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE,title);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            mTitle = String.valueOf(getArguments().getString(TITLE));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,null);
        TextView textView = view.findViewById(R.id.text_view_fragmet);
        textView.setText(mTitle);
        return view;
    }
}
