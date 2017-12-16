package com.firelotus.meteorite.ui.mvp;

import android.content.Context;

import com.firelotus.meteorite.BuildConfig;
import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteorite.ui.content.IMvpContract;
import com.firelotus.meteoritelibrary.base.ICallBack;
import com.firelotus.meteoritelibrary.tools.MNovateResponse;
import com.firelotus.meteoritelibrary.tools.NovateManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.tamic.novate.Novate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

import okhttp3.ResponseBody;

/**
 * Created by firelotus on 2017/12/10.
 */

public class MvpModel implements IMvpContract.Model {

    private Novate novate;

    public MvpModel(Context context){
        /*novate = new Novate.Builder(context)
                .baseUrl(BuildConfig.BASE_URL)
                .addInterceptor(new NetworkInterceptor(context))
                .build();*/
        //
        novate = NovateManager.INSTANCE.init(context,BuildConfig.BASE_URL);
        Logger.d("novate = "+novate.toString());
    }

    @Override
    public void loadContent(Context context, String type, int pageIndex, int pageSize, final /*IMvpContract.Presenter.onContentListener*//*IMvpContract.Presenter.*/ICallBack<ArrayList<GankBean>> onContentListener) {
        /*TreeMap<String, Object> parameters = new TreeMap<>();
        //福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
        novate.get("data/"+type+"/"+pageSize+"/"+pageIndex,parameters,new BaseSubscriber<ResponseBody>(context){
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String jstr = new String(responseBody.bytes());
                    //MLog.d(jstr);
                    Type type = new TypeToken<MNovateResponse<ArrayList<GankBean>>>() {
                    }.getType();
                    MNovateResponse<ArrayList<GankBean>> response = new Gson().fromJson(jstr, type);
                    //MLog.d(response.toString());
                    //onContentListener.onContentSusscess(response.getResults());
                    onContentListener.onSusscess(response.getResults());
                } catch (IOException e) {
                    e.printStackTrace();
                    onContentListener.onError();
                }

            }

            @Override
            public void onError(Throwable e) {
                onContentListener.onError();
            }
        });*/
        TreeMap<String, Object> parameters = new TreeMap<>();
        NovateManager.INSTANCE.mGet("data/" + type + "/" + pageSize + "/" + pageIndex, parameters, new ICallBack<ResponseBody>() {
            @Override
            public void onSusscess(ResponseBody result) {
                try {
                    String jstr = new String(result.bytes());
                    //MLog.d(jstr);
                    Type type = new TypeToken<MNovateResponse<ArrayList<GankBean>>>() {
                    }.getType();
                    MNovateResponse<ArrayList<GankBean>> response = new Gson().fromJson(jstr, type);
                    onContentListener.onSusscess(response.getResults());
                } catch (IOException e) {
                    e.printStackTrace();
                    onContentListener.onError();
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}
