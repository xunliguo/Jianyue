//package com.example.asus_pc.jianyue.adapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.asus_pc.jianyue.R;
//
///**
// * Created by ASUS-PC on 2017/8/18.
// */
//
//public class ListviewAdapters extends BaseAdapter {
//    private Context context;
//    private List<JsonBean> list;
//    ViewHolder holder;
//
//    public MyAdapter(Context context, List<JsonBean> list) {
//        this.context = context;
//        this.list = list;
//
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//           // holder = new ViewHolder();
////            convertView=View.inflate(context, R.layout.item,null);
////            holder.image =(ImageView)convertView.findViewById(R.id.image);
////            holder.good_name=(TextView) convertView.findViewById(R.id.goods_name);
////            holder.integral=(TextView)convertView.findViewById(R.id.integral);
////            holder.likes=(TextView)convertView.findViewById(R.id.likes);
////            convertView.setTag(holder);
////        }else{
////            holder=(ViewHolder) convertView.getTag();
////
////        }
////        holder.good_name.setText(list.get(position).goods_name);
////        holder.integral.setText(list.get(position).integral);
////        holder.likes.setText(list.get(position).likes);
////
////        return convertView;
////    }
////    static  class ViewHolder{
////        ImageView image;
////        TextView good_name;
////        TextView integral;
////        TextView  likes;
//
//
//
//
//
