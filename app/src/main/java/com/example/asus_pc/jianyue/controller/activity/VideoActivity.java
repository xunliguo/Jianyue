package com.example.asus_pc.jianyue.controller.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.asus_pc.jianyue.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


public class VideoActivity extends AppCompatActivity {

    private JCVideoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
        initView();


    }


    private void initView() {
        player = (JCVideoPlayer) findViewById(R.id.player);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String urla = intent.getStringExtra("urla");
        player.setUp(url,urla);

    }
}
