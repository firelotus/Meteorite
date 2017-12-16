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

    /**
     * 主要用于网络请求回调时的判断,activity和fragment是否可见.
     * @return
     */
    boolean isActive();

    //void showEmpty();//获取数据为空时


    //showerror与showloading配对使用。
    //loading成对使用大的角度如fragment
    //progress成对使用小的角度如提交
    // TODO: 待参考android-mvp-architecture中的MvpView命名
}
