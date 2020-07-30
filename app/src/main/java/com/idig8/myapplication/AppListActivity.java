package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        ListView applistview =findViewById(R.id.applistview);

        List<String> appnames = new ArrayList<>();
        appnames.add("qq");
        appnames.add("微信");
        appnames.add("idig8.com");
//        applistview.setAdapter(new AppListAdapter(appnames));
        final List<ResolveInfo> appInfos = getAppInfos();
        applistview.setAdapter(new AppInfoAdapter(appInfos));
        //可以这样增加点击
//        applistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                String packageName = appInfos.get(position).activityInfo.packageName;
////                String className = appInfos.get(position).activityInfo.name;
////                ComponentName componentName = new ComponentName(packageName,className);
////                final Intent intent = new Intent();
////                intent.setComponent(componentName);
////                startActivity(intent);
////            }
////        });

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headerView = layoutInflater.inflate(R.layout.header_list,null);

        applistview.addHeaderView(headerView);



    }

    private List<ResolveInfo> getAppInfos(){
        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return getPackageManager().queryIntentActivities(intent,0);
    }
    public class AppListAdapter extends BaseAdapter{

        List<String> mAppNames;
        public AppListAdapter(List<String> appNames) {
            mAppNames =appNames;
        }

        @Override
        public int getCount() {
            return mAppNames.size();
        }

        @Override
        public Object getItem(int position) {
            return mAppNames.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_app_list_view,null);

            if(convertView!=null) {
                ImageView appIconImageView = convertView.findViewById(R.id.app_icon_image_view);
                TextView appTextView = convertView.findViewById(R.id.app_name_text_view);
                appTextView.setText(mAppNames.get(position));
            }
            return convertView;
        }
    }

    public class AppInfoAdapter extends BaseAdapter{

        List<ResolveInfo> mAppInfos;
        public AppInfoAdapter(List<ResolveInfo> appInfos) {
            mAppInfos =appInfos;
        }

        @Override
        public int getCount() {
            return mAppInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return mAppInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_app_list_view,null);

            if(convertView!=null) {
                ImageView appIconImageView = convertView.findViewById(R.id.app_icon_image_view);
                TextView appTextView = convertView.findViewById(R.id.app_name_text_view);
                appTextView.setText(mAppInfos.get(position).activityInfo.loadLabel(getPackageManager()));
                appIconImageView.setImageDrawable(mAppInfos.get(position).activityInfo.loadIcon(getPackageManager()));
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String packageName = mAppInfos.get(position).activityInfo.packageName;
                        String className = mAppInfos.get(position).activityInfo.name;
                        ComponentName componentName = new ComponentName(packageName,className);
                        final Intent intent = new Intent();
                        intent.setComponent(componentName);
                        startActivity(intent);
                    }
                });
            }
            return convertView;
        }
    }
}
