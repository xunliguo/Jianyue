package com.example.asus_pc.jianyue.model;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS-PC on 2017/6/14.
 */

public class OkHttpManager {	
    private static volatile OkHttpManager manager;
    private  OkHttpClient client;
    private  Handler handler;

    public  OkHttpManager (){
        client = new OkHttpClient();
        handler = new Handler();


    }
    public  static  OkHttpManager getInstance(){
        if (manager==null){
            manager=new OkHttpManager();
        }
        return manager;
    }
    interface  Callacks{
    //    void show(Bean bean);

    }
    public  void  getqingqiu(String url, final Callacks calls){
        Request request=new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
              //  final Bean bean = gson.fromJson(string, Bean.class);
              //  handler.post(new Runnable() {
               //     @Override
                //    public void run() {
               //         calls.show(bean);
                    }
                });
            }
     //   });

   // }
}
