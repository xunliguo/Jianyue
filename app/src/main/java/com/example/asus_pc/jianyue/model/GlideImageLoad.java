package com.example.asus_pc.jianyue.model;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus_pc.jianyue.R;
import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by ASUS-PC on 2017/8/22.
 */

public class GlideImageLoad extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).error(R.mipmap.ic_launcher).into(imageView);
    }
}
