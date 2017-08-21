package com.example.asus_pc.jianyue.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2017/8/18.
 */

public class FragmentAdapters extends FragmentPagerAdapter {
    private List<Fragment> mlist;
    private ArrayList<String> list=new ArrayList<>();



    public FragmentAdapters(FragmentManager fm,List<Fragment> mlist) {
        super(fm);
        list.add("头条");
        list.add("汽车");
        list.add("房产");
        list.add("科技");
        list.add("星座");
        list.add("旅游");
        list.add("时尚");
        list.add("娱乐");
        this.mlist = mlist;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);

    }

    @Override
    public int getCount() {

        return mlist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  list.get(position);
    }
}
