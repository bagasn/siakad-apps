package com.bagasnasution.lecturesapp.app.model;

import android.support.annotation.DrawableRes;

import com.bagasnasution.lecturesapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubMenuModel {
    private int id;
    private String title;
    @DrawableRes
    private int icon;

    public SubMenuModel(int id, String title, int icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public static List<SubMenuModel> getInstance() {
        List<SubMenuModel> list = new ArrayList<>();

        list.add(new SubMenuModel(MENU_JADWAL, "Jadwal", R.drawable.ic_schedule_black_24dp));
        list.add(new SubMenuModel(MENU_TRANSKRIP, "Transkrip", R.drawable.ic_menu_assessment_black_24dp));
        list.add(new SubMenuModel(MENU_MATAKULIAH, "Mata Kuliah", R.drawable.ic_menu_list_black_24dp));
        list.add(new SubMenuModel(MENU_NEWS, "News", R.drawable.ic_event_black_24dp));

        return list;
    }

    public static final int MENU_JADWAL = 1;
    public static final int MENU_TRANSKRIP = 2;
    public static final int MENU_MATAKULIAH = 3;
    public static final int MENU_NEWS = 4;

}
