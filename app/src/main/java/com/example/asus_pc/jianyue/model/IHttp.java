package com.example.asus_pc.jianyue.model;

import java.util.Map;

/**
 * 爱生活，爱代码
 * 创建于：2017/8/16 11:29
 * 作 者：T
 * 微信：704003376
 */

public interface IHttp {
    <T> void get(String url, Map<String, String> paramsMap, MyCallBack<T> callBack);
    <T> void post(String url, Map<String, String> paramsMap, MyCallBack<T> callBack);
}
