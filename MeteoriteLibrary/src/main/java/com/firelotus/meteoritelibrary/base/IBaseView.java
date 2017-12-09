package com.firelotus.meteoritelibrary.base;

/**
 * Created by firelotus on 2017/10/14.
 */

public interface IBaseView {
    void showProgress();

    void hideProgress();

    void showLoading();

    void hideLoading();

    void showTip(String message);

    void showError();


    //showerror与showloading配对使用。
    //loading成对使用大的角度如fragment
    //progress成对使用小的角度如提交
    // TODO: 待参考android-mvp-architecture中的MvpView命名
}
