package com.example.asus_pc.jianyue.controller.activity.homesActivity.videosfragment.video_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.picturefragment.fragment.FoodFragment;

/**
 * Created by ASUS-PC on 2017/8/19.
 */

public class PopularFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.video_pop, null);

        return view1;
    }
}
