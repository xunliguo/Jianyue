package com.example.asus_pc.jianyue.controller.activity.homesActivity.newsfragment.fragments;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.adapter.RecycleAdapters;
import com.example.asus_pc.jianyue.controller.activity.StartActivity;
import com.example.asus_pc.jianyue.controller.activity.homesActivity.DetailsActivity;
import com.example.asus_pc.jianyue.model.GlideImageLoad;
import com.example.asus_pc.jianyue.model.GsonUtils;
import com.example.asus_pc.jianyue.model.bean.HeadLinBean;
import com.example.asus_pc.jianyue.model.bean.HttpUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by ASUS-PC on 2017/8/18.
 */

public class HeadlinesFragment extends Fragment {
    private XRecyclerView recyclerView;
    private List<HeadLinBean.ItemBean> mlist = new ArrayList<>();
    private Banner banner;
    int page = 1;
    private RecycleAdapters adapters;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private ProgressBar progeress;
   List<String> imagelist = new ArrayList<>();
    List<String> stringlist = new ArrayList<>();
    private View inflate1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(),mPermissionList,123);
        }

        View view1 = inflater.inflate(R.layout.fg_headline, null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progeress.setVisibility(View.GONE);
            }
        }, 2000);

        initView(view1);
        NetWork();

        initData();

        lodemore();
        return view1;
    }

    private void initView( View view1) {
        recyclerView = (XRecyclerView) view1.findViewById(R.id.recycle);
        inflate1 = View.inflate(getActivity(), R.layout.headview, null);
        progeress = (ProgressBar) view1.findViewById(R.id.progress);

    }
    public void NetWork() {
         imagelist.clear();
        stringlist.clear();
        HttpUtils.doGet("http://api.iclient.ifeng.com/ClientNews?id=SYLB10,SYDT10&" + page, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "请求失败了", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                List<HeadLinBean> list = GsonUtils.jsonToArrayList(s, HeadLinBean.class);
                List<HeadLinBean.ItemBean> item = list.get(0).getItem();
                mlist.addAll(item);
                final List<HeadLinBean.ItemBean> item1 = list.get(1).getItem();

                for (int i = 0; i < item1.size(); i++) {
                    String thumbnail = item1.get(i).getThumbnail();
                    String title = item1.get(i).getTitle();

                    imagelist.add(thumbnail);
                    stringlist.add(title);
                    Log.e("sf","+++++++++++++>"+imagelist);

                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        banner = (Banner) inflate1.findViewById(R.id.banner);
                        banner.isAutoPlay(false);
                        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                        banner.setImageLoader(new GlideImageLoad());
                        banner.setImages(imagelist);
                        banner.setBannerTitles(stringlist);
                        banner.start();
                           banner.setOnBannerListener(new OnBannerListener() {
                               @Override
                               public void OnBannerClick(int position) {
                                   String commentsUrl = item1.get(position).getCommentsUrl();
                                   Intent intent=new Intent(getActivity(),DetailsActivity.class);
                                   intent.putExtra("name",commentsUrl);
                                   startActivity(intent);
                               }
                           });


                    }
                });

            }


        });



    }

    private void initData() {
        adapters = new RecycleAdapters(mlist, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
;
        recyclerView.addHeaderView(inflate1);

        recyclerView.setAdapter(adapters);

        adapters.setOnclick(new RecycleAdapters.Listener() {
            @Override
            public void onclick(int position) {
                Intent intent=new Intent(getActivity(), DetailsActivity.class);
                String commentsUrl = mlist.get(position).getCommentsUrl();
                intent.putExtra("name",commentsUrl);
                startActivity(intent);
            }
        });
        adapters.setOnclicktwo(new RecycleAdapters.Listeneritemtwo() {
            @Override
            public void onclicks(int position) {
                Intent intent=new Intent(getActivity(), DetailsActivity.class);
                String commentsUrl = mlist.get(position).getCommentsUrl();
                intent.putExtra("name",commentsUrl);
                startActivity(intent);
            }
        });
        adapters.setOnclickThree(new RecycleAdapters.Listeneritemthree() {
            @Override
            public void onclickss(int position) {
                Intent intent=new Intent(getActivity(), DetailsActivity.class);
                String commentsUrl = mlist.get(position).getCommentsUrl();
                intent.putExtra("name",commentsUrl);
                startActivity(intent);
            }
        });
    }
    private void lodemore() {
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mlist.clear();
                NetWork();
                recyclerView.refreshComplete();
                Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                int itemCount = adapters.getItemCount();
                if (page < 30) {
                    page++;
                    NetWork();
                } else {
                    recyclerView.loadMoreComplete();
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
