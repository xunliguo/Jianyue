package com.example.asus_pc.jianyue.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2017/8/18.
 */

public class VideoFragmentAdapters extends FragmentPagerAdapter {
    private List<Fragment> mlist;
    private ArrayList<String> list=new ArrayList<>();



    public VideoFragmentAdapters(FragmentManager fm, List<Fragment> mlist) {
        super(fm);
        list.add("热门");
        list.add("搞笑");
        list.add("动漫");
        list.add("综艺");


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
