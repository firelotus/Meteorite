package com.firelotus.meteoritelibrary.base;

/**
 * Created by firelotus on 2017/10/15.
 */

public interface BaseView<T> extends IBaseView{
    // TODO: 2017/12/9 参考了android-mvp-architecture中的写法
    void setPresenter(T presenter);
}
