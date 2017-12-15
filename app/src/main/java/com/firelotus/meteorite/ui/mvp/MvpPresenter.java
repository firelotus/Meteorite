package com.firelotus.meteorite.ui.mvp;

import android.content.Context;

import com.firelotus.meteorite.BuildConfig;
import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteorite.ui.content.MvpContract;
import com.firelotus.meteoritelibrary.base.ICallBack;
import com.firelotus.meteoritelibrary.tools.NetworkInterceptor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

import okhttp3.ResponseBody;

/**
 * Created by firelotus on 2017/10/15.
 */

public class MvpPresenter implements MvpContract.Presenter{
    private MvpContract.View view;
    private MvpContract.Model model;
    private Context context;
    private Novate novate;


    public MvpPresenter(Context context, MvpContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        model = new MvpModel(context);
        this.context = context;
        novate = new Novate.Builder(context)
                .baseUrl(BuildConfig.BASE_URL)
                .addInterceptor(new NetworkInterceptor(context))
                .build();
    }


    @Override
    public void getContent(String type, int pageIndex, int pageSize) {
        model.loadContent(context, type, pageIndex, pageSize, new ICallBack<ArrayList<GankBean>>/*onContentListener*/() {
            @Override
            public void /*Content*/onSusscess(ArrayList<GankBean> list) {
                view.onContentSuccess(list);
            }

            @Override
            public void onError() {
                view.showError();
            }
        });

    }

    @Override
    public void getEveryDay(String year, String month, String day) {
        view.showProgress();
        TreeMap<String, Object> parameters = new TreeMap<>();
        novate.get("day/"+year+"/"+month+"/"+day,parameters,new BaseSubscriber<ResponseBody>(context){
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String jstr = new String(responseBody.bytes());
                    //MLog.d(jstr);
                    JSONObject jsonObject = new JSONObject(jstr);
                    JSONArray category = jsonObject.getJSONArray("category");
                    JSONObject results = jsonObject.getJSONObject("results");
                    ArrayList<GankBean> gankBeans = new ArrayList<>();
                    Type type = new TypeToken<ArrayList<GankBean>>() {}.getType();
                    for(int i = 0; i < category.length(); i++){
                        if("Android".equals(category.get(i))){
                            gankBeans.addAll((ArrayList<GankBean>)new Gson().fromJson(String.valueOf(results.getJSONArray("Android")), type));
                        }else if("iOS".equals(category.get(i))){
                            gankBeans.addAll((ArrayList<GankBean>)new Gson().fromJson(String.valueOf(results.getJSONArray("iOS")), type));
                        }else if("前端".equals(category.get(i))){
                            gankBeans.addAll((ArrayList<GankBean>)new Gson().fromJson(String.valueOf(results.getJSONArray("前端")), type));
                        }else if("App".equals(category.get(i))){
                            gankBeans.addAll((ArrayList<GankBean>)new Gson().fromJson(String.valueOf(results.getJSONArray("App")), type));
                        }else if("休息视频".equals(category.get(i))){
                            gankBeans.addAll((ArrayList<GankBean>)new Gson().fromJson(String.valueOf(results.getJSONArray("休息视频")), type));
                        }else if("拓展资源".equals(category.get(i))){
                            gankBeans.addAll((ArrayList<GankBean>)new Gson().fromJson(String.valueOf(results.getJSONArray("拓展资源")), type));
                        }else if("瞎推荐".equals(category.get(i))){
                            gankBeans.addAll((ArrayList<GankBean>)new Gson().fromJson(String.valueOf(results.getJSONArray("瞎推荐")), type));
                        }/*else if("福利".equals(category.get(i))){
                            gankBeans.addAll((ArrayList<GankBean>)new Gson().fromJson(String.valueOf(results.getJSONArray("福利")), type));
                        }*/
                    }
                    //MLog.d(everyDayBean.toString());
                    view.onEveryDaySuccess(gankBeans);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        view.hideProgress();
    }

    @Override
    public void detach() {
        view = null;
    }
}
