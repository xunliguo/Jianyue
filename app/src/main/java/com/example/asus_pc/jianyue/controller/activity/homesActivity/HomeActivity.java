package com.example.asus_pc.jianyue.controller.activity.homesActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_pc.jianyue.R;
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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        title = (TextView) findViewById(R.id.title);
        //二维码扫描模块
        ImageView image = (ImageView) findViewById(R.id.images);
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

        title.setText("新闻简读");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transactiosn = manager.beginTransaction();

        newsFragment = new NewsFragment();
        transactiosn.add(R.id.fragme,newsFragment);
        transactiosn.show(newsFragment);
        transactiosn.commit();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                title.setText("简阅");
                Toast.makeText(HomeActivity.this, "打开了", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                Toast.makeText(HomeActivity.this, "合住了", Toast.LENGTH_SHORT).show();

            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //设置菜单图标恢复原来的颜色
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }



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
    public void customScan(){
        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .setCaptureActivity(TestActivity.class) // 设置自定义的activity是CustomActivity
                .initiateScan(); // 初始化扫描
    }
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
              if (musicFragment==null){

                  musicFragment = new MusicFragment();

                  transaction.add(R.id.fragme,musicFragment);
              }else{
                  transaction.show(musicFragment);
              }
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
        if (musicFragment!=null){
            transaction.hide(musicFragment);
        }
        if (pictureFragment!=null){
            transaction.hide(pictureFragment);
        }
        if (videoFragment!=null){
            transaction.hide(videoFragment);
        }
    }


}
