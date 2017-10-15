package com.firelotus.meteorite.base;

import com.firelotus.meteoritelibrary.application.ErrorReportApplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
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

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("BaseApplication Start");
    }
}
