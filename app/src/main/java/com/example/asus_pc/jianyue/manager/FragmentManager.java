package com.example.asus_pc.jianyue.manager;

/**
 * 爱生活，爱代码
 * 创建于：2017/8/17 12:01
 * 作 者：T
 * 微信：704003376
 */

import android.support.v4.app.FragmentTransaction;

import com.example.asus_pc.jianyue.base.BaseFragment;
import com.example.asus_pc.jianyue.global.MyApp;


/**
 * Fragment管理类，用来管理碎片之间的切换
 */
public class FragmentManager {
    //记录上一个Fragment
    private static BaseFragment mLastFragment;


    /**
     * 切换碎片的时候调用
     * NewsFragment   MusicFragment  VideoFragment  ImageFragment
     */

    /** 碎片之间的切换
     * @param fragmentClass BaseFragmet的实现类
     * @param isHideAndShow 切换碎片用的是哪种方法(true代表add,hide,show,false代表是replace)
     * @param containerId   容器ID
     * @param isBack        是否
     * @return
     */
    public static BaseFragment changeFragment(Class<? extends BaseFragment> fragmentClass, boolean isHideAndShow, int containerId, boolean isBack) {
        android.support.v4.app.FragmentManager manager = MyApp.mContext.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        String fragmentName = fragmentClass.getSimpleName();
        BaseFragment currentFragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
        try {
            if (currentFragment == null) {
                currentFragment = fragmentClass.newInstance();
                transaction.add(containerId, currentFragment, fragmentName);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //如果是true 就使用hide()和show();  false就使用replace()方法
        if (isHideAndShow)
            if (mLastFragment != null && isBack)
                transaction.hide(mLastFragment).show(currentFragment);
            else
                transaction.replace(containerId, currentFragment, fragmentName);

        //就需要添加回退栈了
        //true代表添加到回退栈  AFragment--跳转到--->BFragment---按下--->back键----->AFragment  添加回退栈
        //false 代表不添加回退栈   AFragment--跳转到--->BFragment---按下--->back键----->退出程序  不添加回退栈
        if (isBack) {
            transaction.addToBackStack(fragmentName);
            mLastFragment = currentFragment;
        }
        transaction.commit();
        return mLastFragment;
    }


}
