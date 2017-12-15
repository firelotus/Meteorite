package com.firelotus.meteoritelibrary.base;

/**
 * Created by firelotus on 2017/12/13.
 */

public interface ICallBack<T>{//考虑单独一个接口.调用方便
    void onSusscess(T result);
    void onError();
}
