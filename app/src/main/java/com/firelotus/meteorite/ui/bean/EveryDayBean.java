package com.firelotus.meteorite.ui.bean;

import java.util.List;

/**
 * Created by firelotus on 2017/10/21.
 */

public class EveryDayBean {
    //福利 休息视频 瞎推荐 Android 前端 拓展资源
    private List<GankBean> Android;

    private List<GankBean> iOS;

    private List<GankBean> front;

    private List<GankBean> app;

    private List<GankBean> restMovie;

    private List<GankBean> resource;

    private List<GankBean> recommend;

    private List<GankBean> welfare;

    public List<GankBean> getAndroid() {
        return Android;
    }

    public void setAndroid(List<GankBean> android) {
        Android = android;
    }

    public List<GankBean> getiOS() {
        return iOS;
    }

    public void setiOS(List<GankBean> iOS) {
        this.iOS = iOS;
    }

    public List<GankBean> getFront() {
        return front;
    }

    public void setFront(List<GankBean> front) {
        this.front = front;
    }

    public List<GankBean> getApp() {
        return app;
    }

    public void setApp(List<GankBean> app) {
        this.app = app;
    }

    public List<GankBean> getRestMovie() {
        return restMovie;
    }

    public void setRestMovie(List<GankBean> restMovie) {
        this.restMovie = restMovie;
    }

    public List<GankBean> getResource() {
        return resource;
    }

    public void setResource(List<GankBean> resource) {
        this.resource = resource;
    }

    public List<GankBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<GankBean> recommend) {
        this.recommend = recommend;
    }

    public List<GankBean> getWelfare() {
        return welfare;
    }

    public void setWelfare(List<GankBean> welfare) {
        this.welfare = welfare;
    }

    @Override
    public String toString() {
        return "EveryDayBean{" +
                "Android=" + Android +
                ", iOS=" + iOS +
                ", front=" + front +
                ", app=" + app +
                ", restMovie=" + restMovie +
                ", resource=" + resource +
                ", recommend=" + recommend +
                ", welfare=" + welfare +
                '}';
    }
}
