package com.example.asus_pc.jianyue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;

public class ImageBrowseActivity extends AppCompatActivity {

    private PhotoView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        initView();
    }

    private void initView() {
        image = (PhotoView) findViewById(R.id.image);
        Intent intent = getIntent();
        String imgs = intent.getStringExtra("imgs");
        Picasso.with(this).load(imgs).into(image);

    }
}
