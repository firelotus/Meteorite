package com.firelotus.meteorite.base;

import com.firelotus.meteorite.BuildConfig;
import com.firelotus.meteoritelibrary.application.ErrorReportApplication;
import com.firelotus.meteoritelibrary.tools.NovateManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by firelotus on 2017/10/14.
 */

public class BaseApplication extends ErrorReportApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("MJson")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG; //控制日志是否打印
            }
        });
        Logger.d("BaseApplication Start");

        //初始化网络
        NovateManager.INSTANCE.init(getApplicationContext(),BuildConfig.BASE_URL);
    }
}
