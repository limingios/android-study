package com.idig8.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {


    public static final String BUNDLE_TITLE = "bundle_title";
    private String title;

    public static ListFragment getInstance(String title){
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE,title);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    // 最主要是这个方法，它会返回一个视图就是展示的样子
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建一个视图
        View v  =inflater.inflate(R.layout.fragmet_list,container,false);
        TextView textView = v.findViewById(R.id.textView2);
//        textView.setText("测试内部赋值");
        textView.setText(title);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnTitleClickLister!=null){
                    mOnTitleClickLister.onClick(title);
                }
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            title = getArguments().getString(BUNDLE_TITLE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    // 定义变量
    private  OnTitleClickLister mOnTitleClickLister;

    //  设置接口的方法
    public void setmOnTitleClickLister(OnTitleClickLister mOnTitleClickLister) {
        this.mOnTitleClickLister = mOnTitleClickLister;
    }

    //定义接口
    public interface  OnTitleClickLister{
        void   onClick(String clickTitle);
    }
}
