package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class TabHostActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);


        //初始化总布局
        tabHost = findViewById(R.id.tab_host);
        tabHost.setup();


        int[] titleIDs = new int[]{
               R.string.home,
               R.string.message,
               R.string.me

        };
        int[] drawableIDs = new int[]{
                R.drawable.main_tab_icon_home,
                R.drawable.main_tab_icon_message,
                R.drawable.main_tab_icon_me
        };

        // data --> view

        for (int i = 0; i < titleIDs.length ; i++) {
            View view = getLayoutInflater().inflate(R.layout.main_tab_layout,null,false);

            ImageView iconView = view.findViewById(R.id.main_tab_icon);
            TextView title = view.findViewById(R.id.main_tab_tab_txt);
            View tab = view.findViewById(R.id.tab_bg);

            iconView.setImageResource(drawableIDs[i]);
            title.setText(titleIDs[i]);

            tab.setBackgroundColor(getResources().getColor(R.color.white));

            tabHost.addTab(
                    tabHost.newTabSpec(getString(titleIDs[i])).setContent(this).setIndicator(view)
            );
        }



        final Fragment[] fragments = new Fragment[]{
                TestTabFragment.newInstance("home"),
                TestTabFragment.newInstance("message"),
                TestTabFragment.newInstance("me")
        };



        final ViewPager viewPager = findViewById(R.id.viewPager_tab);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return  fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(tabHost!=null){
                    tabHost.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabHost!=null ){
                    int positon = tabHost.getCurrentTab();
                    viewPager.setCurrentItem(positon);
                }
            }
        });

    }

    @Override
    public View createTabContent(String tag) {
        View view = new View(this);
        view.setMinimumHeight(0);
        view.setMinimumWidth(0);
        return view;
    }
}
