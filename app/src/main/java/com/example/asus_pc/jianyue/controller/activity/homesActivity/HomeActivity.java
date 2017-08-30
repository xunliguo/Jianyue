package com.example.asus_pc.jianyue.controller.activity.homesActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.controller.activity.MusicActivity;
import com.example.asus_pc.jianyue.controller.activity.TestActivity;
import com.example.asus_pc.jianyue.controller.activity.fragment.MusicFragment;
import com.example.asus_pc.jianyue.controller.activity.fragment.NewsFragment;
import com.example.asus_pc.jianyue.controller.activity.fragment.PictureFragment;
import com.example.asus_pc.jianyue.controller.activity.fragment.VideoFragment;
import com.example.asus_pc.jianyue.controller.option.AboutusActivity;
import com.example.asus_pc.jianyue.controller.option.OpinionActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;




public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

     NewsFragment newsFragment;
     PictureFragment pictureFragment;
     VideoFragment videoFragment;
     MusicFragment musicFragment;
    private TextView title;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ImageView image;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }

        setContentView(R.layout.activity_home);
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
        initView();//4
        initDlayOut();
        Scanner();
        isNetworkConnected(HomeActivity.this);//有无网络连接
        isWifiConnected(HomeActivity.this);//是否为wifi
        title.setText("新闻简读");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transactiosn = manager.beginTransaction();
        newsFragment = new NewsFragment();
        transactiosn.add(R.id.fragme, newsFragment);
        transactiosn.show(newsFragment);
        transactiosn.commit();
    }
    public void initDlayOut() {

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){@Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);

        }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //设置菜单图标恢复原来的颜色
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

    }


    private void initView() {
        title = (TextView) findViewById(R.id.title);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        image = (ImageView) findViewById(R.id.images);
    }
    //二维码
    private void Scanner() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(HomeActivity.this);
                intentIntegrator
                        .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                        .setPrompt("将二维码/条码放入框内，即可自动扫描")//写那句提示的话
                        .setOrientationLocked(false)//扫描方向固定
                        .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
                        .initiateScan(); // 初始化扫描
            }
        });
    }

    //有无网络连接
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {

                return mNetworkInfo.isAvailable();
            }
        }
        Toast.makeText(context, "网络无连接，请检查网络", Toast.LENGTH_SHORT).show();
        return false;
    }
    //有无wifi
    public boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {

                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    //返回键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗");
            // 添加选择按钮并注册监听
            isExit.setButton("确定",listener);

            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();

        }
        return false;

    }
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which)
        {
            switch (which)
            {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };

    //获取扫描的结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {

            } else {
                // ScanResult 为获取到的字符串
                String ScanResult = intentResult.getContents();
                Toast.makeText(this, "扫描成功-内容为"+ScanResult, Toast.LENGTH_SHORT).show();;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
/**
 * 下面切换四大模块
 *
 */
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction  transaction = manager.beginTransaction();
        hideAll(transaction);
        switch (item.getItemId()){
          case R.id.news:
              title.setText("新闻阅读");
         if (newsFragment==null){

             newsFragment = new NewsFragment();

             transaction.add(R.id.fragme,newsFragment);
         }else{
             transaction.show(newsFragment);
         }
              break;
          case R.id.pictrue:
              title.setText("图片浏览");
              if (pictureFragment==null){

                  pictureFragment = new PictureFragment();

                  transaction.add(R.id.fragme,pictureFragment);
              }else{
                  transaction.show(pictureFragment);
              }
              break;
          case  R.id.videol:
              title.setText("视频爽看");
              if (videoFragment==null){

                  videoFragment = new VideoFragment();

                  transaction.add(R.id.fragme,videoFragment);
              }else{
                  transaction.show(videoFragment);
              }
              break;
          case  R.id.music:
              title.setText("音乐轻听");
           startActivity(  new Intent(HomeActivity.this, MusicActivity.class));
//              if (musicFragment==null){

//                  musicFragment = new MusicFragment();
//
//                  transaction.add(R.id.fragme,musicFragment);
//              }else{
//                  transaction.show(musicFragment);
//              }
        break;


      }
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void hideAll(FragmentTransaction transaction) {
        if (newsFragment!=null){
            transaction.hide(newsFragment);

        }
//        if (musicFragment!=null){
//            transaction.hide(musicFragment);
//        }
        if (pictureFragment!=null){
            transaction.hide(pictureFragment);
        }
        if (videoFragment!=null){
            transaction.hide(videoFragment);
        }
    }
    //右边菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            //关于我们 模块
            case R.id.action_about:
                Intent intent = new Intent(HomeActivity.this, AboutusActivity.class);
                startActivity(intent);
                break;
            //意见反馈 模块
            case R.id.action_yijian:
                Intent intent1 = new Intent(HomeActivity.this, OpinionActivity.class);
                startActivity(intent1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
