package com.idig8.myapplication.until;

import android.content.Context;
import android.widget.ImageView;

import com.idig8.myapplication.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加数据
 */
public class DataUtil {

    /**
     *
     * @param context
     * @param icons
     * @return
     */
    public  static List<ImageView> getHeaderAddInfo(Context context,int[] icons){
        List<ImageView> datas = new ArrayList<>();

        for (int i = 0; i <icons.length; i++) {
            ImageView icon = new ImageView(context);
            icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            icon.setImageResource(icons[i ]);
            datas.add(icon);
        }

        return datas;
    }

    /**
     * 主菜单
     * @param icons
     * @param names
     * @return
     */
    public static List<Menu> getMainMenus(int[] icons,String[] names){
        List<Menu> menuList = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            Menu menu = new Menu(icons[i],names[i]);
            menuList.add(menu);

        }
        return menuList;
    }
}
