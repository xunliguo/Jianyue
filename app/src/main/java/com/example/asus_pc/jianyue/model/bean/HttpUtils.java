package com.example.asus_pc.jianyue.model.bean;


import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class HttpUtils {
    //TODO 创建OKHttp对象
    private  static OkHttpClient client =null;
    //TODO 构造方法
    public HttpUtils(){}

    public static OkHttpClient getInstance(){
        if (client ==null){
            synchronized (HttpUtils.class){
                if (client ==null){
                    client=new OkHttpClient();
                }
            }
        }
        //TODO 返回client
        return client;
    }

    //TODO Get请求
    public  static  void doGet(String url, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }
    //// TODO: 2017/8/9  Post 发送键值对数据

    public  static  void doPost(String url, Map<String,String> mapParams, Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : mapParams.keySet()) {
            builder.add(key, mapParams.get(key));

        }

        Request request =new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);

    }


    // TODO: 2017/8/9 Post请求发送JSON数据
    public static void doPost(String url, String jsonParams, Callback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }
}
