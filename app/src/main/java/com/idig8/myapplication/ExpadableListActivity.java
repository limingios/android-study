package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.idig8.myapplication.adapter.ChapterAdapter;
import com.idig8.myapplication.biz.ChapterBiz;
import com.idig8.myapplication.model.Chapter;
import com.idig8.myapplication.model.ChapterLab;

import java.util.ArrayList;
import java.util.List;

public class ExpadableListActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private BaseExpandableListAdapter mExpandableListAdapter;
    private List<Chapter> mDatas = new ArrayList<>();
    private Button mBtnRefresh;
    private ChapterBiz mChapterBiz = new ChapterBiz();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expadable_list);
        mExpandableListView = findViewById(R.id.expandable_list);
        mBtnRefresh = findViewById(R.id.id_btn_refresh);
//        initView();
        loadDatas(true);
        initEvents();

        mExpandableListAdapter = new ChapterAdapter(this, mDatas);
        mExpandableListView.setAdapter(mExpandableListAdapter);


    }

//    private void initView(){
//
//        mDatas.clear();
//        mDatas.addAll(ChapterLab.generateDatas());
//
//    }

    private void loadDatas(boolean useCache) {
        mChapterBiz.loadDatas(this, new ChapterBiz.CallBack() {
            @Override
            public void loadSuccess(List<Chapter> chapterList) {
                Log.e("zhy", "loadSuccess");

                mDatas.clear();
                mDatas.addAll(chapterList);
                mExpandableListAdapter.notifyDataSetChanged();
            }

            @Override
            public void loadFailed(Exception ex) {
                ex.printStackTrace();
                Log.e("zhy", "loadFailed ex= " + ex.getMessage());
            }
        }, useCache);
    }
    private void initEvents() {

        mBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(false);
            }
        });

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d("zhy", "onGroupClick groupPosition = " + groupPosition);
                return false;
            }
        });


        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Log.d("zhy", "onChildClick groupPosition = "
                        + groupPosition + " , childPosition = " + childPosition + " , id = " + id);

                return false;
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            // 收回
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d("zhy", "onGroupCollapse groupPosition = " + groupPosition);

            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            // 展开
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d("zhy", "onGroupExpand groupPosition = " + groupPosition);

            }
        });

        mExpandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("zhy", "onItemClick position = " + position);

            }
        });


    }
}
