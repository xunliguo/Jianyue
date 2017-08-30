package com.example.asus_pc.jianyue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.model.bean.ImageBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2017/8/22.
 */

public class MusicAdaptes extends XRecyclerView.Adapter<MusicAdaptes.ViewHolder> {
private List<ImageBean.ImgsBean> list;
    private Context context;
  private Listener listener;
    private List<Integer> heights;

    public MusicAdaptes(List<ImageBean.ImgsBean> list, Context context) {
        this.list = list;
        this.context = context;
        //getRandomHeight(this.list);
    }

    @Override
    public MusicAdaptes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.music_item, null);
        ViewHolder vholer=new ViewHolder(inflate);
        return vholer;
    }

    @Override
    public void onBindViewHolder(MusicAdaptes.ViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getImageUrl()).error(R.mipmap.ic_launcher).into(holder.imageView);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onclick(position,v);
        }
    });
    }
   public void setOnclick(Listener listeners){
       this.listener=listeners;

   }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final  ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
          imageView = (ImageView) itemView.findViewById(R.id.b_image);


        }
    }
    public interface  Listener{
      void  onclick(int position, View view);

    }
    private void getRandomHeight(List<ImageBean.ImgsBean> lists){//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            heights.add((int)(200+Math.random()*400));
        }
    }
}
