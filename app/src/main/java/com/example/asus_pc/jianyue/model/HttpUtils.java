package com.example.asus_pc.jianyue.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 爱生活，爱代码
 * 创建于：2017/8/16 11:35
 * 作 者：T
 * 微信：704003376
 */

/**
 * 将HttpUtils实例通过单例提供出去
 */
public class HttpUtils implements IHttp {
    private static HttpUtils mHttpUtils;
    private OkHttpClient mOkHttpClient;

    private HttpUtils() {
        mOkHttpClient = new OkHttpClient();
    }

    public static synchronized IHttp getHttpUtilsInstance() {
        if (mHttpUtils == null)
            mHttpUtils = new HttpUtils();
        return mHttpUtils;
    }

    // http://www.baidu.com?name=zhangsan&pwd=123&;
    @Override
    public <T> void get(String baseUrl, Map<String, String> paramsMap, final MyCallBack<T> callBack) {
        StringBuffer sb = new StringBuffer(baseUrl);
        sb.append("?");
        if (paramsMap != null && paramsMap.size() > 0) {
            for (String key : paramsMap.keySet()) {
                String value = paramsMap.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            sb.toString().trim().substring(0, sb.toString().trim().length() - 1);
        }
        String getUrl = sb.toString().trim();
        Request request = new Request.Builder().url(getUrl).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.faile(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                callBack.success(getParseData(jsonData, callBack));
            }
        });


    }

    /**
     * 通过反射的形式,将CallBack接口的实现类的类类型
     * 然后将类类型传到Gson中，最后得到解析后的结果(类类型对应的实体)
     *
     * @param jsonData
     * @param callBack
     * @param <T>
     * @return
     */
    private <T> T getParseData(String jsonData, MyCallBack<T> callBack) {
        Type[] interfacetypes = callBack.getClass().getGenericInterfaces();
        Type[] types = ((ParameterizedType) interfacetypes[0]).getActualTypeArguments();
        Type type = types[0];
        Gson gson = new Gson();
        T t = gson.fromJson(jsonData, type);
        return t;
    }

    //http://www.baidu.com
    @Override
    public <T> void post(String url, Map<String, String> paramsMap, final MyCallBack<T> callBack) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (String key : paramsMap.keySet()) {
            formBuilder.add(key, paramsMap.get(key));
        }
        FormBody body = formBuilder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.faile(e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                callBack.success(getParseData(jsonData, callBack));
            }
        });
    }
}
