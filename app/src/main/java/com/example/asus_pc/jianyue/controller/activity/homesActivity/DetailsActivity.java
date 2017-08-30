package com.example.asus_pc.jianyue.controller.activity.homesActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.asus_pc.jianyue.R;

import static android.view.KeyEvent.KEYCODE_BACK;
import static com.example.asus_pc.jianyue.R.id.weview;

public class DetailsActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
        initView();
    }

    private void initView() {
        webview = (WebView) findViewById(weview);
        WebSettings webSettings = webview.getSettings();
//支持缩放，默认为true。
        webSettings .setSupportZoom(true);
//调整图片至适合webview的大小
        webSettings .setUseWideViewPort(true);
// 缩放至屏幕的大小
        webSettings .setLoadWithOverviewMode(true);
//设置默认编码
        webSettings .setDefaultTextEncodingName("utf-8");
////设置自动加载图片
        webSettings .setLoadsImagesAutomatically(true);


        Intent intent = getIntent();
         String urls = intent.getStringExtra("name");
        webview.loadUrl(urls);
       webview.setWebViewClient(new WebViewClient(){
           @Override
           public boolean shouldOverrideUrlLoading(WebView view, String url) {
             view.loadUrl(url);
               return true;
           }
       });

    }


    @Override
    protected void onDestroy() {
        if (webview!= null) {
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.clearHistory();
            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview= null;
        }
        super.onDestroy();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webview.canGoBack()) {
           webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
