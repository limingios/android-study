package com.idig8.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idig8.myapplication.R;
import com.idig8.myapplication.model.LessonResult;

import java.util.ArrayList;
import java.util.List;

public class RequestDataAdapter extends BaseAdapter {

    List<LessonResult.Lesson> mLessInfos = new ArrayList<>();
    private  Context mConext;
    public RequestDataAdapter(Context context,List<LessonResult.Lesson> infos) {
        mConext = context;
        mLessInfos = infos;
    }

    @Override
    public int getCount() {
        return mLessInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mLessInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) mConext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_app_list_view,null);
            viewHolder.mAppIconImageView = convertView.findViewById(R.id.app_icon_image_view);
            viewHolder.mAppTextView = convertView.findViewById(R.id.app_name_text_view);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.mAppTextView.setText(mLessInfos.get(position).getmName());
        viewHolder.mAppIconImageView.setVisibility(View.GONE);
        return convertView;
    }

    public class ViewHolder {
        public ImageView mAppIconImageView;
        public TextView mAppTextView;
    }
}
