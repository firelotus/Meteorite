package com.firelotus.meteorite.ui.content;

import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.ICallBack;

import java.util.ArrayList;

/**
 * Created by firelotus on 2017/10/15.
 */

public class SubPresenter implements ISubContract.Presenter {
    private ISubContract.View view;
    private ISubContract.Modle modle;

    public SubPresenter(ISubContract.View view) {
        this.view = view;
        this.modle = new SubModel();
    }
    @Override
    public void dealContent(String type, int pageIndex, int pageSize) {
        view.showLoading();
        modle.getContent(type, pageIndex, pageSize, new ICallBack<ArrayList<GankBean>>() {
            @Override
            public void onSusscess(ArrayList<GankBean> result) {
                view.onContentSuccess(result);
            }

            @Override
            public void onError() {
                view.showError();
            }
        });
        view.hideLoading();
    }

    @Override
    public void dealEveryDay(String year, String month, String day) {
        view.showProgress();
        modle.getEveryDay(year, month, day, new ICallBack<ArrayList<GankBean>>() {
            @Override
            public void onSusscess(ArrayList<GankBean> result) {
                view.onEveryDaySuccess(result);
            }

            @Override
            public void onError() {
                view.showError();
            }
        });
        view.hideProgress();
    }

    @Override
    public void detach() {
        view = null;
    }
}
