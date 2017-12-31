package com.firelotus.meteoritelibrary.tools;

import android.content.Context;

import com.tamic.novate.Novate;

/**
 * Created by firelotus on 2017/12/13.
 */

public enum NovateManager {
    INSTANCE;
    public Novate novate;
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

}
