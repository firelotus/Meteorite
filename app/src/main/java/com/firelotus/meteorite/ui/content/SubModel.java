package com.firelotus.meteorite.ui.content;

import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.ICallBack;
import com.firelotus.meteoritelibrary.tools.MNovateResponse;
import com.firelotus.meteoritelibrary.tools.NovateManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

import okhttp3.ResponseBody;

/**
 * Created by firelotus on 2017/12/16.
 */

public class SubModel implements ISubContract.Modle {

    public SubModel(){
    }

    @Override
    public void getContent(String type, int pageIndex, int pageSize, final ICallBack<ArrayList<GankBean>> callBack) {
        TreeMap<String, Object> parameters = new TreeMap<>();
        //福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
        NovateManager.INSTANCE.get("data/" + type + "/" + pageSize + "/" + pageIndex, parameters, new ICallBack<ResponseBody>() {
            @Override
            public void onSusscess(ResponseBody result) {
                try {
                    String jstr = new String(result.bytes());
                    //MLog.d(jstr);
                    Type type = new TypeToken<MNovateResponse<ArrayList<GankBean>>>() {
                    }.getType();
                    MNovateResponse<ArrayList<GankBean>> response = new Gson().fromJson(jstr, type);
                    callBack.onSusscess(response.getResults());
                } catch (Exception e) {
                    e.printStackTrace();
                    callBack.onError();
                }
            }

            @Override
            public void onError() {
                callBack.onError();
            }
        });

    }

    @Override
    public void getEveryDay(String year, String month, String day, final ICallBack<ArrayList<GankBean>> callBack) {
        TreeMap<String, Object> parameters = new TreeMap<>();
        NovateManager.INSTANCE.get("day/"+year+"/"+month+"/"+day, parameters, new ICallBack<ResponseBody>() {
            @Override
            public void onSusscess(ResponseBody result) {
                try {
                    String jstr = new String(result.bytes());
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
                    callBack.onSusscess(gankBeans);
                } catch (Exception e) {
                    e.printStackTrace();
                    callBack.onError();
                }
            }

            @Override
            public void onError() {
                callBack.onError();
            }
        });

    }
}
