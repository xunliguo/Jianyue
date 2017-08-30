package com.example.asus_pc.jianyue.controller.activity.homesActivity.videosfragment.video_fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.adapter.MyAdapter;
import com.example.asus_pc.jianyue.controller.activity.VideoActivity;
import com.example.asus_pc.jianyue.model.bean.MpBean;
import com.example.asus_pc.jianyue.model.network.OkHttpManager;

import java.util.ArrayList;
import java.util.List;

import static com.example.asus_pc.jianyue.R.id.lsitviw;


/**
 * Created by ASUS-PC on 2017/8/19.
 */

public class HappyFragment extends Fragment {

    private String url = "https://live-api-m.mtime.cn/live/detail?clientId=e36e1f24304a0d60QMSDU15A30004375&locationId=290&liveId=666";

    private List<MpBean.DataBean.VideoBean> list = new ArrayList<>();
    private ListView listveiw;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(),mPermissionList,123);
        }
        View view1 = inflater.inflate(R.layout.video_hapy, null);
        initView(view1);
        intiData();
        return view1;
    }

    private void intiData() {
        OkHttpManager.getInstance().getqingqiu(url, new OkHttpManager.Callacks() {
            @Override
            public void show(MpBean bean) {
                List<MpBean.DataBean.VideoBean> video = bean.getData().getVideo();
                list.addAll(video);
                MyAdapter adapter = new MyAdapter(getActivity(), list);
           listveiw.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listveiw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getActivity(), VideoActivity.class);
                        String url = list.get(position).getUrl();
                        String title = list.get(position).getTitle();
                        intent.putExtra("url",url);
                        intent.putExtra("urla",title);
                        startActivity(intent);
                    }
                });


            }
        });


    }


    private void initView(View view1) {


        listveiw = (ListView) view1.findViewById(R.id.playerg);

    }
}
