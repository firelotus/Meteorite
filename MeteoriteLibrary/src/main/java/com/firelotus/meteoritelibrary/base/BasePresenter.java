package com.firelotus.meteoritelibrary.base;

/**
 * Created by firelotus on 2017/10/15.
 */

public interface BasePresenter {
    /*interface CallBack<T>{//考虑单独一个接口.调用方便
        void onSusscess(T result);
        void onError();
    }*/

    void detach();
}
