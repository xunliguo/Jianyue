package com.example.asus_pc.jianyue.controller.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.HomeActivity;

import static android.R.attr.y;

public class StartActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);

        ObjectAnimator scaleX= ObjectAnimator.ofFloat(image,"scaleX",1f,1.2f,1.4f);
        ObjectAnimator scaleY= ObjectAnimator.ofFloat(image,"scaleY",1f,1.2f,1.4f);
        AnimatorSet set=new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.setDuration(4500);
       set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
               startActivity(new Intent(StartActivity.this,HomeActivity.class));

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }
}
