package com.example.asus_pc.jianyue.controller.activity.homesActivity.videosfragment.video_fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.adapter.MyAdapter;
import com.example.asus_pc.jianyue.controller.activity.VideoActivity;
import com.example.asus_pc.jianyue.model.bean.MpBean;
import com.example.asus_pc.jianyue.model.network.OkHttpManager;

import org.androidannotations.annotations.Bean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ASUS-PC on 2017/8/19.
 */
//https://live-api-m.mtime.cn/live/detail?clientId=e36e1f24304a0d60QMSDU15A30004375&locationId=290&liveId=665
public class PopularFragment extends Fragment {
    private ListView lsitviw;
    private Handler handler = new Handler();
    private List<MpBean.DataBean.VideoBean> lsit = new ArrayList<>();
    private String url = "https://live-api-m.mtime.cn/live/detail?clientId=e36e1f24304a0d60QMSDU15A30004375&locationId=290&liveId=665";
    private MyAdapter adapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(),mPermissionList,123);
        }

       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
           progressBar.setVisibility(View.GONE);
           }
       },2000);

        View view = inflater.inflate(R.layout.video_pop, null);

        initView(view);
        getData();

        return view;
    }
    private void getData() {
        OkHttpManager.getInstance().getqingqiu(url, new OkHttpManager.Callacks() {
            @Override
            public void show(MpBean bean) {
                List<MpBean.DataBean.VideoBean> videos = bean.getData().getVideo();
                Log.e("TAG", "+++++++>" + videos);
                lsit.addAll(videos);
                adapter = new MyAdapter(getActivity(), lsit);
                lsitviw.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                lsitviw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getActivity(), VideoActivity.class);
                        String url = lsit.get(position).getUrl();
                        String title = lsit.get(position).getTitle();
                        intent.putExtra("url",url);
                        intent.putExtra("urla",title);

                        startActivity(intent);
                    }
                });


            }
        });

    }

    private void initView(View view) {
        lsitviw = (ListView) view.findViewById(R.id.lsitviw);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);

    }
}
