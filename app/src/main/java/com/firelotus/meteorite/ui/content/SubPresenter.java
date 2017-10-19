package com.firelotus.meteorite.ui.content;

import android.content.Context;

import com.firelotus.meteorite.BuildConfig;
import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.tools.MLog;
import com.firelotus.meteoritelibrary.tools.MNovateResponse;
import com.firelotus.meteoritelibrary.tools.NetworkInterceptor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

import okhttp3.ResponseBody;

/**
 * Created by firelotus on 2017/10/15.
 */

public class SubPresenter implements SubContract.Presenter {
    private SubContract.View view;
    private Context context;
    private Novate novate;

    public SubPresenter(Context context, SubContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.context = context;
        novate = new Novate.Builder(context)
                .baseUrl(BuildConfig.BASE_URL)
                .addInterceptor(new NetworkInterceptor(context))
                .build();
    }
    @Override
    public void getContent(String type, int pageIndex, int pageSize) {
        view.showProgress();
        TreeMap<String, Object> parameters = new TreeMap<>();
        //福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
        novate.get("data/"+type+"/"+pageSize+"/"+pageIndex,parameters,new BaseSubscriber<ResponseBody>(context){
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String jstr = new String(responseBody.bytes());
                    MLog.d(jstr);
                    Type type = new TypeToken<MNovateResponse<ArrayList<GankBean>>>() {
                    }.getType();
                    MNovateResponse<ArrayList<GankBean>> response = new Gson().fromJson(jstr, type);
                    MLog.d(response.toString());
                    view.onSuccess(response.getResults());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        view.dismissProgress();
    }
}
