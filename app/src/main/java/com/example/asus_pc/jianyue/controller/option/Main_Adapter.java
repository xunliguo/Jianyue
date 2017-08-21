package com.example.asus_pc.jianyue.controller.option;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ASUS-PC on 2017/5/19.
 */

public class Main_Adapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;

    public Main_Adapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
