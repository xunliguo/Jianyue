package com.example.asus_pc.jianyue.controller.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.adapter.PictureFragmentAdapters;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.picturefragment.fragment.AnimeFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.picturefragment.fragment.BeautyFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.picturefragment.fragment.CasrFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.picturefragment.fragment.FoodFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.picturefragment.fragment.PhotoFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.picturefragment.fragment.StartFragment;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by ASUS-PC on 2017/8/18.
 */

public class PictureFragment extends Fragment {
    private TabLayout tablayout;
    private ViewPager viewpager;
    private List<Fragment> mlist=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.picture_fragment, null);

        initView(inflate);


        return inflate;
    }

    private void initView(View inflate) {
        tablayout = (TabLayout) inflate.findViewById(R.id.pictabblayout);
        viewpager = (ViewPager) inflate.findViewById(R.id.picviewpager);
        getData();

        PictureFragmentAdapters adapters=new PictureFragmentAdapters(getChildFragmentManager(),mlist);
        viewpager.setAdapter(adapters);
        tablayout.setupWithViewPager(viewpager);



    }

    public void getData() {
       BeautyFragment beautyFragment=new BeautyFragment();
        AnimeFragment animeFragment=new AnimeFragment();
        StartFragment startFragment=new StartFragment();
         CasrFragment casrFragment=new CasrFragment();
        PhotoFragment photoFragment=new PhotoFragment();
        FoodFragment foodFragment=new FoodFragment();
        mlist.add(beautyFragment);
        mlist.add(animeFragment);
        mlist.add(startFragment);
        mlist.add(casrFragment);
        mlist.add(photoFragment);
        mlist.add(foodFragment);

    }
}
