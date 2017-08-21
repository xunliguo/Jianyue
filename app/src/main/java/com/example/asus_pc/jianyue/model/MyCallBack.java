package com.example.asus_pc.jianyue.model;

/**
 * 爱生活，爱代码
 * 创建于：2017/8/16 11:33
 * 作 者：T
 * 微信：704003376
 */

public interface MyCallBack<T> {
    void success(T t);
    void faile(String msg);
}
