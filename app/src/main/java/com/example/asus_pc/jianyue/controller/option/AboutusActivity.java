package com.example.asus_pc.jianyue.controller.option;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.asus_pc.jianyue.controller.activity.homesActivity.HomeActivity;
import com.example.asus_pc.jianyue.R;

public class AboutusActivity extends AppCompatActivity {

    private Toolbar toobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        initView();
    }

    private void initView() {
        toobar = (Toolbar) findViewById(R.id.toobar);
        toobar.setNavigationIcon(R.drawable.ic_back);
        toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutusActivity.this, HomeActivity.class));
                finish();
                setSupportActionBar(toobar);
            }
        });
    }
}
