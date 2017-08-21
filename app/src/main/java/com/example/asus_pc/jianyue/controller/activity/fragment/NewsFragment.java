package com.example.asus_pc.jianyue.controller.activity.fragment;

import android.support.design.widget.TabLayout;;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.adapter.FragmentAdapters;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.newsfragment.fragments.CarFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.newsfragment.fragments.Constellation;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.newsfragment.fragments.EntertainmentFrament;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.newsfragment.fragments.FashionFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.newsfragment.fragments.HeadlinesFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.newsfragment.fragments.HoursFrament;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.newsfragment.fragments.TechnologyFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.newsfragment.fragments.TourismFragment;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2017/8/18.
 */

public class NewsFragment extends Fragment {
    private TabLayout tablayout;
    private ViewPager viewpager;
  private List<Fragment> mlist=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.news_fragment, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        tablayout = (TabLayout) inflate.findViewById(R.id.tacblayout);
        viewpager = (ViewPager) inflate.findViewById(R.id.viewpager1);
        getdata();
        FragmentAdapters adapters=new FragmentAdapters(getChildFragmentManager(),mlist);
        viewpager.setAdapter(adapters);
        tablayout.setTabMode(View.SCROLLBAR_POSITION_DEFAULT);
        //第一个参数是字体颜色 第二歌参数是背景颜色
        tablayout.setupWithViewPager(viewpager);
    }
    public void getdata() {
        HeadlinesFragment heaflinesfragment=new HeadlinesFragment();//头条
        CarFragment carFragment=new CarFragment();//汽车
        HoursFrament housframent=new HoursFrament();//房产
        TechnologyFragment teacholoFragment=new TechnologyFragment();//科技
        Constellation constellation=new Constellation();//星座
        TourismFragment tourismfragment=new TourismFragment();//旅游
        FashionFragment fashionFragment=new FashionFragment();//时尚
        EntertainmentFrament entertainfragment=new EntertainmentFrament();//娱乐



        mlist.add(heaflinesfragment);
        mlist.add(carFragment);
        mlist.add(housframent);
        mlist.add(teacholoFragment);//科技
        mlist.add(constellation);
        mlist.add(tourismfragment);
        mlist.add(fashionFragment);//时尚
        mlist.add(entertainfragment);//娱乐






    }
}
