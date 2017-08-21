package com.example.asus_pc.jianyue.model.bean;

/**
 * 爱生活，爱代码
 * 创建于：2017/8/15 10:47
 * 作 者：T
 * 微信：704003376
 */

public class User {
    private String mName;
    private String mPwd;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPwd() {
        return mPwd;
    }

    public void setmPwd(String mPwd) {
        this.mPwd = mPwd;
    }


    @Override
    public String toString() {
        return "User{" +
                "mName='" + mName + '\'' +
                ", mPwd='" + mPwd + '\'' +
                '}';
    }


    public User(String mName, String mPwd) {
        this.mName = mName;
        this.mPwd = mPwd;
    }
}
