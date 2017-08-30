package com.example.asus_pc.jianyue.global;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.asus_pc.jianyue.base.BaseActivity;
import com.example.asus_pc.jianyue.service.PlayService;


/**
 * 爱生活，爱代码
 * 创建于：2017/8/17 11:42
 * 作 者：T
 * 微信：704003376
 */

/**
 * MP4,3GP    , AVM,MKIV, AVI,FLV,WMV,
 * 第三方初始化  Vitamio(第三方,非商业应用免费,基于FFmpeg, 支持市面上主流的视频格式)
 * 存放整个程序共享的数据
 */
public class MyApp extends Application {
    public static Context sContext;
    public static int sScreenWidth;
    public static int sScreenHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        startService(new Intent(this, PlayService.class));
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        sScreenWidth = dm.widthPixels;
        sScreenHeight = dm.heightPixels;
    }
}
