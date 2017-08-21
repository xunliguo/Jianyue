package com.example.asus_pc.jianyue.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2017/8/18.
 */

public class PictureFragmentAdapters extends FragmentPagerAdapter {
    private List<Fragment> mlist;
    private ArrayList<String> list=new ArrayList<>();



    public PictureFragmentAdapters(FragmentManager fm, List<Fragment> mlist) {
        super(fm);
        list.add("美女");
        list.add("动漫");
        list.add("明星");
        list.add("汽车");
        list.add("摄影");
        list.add("美食");

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
