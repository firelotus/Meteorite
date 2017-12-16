package com.firelotus.meteoritelibrary.base;

/**
 * Created by firelotus on 2017/10/15.
 */

public interface IBasePresenter {
    void detach();

    /**
     * presenter和对应的view绑定
     * @param mvpView  目标view
     */
    //void attachView(V mvpView);

    /**
     * presenter与view解绑
     */
    //void detachView();
}
