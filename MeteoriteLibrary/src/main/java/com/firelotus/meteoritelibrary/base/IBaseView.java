package com.firelotus.meteoritelibrary.base;

/**
 * Created by firelotus on 2017/10/14.
 */

public interface IBaseView {
    void showProgress();

    void dismissProgress();

    void showTip(String message);
}
