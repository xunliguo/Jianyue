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

import static android.R.id.list;


/**
 * Created by ASUS-PC on 2017/8/19.
 */

public class VarietyFragment extends Fragment {

    private String url1 = "http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv";
    private String url2 = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
    private String url3 = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
    private String url4 = "http://42.96.249.166/live/388.m3u8";
    private String url5 = "http://live.3gv.ifeng.com/zixun.m3u8";
    private ListView listview;
    private String url = "https://live-api-m.mtime.cn/live/detail?clientId=e36e1f24304a0d60QMSDU15A30004375&locationId=290&liveId=667";
    private List<MpBean.DataBean.VideoBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(),mPermissionList,123);
        }
        View view1 = inflater.inflate(R.layout.video_variet, null);

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
              listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        listview = (ListView) view1.findViewById(R.id.listview);
    }
}



