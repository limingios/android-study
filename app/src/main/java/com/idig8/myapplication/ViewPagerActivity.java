package com.idig8.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    //初始化位置
    public static final int INIT_POSITION = 0;
    private ViewPager viewPager;
    private ViewPager viewImagePager;

    private  int[] mLayoutIDs = {
            R.layout.view_first,
            R.layout.view_second,
            R.layout.view_thrid

    };
    private List<View> views;
    private List<View> viewsImage;

    private ViewGroup mDotViewGroup;
    private List<ImageView> mDotViews = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        //加载view
        viewPager1();
        //加载图片的view
        viewPager2();
    }

    private void viewPager1() {
        viewPager = findViewById(R.id.view_pager1);

        //初始化数据
        views = new ArrayList<>();
        for (int index = 0 ;index<mLayoutIDs.length;index++){
                final View view = getLayoutInflater().inflate(mLayoutIDs[index],null);
                views.add(view);
        }

        // 设置Adapter
        viewPager.setAdapter(mPagerAdpth);
    }

    private void viewPager2() {
        viewImagePager = findViewById(R.id.view_pager2);
        mDotViewGroup = findViewById(R.id.dot_layout);
        //初始化数据
        viewsImage = new ArrayList<>();
        for (int index = 0 ;index<mLayoutIDs.length;index++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.bluetooth);
            viewsImage.add(imageView);

            ImageView dot = new ImageView(this);
            dot.setImageResource(R.mipmap.ic_launcher);
            dot.setMaxWidth(100);
            dot.setMaxHeight(100);

            LinearLayout.LayoutParams  layoutParams = new LinearLayout.LayoutParams(20,20);
            layoutParams.leftMargin = 20;
            dot.setLayoutParams(layoutParams);
            dot.setEnabled(false);
            mDotViewGroup.addView(dot);
            mDotViews.add(dot);


        }

        // 设置Adapter
        viewImagePager.setAdapter(mPagerAdpth2);
        viewImagePager.setOffscreenPageLimit(4);
        //第一次进入的时候需要也设置对应的变化图标的
        viewImagePager.setCurrentItem(INIT_POSITION);

        setDotViews(0);

        viewImagePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setDotViews(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setDotViews(int position) {
        for (int index = 0 ;index<mDotViews.size();index++){
            mDotViews.get(index).setImageResource(position == index?R.mipmap.button_blue_play:R.mipmap.ic_launcher);
        }
    }

    PagerAdapter  mPagerAdpth = new PagerAdapter(){

        @Override
        public int getCount() {
            return mLayoutIDs.length;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View child = views.get(position);
            container.addView(child);
            return child;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
          container.removeView(views.get(position));

        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    };

    PagerAdapter  mPagerAdpth2 = new PagerAdapter(){

        @Override
        public int getCount() {
            return mLayoutIDs.length;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View child = viewsImage.get(position);
            container.addView(child);
            return child;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(viewsImage.get(position));

        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    };
}
