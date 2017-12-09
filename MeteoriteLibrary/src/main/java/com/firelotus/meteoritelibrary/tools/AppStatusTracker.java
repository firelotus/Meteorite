package com.firelotus.meteoritelibrary.tools;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

/**
 * 对Acitivity的生命周期进行集中处理
 * Created by firelotus on 2017/12/9.
 */

public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
    private static AppStatusTracker tracker;
    private Application application;

    private AppStatusTracker(Application application){
        this.application = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application){
        tracker = new AppStatusTracker(application);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Logger.d(activity.getClass().getSimpleName(),"onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Logger.d(activity.getClass().getSimpleName(),"onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Logger.d(activity.getClass().getSimpleName(),"onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Logger.d(activity.getClass().getSimpleName(),"onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Logger.d(activity.getClass().getSimpleName(),"onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Logger.d(activity.getClass().getSimpleName(),"onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Logger.d(activity.getClass().getSimpleName(),"onActivityDestroyed");
    }
}
