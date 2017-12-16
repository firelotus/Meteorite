package com.firelotus.meteoritelibrary.base;

/**
 * Created by firelotus on 2017/12/13.
 */

public interface ICallBack<T>{//此种实现方式由接口决定返回值类型,自上而下,操作效果更顺畅
    void onSusscess(T result);
    void onError();
}

/*
public interface MCallBack {//此种实现方式返回值可传任意类型,自下而上,操作不便
    <T> void onSuccess(T result);
    void onError();
}
*/
