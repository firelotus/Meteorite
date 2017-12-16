package com.firelotus.meteoritelibrary.tools;

import android.content.Context;

import com.firelotus.meteoritelibrary.base.ICallBack;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.util.TreeMap;

import okhttp3.ResponseBody;

/**
 * Created by firelotus on 2017/12/13.
 */

public enum NovateManager {
    INSTANCE;
    private Novate novate;
    private Context context;
    NovateManager(){
    }

    /**
     * Application中初始化
     * @param context
     * @param baseUrl
     */
    public void init(Context context,String baseUrl){
        if(novate == null){
            novate = new Novate.Builder(context)
                    .baseUrl(baseUrl)
                    .addInterceptor(new NetworkInterceptor(context))
                    .build();
            this.context = context;
        }
    }

    public void mGet(String url, TreeMap<String, Object> parameters, final ICallBack<ResponseBody> callBack){
        //福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
        novate.get(url,parameters,new BaseSubscriber<ResponseBody>(context){
            @Override
            public void onNext(ResponseBody responseBody) {
                callBack.onSusscess(responseBody);
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError();
            }
        });
    }
}
