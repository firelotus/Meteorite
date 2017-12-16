package com.firelotus.meteorite.ui.content;

import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.BasePresenter;
import com.firelotus.meteoritelibrary.base.IBaseView;

import java.util.ArrayList;

/**
 * Created by firelotus on 2017/10/15.
 */

public interface SubContract {
    interface View extends IBaseView {
        void onContentSuccess(ArrayList<GankBean> list);
        void onEveryDaySuccess(ArrayList<GankBean> list);
    }
    interface Presenter extends BasePresenter {
        void getContent(String type,int pageIndex, int pageSize);
        void getEveryDay(String year, String month, String day);
    }
}
