package com.idig8.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.idig8.myapplication.R;
import com.idig8.myapplication.adapter.MainHeaderAdsAdapter;
import com.idig8.myapplication.adapter.MainMenuAdapter;
import com.idig8.myapplication.until.DataUtil;

/**
 * 主界面
 */
public class MainFragment extends Fragment {

    protected ViewPager mViewPagerHeaderAds; // 广告头部
    protected  int[] icons = {R.mipmap.header_pic_ad1,R.mipmap.header_pic_ad2};
    //菜单图标
    protected  int [] menuIons={R.mipmap.menu_airport,R.mipmap.menu_car
            ,R.mipmap.menu_course,R.mipmap.menu_hatol,
            R.mipmap.menu_nearby,R.mipmap.me_menu_go,
            R.mipmap.menu_ticket,R.mipmap.menu_train};
    protected  String [] menus;
    protected RecyclerView mRecyclerViewMenu; //主菜单

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPagerHeaderAds = getView().findViewById(R.id.vpager_main_header_ads);
        mRecyclerViewMenu = getView().findViewById(R.id.recycleview_main_menu);
        MainHeaderAdsAdapter adpter = new MainHeaderAdsAdapter(getActivity(), DataUtil.getHeaderAddInfo(getActivity(),icons));
        mViewPagerHeaderAds.setAdapter(adpter);
        menus=this.getActivity().getResources().getStringArray(R.array.main_menu);
        //设置菜单的
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(getActivity(),DataUtil.getMainMenus(menuIons,menus));
        //布局样式
        mRecyclerViewMenu.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRecyclerViewMenu.setAdapter(mainMenuAdapter);
    }
}
