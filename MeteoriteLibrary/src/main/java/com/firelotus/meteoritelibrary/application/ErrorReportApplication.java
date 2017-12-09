package com.firelotus.meteoritelibrary.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.firelotus.meteoritelibrary.tools.AppStatusTracker;
import com.firelotus.meteoritelibrary.utils.ExceptionWriter;

/**
 * Created by firelotus on 2017/10/14.
 * 全局事件处理
 */

public class ErrorReportApplication extends Application{
    protected static Handler mHandler;
    private Thread.UncaughtExceptionHandler exceptionHandler;
    private Context mContext;
    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            new ExceptionWriter(ex, mContext).saveStackTraceToSD();
            exceptionHandler.uncaughtException(thread, ex);
        }
    };

    public static Handler getMainThreadHandler() {
        return mHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler();
        AppStatusTracker.init(this);
        Utils.init(this);
        exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }
}
