package com.firelotus.meteoritelibrary.tools;

import android.util.Log;

import com.firelotus.meteoritelibrary.BuildConfig;

/**
 * 日志管理器，开关统一控制输出
 * Created by firelotus on 2017/10/15.
 */

public class MLog {
    private static final String TAG = "Meteorite";

    // 下面四个是默认tag的函数
    public static void i(String msg)
    {
        if (BuildConfig.DEBUG)
            Log.i(TAG, msg);
    }

    public static void d(String msg)
    {
        if (BuildConfig.DEBUG)
            Log.d(TAG, msg);
    }

    public static void e(String msg)
    {
        if (BuildConfig.DEBUG)
            Log.e(TAG, msg);
    }

    public static void v(String msg)
    {
        if (BuildConfig.DEBUG)
            Log.v(TAG, msg);
    }


    // 下面四个是自定义tag的函数
    public static void i(String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.i(tag,msg);
        }
    }

    public static void d(String tag, String msg){
        if (BuildConfig.DEBUG) {
            Log.d(tag,msg);
        }
    }

    public static void w(String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.w(tag,msg);
        }
    }

    public static void e(String tag, String msg){
        if (BuildConfig.DEBUG){
            Log.e(tag,msg);
        }
    }
}
