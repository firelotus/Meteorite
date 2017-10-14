package com.firelotus.meteoritelibrary.application;

import android.app.Application;
import android.content.Context;

import com.firelotus.meteoritelibrary.utils.ExceptionWriter;

/**
 * Created by firelotus on 2017/10/14.
 * 全局事件处理
 */

public class ErrorReportApplication extends Application{
    private Thread.UncaughtExceptionHandler exceptionHandler;
    private Context mContext;

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            new ExceptionWriter(ex, mContext).saveStackTraceToSD();
            exceptionHandler.uncaughtException(thread, ex);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }
}
