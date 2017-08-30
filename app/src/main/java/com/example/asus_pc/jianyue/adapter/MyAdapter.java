package com.example.asus_pc.jianyue.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus_pc.jianyue.R;
import com.example.asus_pc.jianyue.model.bean.MpBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2017/5/11.
 */

public class MyAdapter extends BaseAdapter{


    private Context context;
    private List<MpBean.DataBean.VideoBean> list;
     private  ViewHolder holder;
    public MyAdapter(Context context, List<MpBean.DataBean.VideoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
           holder=new ViewHolder();
        convertView= View.inflate(context, R.layout.videoitem,null);
         holder.text= (TextView) convertView.findViewById(R.id.name);
          holder.image= (ImageView) convertView.findViewById(R.id.timer);
            convertView.setTag(holder);

        }else{
         holder= (ViewHolder) convertView.getTag();

        }
        holder.text.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImg()).into(holder.image);
        return convertView;

    }
    static  class ViewHolder {
        TextView text;
       ImageView  image;

    }
}
