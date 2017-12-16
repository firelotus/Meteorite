package com.firelotus.meteorite.ui.content;

import android.content.Context;

import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.IBaseModel;
import com.firelotus.meteoritelibrary.base.IBasePresenter;
import com.firelotus.meteoritelibrary.base.IBaseView;
import com.firelotus.meteoritelibrary.base.ICallBack;

import java.util.ArrayList;

/**
 * Created by firelotus on 2017/10/15.
 */

public interface IMvpContract {
    interface View extends IBaseView {
        void onContentSuccess(ArrayList<GankBean> list);
        void onEveryDaySuccess(ArrayList<GankBean> list);
    }
    interface Presenter extends IBasePresenter {
        void getContent(String type,int pageIndex, int pageSize);
        void getEveryDay(String year, String month, String day);

        /*interface onContentListener{
            void onContentSusscess(ArrayList<GankBean> list);
            void onError();
        }*/
    }

    /**
     * 考虑放在一起或独立
     */
    interface Model extends IBaseModel {
        void loadContent(Context context, String type, int pageIndex, int pageSize,/*Presenter.onContentListener onContentListener*//*Presenter.*/ICallBack<ArrayList<GankBean>> onContentListener);
    }
}
