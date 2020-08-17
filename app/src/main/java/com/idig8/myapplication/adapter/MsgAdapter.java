package com.idig8.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idig8.myapplication.R;
import com.idig8.myapplication.model.Msg;

import java.util.List;

public class MsgAdapter
        extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private List<Msg> mDatas;

    public MsgAdapter(Context context, List<Msg> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder==null){
            convertView = mInflater.inflate(R.layout.activity_card_view_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mIvImg = convertView.findViewById(R.id.id_tv_img);
            viewHolder.mIvTitle = convertView.findViewById(R.id.id_tv_title);
            viewHolder.mIVcontent = convertView.findViewById(R.id.id_tv_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Msg msg = mDatas.get(position);
        viewHolder.mIvImg.setImageResource(msg.getImgResId());
        viewHolder.mIvTitle.setText(msg.getTitle());
        viewHolder.mIVcontent.setText(msg.getContent());
        return convertView;
    }

    public static  class ViewHolder{
        ImageView mIvImg;
        TextView mIvTitle;
        TextView mIVcontent;
    }
}
