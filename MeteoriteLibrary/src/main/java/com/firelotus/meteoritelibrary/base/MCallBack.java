package com.firelotus.meteoritelibrary.base;

/**
 * Created by firelotus on 2017/12/16.
 */

public interface MCallBack {
    <T> void onSuccess(T result);
    void onError();
}
