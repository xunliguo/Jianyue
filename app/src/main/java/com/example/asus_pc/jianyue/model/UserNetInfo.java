package com.example.asus_pc.jianyue.model;

/**
 * 爱生活，爱代码
 * 创建于：2017/8/15 12:01
 * 作 者：T
 * 微信：704003376
 */

import android.os.SystemClock;

import com.example.asus_pc.jianyue.model.bean.User;


/**
 * Model层访问并解析网络数据
 */
public class UserNetInfo {

    /**
     * 模拟访问网络数据的方法  MODEL层
     * @param user
     * @return
     */
    public static boolean getNetWorkData(User user) {
        SystemClock.sleep(2000);
        if ("jiyun".equals(user.getmName()) && "123".equals(user.getmPwd()))
            return true;
        else
            return false;
    }


}
