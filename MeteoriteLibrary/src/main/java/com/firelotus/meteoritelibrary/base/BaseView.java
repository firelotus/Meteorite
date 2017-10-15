package com.firelotus.meteoritelibrary.base;

/**
 * Created by firelotus on 2017/10/15.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showProgress();

    void dismissProgress();

    void showTip(String message);

    void onError();//网络异常处理
}
