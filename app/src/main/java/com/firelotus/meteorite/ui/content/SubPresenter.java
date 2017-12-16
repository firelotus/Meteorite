package com.firelotus.meteorite.ui.content;

import android.content.Context;

import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.MCallBack;
import com.tamic.novate.Novate;

import java.util.ArrayList;

/**
 * Created by firelotus on 2017/10/15.
 */

public class SubPresenter implements ISubContract.Presenter {
    private ISubContract.View view;
    private Context context;
    private Novate novate;
    private ISubContract.Modle modle;

    public SubPresenter(Context context, ISubContract.View view) {
        this.view = view;
        this.context = context;
        this.modle = new SubModel(context);
    }
    @Override
    public void dealContent(String type, int pageIndex, int pageSize) {
        view.showLoading();
        modle.getContent(type, pageIndex, pageSize, new MCallBack() {
            @Override
            public <T> void onSuccess(T result) {
                view.onContentSuccess((ArrayList<GankBean>)result);
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
        modle.getEveryDay(year, month, day, new MCallBack() {
            @Override
            public <T> void onSuccess(T result) {
                view.onEveryDaySuccess((ArrayList<GankBean>)result);
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
