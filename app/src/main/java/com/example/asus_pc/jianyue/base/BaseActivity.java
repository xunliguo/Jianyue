package com.example.asus_pc.jianyue.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.asus_pc.jianyue.global.MyApp;


/**
 * 爱生活，爱代码
 * 创建于：2017/8/17 11:51
 * 作 者：T
 * 微信：704003376
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       MyApp.mContext = this;
        setContentView(loadActivityLayoutId());
        initView();
        loadData();
    }
    protected abstract void loadData();
    protected abstract void initView();
    protected abstract int loadActivityLayoutId();
}
