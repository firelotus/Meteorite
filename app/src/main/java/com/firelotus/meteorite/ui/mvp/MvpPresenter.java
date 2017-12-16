package com.firelotus.meteorite.ui.mvp;

import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteorite.ui.content.IMvpContract;
import com.firelotus.meteoritelibrary.base.ICallBack;

import java.util.ArrayList;

/**
 * Created by firelotus on 2017/10/15.
 */

public class MvpPresenter implements IMvpContract.Presenter{
    private IMvpContract.View view;
    private IMvpContract.Model model;

    public MvpPresenter(IMvpContract.View view) {
        this.view = view;
        model = new MvpModel();
    }


    @Override
    public void getContent(String type, int pageIndex, int pageSize) {
        model.loadContent(type, pageIndex, pageSize, new ICallBack<ArrayList<GankBean>>/*onContentListener*/() {
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
        model.loadEveryDay(year, month, day, new ICallBack<ArrayList<GankBean>>() {
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
