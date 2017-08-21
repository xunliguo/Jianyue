package com.example.asus_pc.jianyue.controller.option;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.asus_pc.jianyue.R;

import java.util.ArrayList;

public class OpinionActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toobar;

    private FrameLayout fram;
    private RelativeLayout relative;
    private RelativeLayout relative2;

    private ContactFragment confragmwnt;
    private FeedbackFragment feedback;
    private ViewPager viewpager;
    private ArrayList<Fragment> list=new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        initView();
    }

    private void initView() {

        toobar = (Toolbar) findViewById(R.id.toobar);

        toobar.setNavigationIcon(R.drawable.ic_back);

        toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        relative = (RelativeLayout) findViewById(R.id.relative);

        relative2 = (RelativeLayout) findViewById(R.id.relative2);
        relative.setOnClickListener(this);
        relative2.setOnClickListener(this);


        confragmwnt = new ContactFragment();
        feedback = new FeedbackFragment();
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setOnClickListener(this);
        list.add(confragmwnt);
        list.add(feedback);
        Main_Adapter adapter=new Main_Adapter(getSupportFragmentManager(),list);

        viewpager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.relative:
      viewpager.setCurrentItem(0);
                break;
            case R.id.relative2:
                 viewpager.setCurrentItem(1);
                break;
        }

    }


}
