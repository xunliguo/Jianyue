package com.example.asus_pc.jianyue.controller.activity.homesActivity.picturefragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus_pc.jianyue.R;

/**
 * Created by ASUS-PC on 2017/8/19.
 */

public class AnimeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fg_anim, null);

        return inflate;
    }
}
