package com.firelotus.meteoritelibrary.tools;

import android.os.Handler;
import android.os.HandlerThread;

import com.firelotus.meteoritelibrary.application.ErrorReportApplication;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by firelotus on 2017/12/9.
 */

public class DelayTask {

    private static Handler mDelayTaskHandler;

    static{
        HandlerThread ioThread = new HandlerThread("DelayTaskHandler");
        ioThread.start();
        mDelayTaskHandler = new Handler(ioThread.getLooper());
    }

    private AtomicBoolean mRunning = new AtomicBoolean(false);
    private int duration;
    private Runnable runnable;
    private Handler handler;

    public DelayTask(ThreadType threadType, final Callback callback, int duration){
        this.duration = duration;
        if(threadType == ThreadType.MAIN_THREAD){
            handler = ErrorReportApplication.getMainThreadHandler();
        }else{
            handler = mDelayTaskHandler;
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                if(mRunning.get()){
                    callback.work();
                    mRunning.set(false);
                }
            }
        };
    }

    public void start(){
        mRunning.set(true);
        handler.removeCallbacks(runnable);
        if(duration > 0){
            handler.postDelayed(runnable, duration);
        }else{
            handler.post(runnable);
        }
    }

    public void cancel(){
        mRunning.set(false);
        handler.removeCallbacks(runnable);
    }

    public enum ThreadType{
        MAIN_THREAD, WORK_THREAD
    }

    public interface Callback {
        void work();
    }
}