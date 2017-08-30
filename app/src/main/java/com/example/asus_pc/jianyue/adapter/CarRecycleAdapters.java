package com.example.asus_pc.jianyue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.model.bean.CarBean;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ASUS-PC on 2017/8/16.
 */

public class CarRecycleAdapters extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private List<CarBean.ItemBean> list;
    private Context context;
    private Listener listener;
    private Listeneritemtwo listeneritemtwo;
    private Listeneritemthree listeneritemthree;
    public CarRecycleAdapters(List<CarBean.ItemBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = null;
      switch (viewType){
         case 1:
           view = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
             holder = new OneViewHoder(view);
         break;
          case 2:
              view = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
              holder = new TwoViewHolder(view);
             break;
          case 3:
              view = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);
              holder = new ThreeViewHoder(view);
          break;
      }
        return holder;

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 1:
                OneViewHoder holerone = (OneViewHoder) holder;
                Picasso.with(context).load(list.get(position).getThumbnail()).into(holerone.item1_image);
                holerone.item_1title.setText(list.get(position).getTitle());
                holerone.item1_laiyuan.setText(list.get(position).getSource());
                holerone.item1_timer.setText(list.get(position).getUpdateTime());
                holerone.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onclick(position);
                    }
                });
                break;
            case 2:
                TwoViewHolder holdertwo = (TwoViewHolder) holder;
                Picasso.with(context).load(list.get(position).getThumbnail()).into(holdertwo.item2_image);
                Picasso.with(context).load(list.get(position).getThumbnail()).into(holdertwo.item2_image2);
                Picasso.with(context).load(list.get(position).getThumbnail()).into(holdertwo.item2_image3);
                holdertwo.item2_title.setText(list.get(position).getTitle());
                holdertwo.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      listeneritemtwo.onclicks(position);
                    }
                });
              break;
            case 3:
                ThreeViewHoder holderthree= (ThreeViewHoder) holder;
                Picasso.with(context).load(list.get(position).getThumbnail()).into(holderthree.item3_image);
                holderthree.item_3title.setText(list.get(position).getTitle());
                holderthree.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listeneritemthree.onclickss(position);
                    }
                });
                break;
        }
    }
    @Override
    public int getItemCount() {
        return list.size();


    }

    public void setOnclick(Listener listeners) {
        this.listener = listeners;

    }
    public void setOnclicktwo(Listeneritemtwo listeneritemtwos) {
        this.listeneritemtwo =listeneritemtwos;

    }
    public void setOnclickThree( Listeneritemthree listeneritemthrees) {
        this.listeneritemthree=listeneritemthrees;

    }
    @Override
    public int getItemViewType(int position) {
       if (list.get(position).getShowType()==null){
           return 3;

       }else if (list.get(position).getShowType().equals("1")){
           return 2;

       }else{
           return 1;
       }

    }



       //one
    public interface Listener {
        void onclick(int position);

    }
    //one
    public interface  Listeneritemtwo {
        void onclicks(int position);

    }
    //one
    public interface Listeneritemthree {
        void onclickss(int position);

    }

    private class OneViewHoder extends RecyclerView.ViewHolder {
        private final TextView item_1title;
        private final ImageView item1_image;
        private final TextView item1_laiyuan;
        private final TextView item1_timer;

        public OneViewHoder(View itemView) {
            super(itemView);
            item_1title = (TextView) itemView.findViewById(R.id.item1_name);
            item1_image = (ImageView) itemView.findViewById(R.id.item1_image);
            item1_laiyuan = (TextView) itemView.findViewById(R.id.item1_aiyuan);
            item1_timer = (TextView) itemView.findViewById(R.id.item1_timer);
        }
    }
    private class ThreeViewHoder extends RecyclerView.ViewHolder {
        private final TextView item_3title;
        private final ImageView item3_image;
        public ThreeViewHoder(View itemView) {
            super(itemView);
            item_3title = (TextView) itemView.findViewById(R.id.item3_name);
            item3_image = (ImageView) itemView.findViewById(R.id.item3_image);
        }
    }
    private class TwoViewHolder extends RecyclerView.ViewHolder {
        private final TextView item2_title;
        private final ImageView item2_image;
        private final ImageView item2_image2;
        private final ImageView item2_image3;
        public TwoViewHolder(View itemView) {
            super(itemView);
            item2_title = (TextView) itemView.findViewById(R.id.item2_name);
            item2_image = (ImageView) itemView.findViewById(R.id.item2_image);
            item2_image2 = (ImageView) itemView.findViewById(R.id.item2_image2);
            item2_image3 = (ImageView) itemView.findViewById(R.id.item2_image3);
        }
    }


}
