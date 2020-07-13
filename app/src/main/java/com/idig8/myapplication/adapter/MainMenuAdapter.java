package com.idig8.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idig8.myapplication.R;
import com.idig8.myapplication.entity.Menu;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuViewholder> {

    protected Context context;
    protected List<Menu> menuList;

    public MainMenuAdapter(Context context, List<Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MainMenuViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainMenuViewholder(LayoutInflater.from(context).inflate(R.layout.item_main_menu,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuViewholder holder, int position) {
        Menu menu = menuList.get(position);
        holder.mImgMenuIcon.setImageResource(menu.icon);
        holder.mTxtMenuName.setText(menu.menuName);

    }

    @Override
    public int getItemCount() {
        return null!=menuList?menuList.size():0;
    }
}

    class MainMenuViewholder extends RecyclerView.ViewHolder {

        public ImageView mImgMenuIcon;
        public TextView mTxtMenuName;

        public MainMenuViewholder(@NonNull View itemView) {

            super(itemView);
            mImgMenuIcon = itemView.findViewById(R.id.img_menu_icon);
            mTxtMenuName = itemView.findViewById(R.id.menu_text);
        }
    }


