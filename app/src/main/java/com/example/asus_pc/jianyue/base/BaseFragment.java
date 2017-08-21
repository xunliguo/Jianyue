package com.example.asus_pc.jianyue.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 爱生活，爱代码
 * 创建于：2017/8/17 11:52
 * 作 者：T
 * 微信：704003376
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(loadFragmentLayoutId(), null);
        initView(view);
        initData();
        return view;
    }
    protected abstract void initData();
    protected abstract void initView(View view);
    protected abstract int loadFragmentLayoutId();

    /**
     * 当前碎片隐藏的时候调用
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden)
            saveData();
        else
            loadData();
    }
    protected  void loadData(){}
    private void saveData() {}
}
