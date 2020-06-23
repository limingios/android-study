package com.idig8.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment
{

    private static  final  String POSITION="position";
    private String postion;

    public static TestFragment newInstance(int position){
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION,position);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            postion = String.valueOf(getArguments().getInt(POSITION));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,null);
        TextView textView = view.findViewById(R.id.text_view_fragmet);
        textView.setText(postion);
        return view;
    }
}
