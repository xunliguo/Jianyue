package com.example.asus_pc.jianyue.controller.activity.homesActivity.picturefragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.asus_pc.jianyue.ImageBrowseActivity;
import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.adapter.ImageAdaptes;
import com.example.asus_pc.jianyue.model.bean.HttpUtils;
import com.example.asus_pc.jianyue.model.bean.ImageBean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS-PC on 2017/8/19.
 */

public class BeautyFragment extends Fragment {
    private PopupWindow popupWindow;
    private List<ImageBean.ImgsBean> mlist = new ArrayList<>();
    private XRecyclerView recycle;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private String url = "http://image.baidu.com/data/imgs?col=%E7%BE%8E%E5%A5%B3&tag=%E5%85%A8%E9%83%A8&pn=0&rn=20&from=1";
    private ProgressBar progressbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fg_beaut, null);
      handler.postDelayed(new Runnable() {
          @Override
          public void run() {
           progressbar.setVisibility(View.GONE);


          }
      },2000);

        initView(inflate);
        netWork();
        initData();
        lodemore();
        return inflate;
    }

    private void netWork() {
        HttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new Gson();
                ImageBean imageBean = gson.fromJson(s, ImageBean.class);
                List<ImageBean.ImgsBean> beanlist = imageBean.getImgs();
                mlist.addAll(beanlist);
            }


        });

    }
    private void lodemore() {
        recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mlist.clear();
                netWork();
                initData();
                recycle.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                recycle.loadMoreComplete();
                Toast.makeText(getActivity(), "没有更多", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initData() {
        ImageAdaptes adapters = new ImageAdaptes(mlist, getActivity());
        recycle.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recycle.setAdapter(adapters);
        adapters.notifyDataSetChanged();
        adapters.setOnclick(new ImageAdaptes.Listener() {
            @Override
            public void onclick(int position, View view) {
                String imageUrl = mlist.get(position).getImageUrl();
                Intent intent = new Intent(getActivity(), ImageBrowseActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("imgs", imageUrl);
                startActivity(intent);



            }
        });
    }


    private void initView(View inflate) {
        recycle = (XRecyclerView) inflate.findViewById(R.id.recycle);
        progressbar = (ProgressBar) inflate.findViewById(R.id.progressbar);

    }


}
