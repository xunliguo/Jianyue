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
import com.example.asus_pc.jianyue.adapter.VideoFragmentAdapters;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.videosfragment.video_fragment.HappyFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.videosfragment.video_fragment.PopularFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.videosfragment.video_fragment.VarietyFragment;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.videosfragment.video_fragment.VideAnimFragment;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by ASUS-PC on 2017/8/18.
 */

public class VideoFragment extends Fragment {
    private TabLayout tablayout;
    private ViewPager viewpager;
   private List<Fragment> mlist=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.video_framgment, null);

        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        tablayout = (TabLayout) inflate.findViewById(R.id.videotaablayout);
        viewpager = (ViewPager) inflate.findViewById(R.id.videopviewpager);
        getData();
        VideoFragmentAdapters adapters=new VideoFragmentAdapters(getChildFragmentManager(),mlist);
         viewpager.setAdapter(adapters);
        tablayout.setupWithViewPager(viewpager);

    }


    public void getData() {
        PopularFragment  popularFragment=new PopularFragment();
        HappyFragment happyfragment=new HappyFragment();
        VideAnimFragment videanimfragment=new VideAnimFragment();
        VarietyFragment varitefragment=new VarietyFragment();
        mlist.add(popularFragment);
        mlist.add(happyfragment);
        mlist.add(videanimfragment);
        mlist.add(varitefragment);
    }
}
