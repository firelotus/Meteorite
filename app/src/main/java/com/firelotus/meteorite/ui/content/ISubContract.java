package com.firelotus.meteorite.ui.content;

import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.IBaseModel;
import com.firelotus.meteoritelibrary.base.IBasePresenter;
import com.firelotus.meteoritelibrary.base.IBaseView;
import com.firelotus.meteoritelibrary.base.ICallBack;

import java.util.ArrayList;

/**
 * Created by firelotus on 2017/10/15.
 */

public interface ISubContract {
    interface View extends IBaseView {
        void onContentSuccess(ArrayList<GankBean> list);
        void onEveryDaySuccess(ArrayList<GankBean> list);
    }

    interface Presenter extends IBasePresenter {
        void dealContent(String type, int pageIndex, int pageSize);
        void dealEveryDay(String year, String month, String day);
    }

    interface Modle extends IBaseModel {
        void getContent(String type, int pageIndex, int pageSize, ICallBack<ArrayList<GankBean>> callBack);
        void getEveryDay(String year, String month, String day, ICallBack<ArrayList<GankBean>> callBack);
    }
}
