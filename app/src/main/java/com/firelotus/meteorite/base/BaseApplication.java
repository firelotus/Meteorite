package com.firelotus.meteorite.base;

import com.firelotus.meteoritelibrary.application.ErrorReportApplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by firelotus on 2017/10/14.
 */

public class BaseApplication extends ErrorReportApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("BaseApplication Start");
    }
}
