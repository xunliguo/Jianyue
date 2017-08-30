package com.example.asus_pc.jianyue.utils;



import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.asus_pc.jianyue.global.MyApp;

import org.androidannotations.annotations.App;


/**
 * 2015年8月15日 16:34:37
 * 博文地址：http://blog.csdn.net/u010156024
 */
public class MobileUtils {

	public static void hideInputMethod(View view) {
		InputMethodManager imm = (InputMethodManager) MyApp.sContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm.isActive()) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
}
