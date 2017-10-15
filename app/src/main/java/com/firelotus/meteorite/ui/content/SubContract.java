package com.firelotus.meteorite.ui.content;

import com.firelotus.meteoritelibrary.base.BasePresenter;
import com.firelotus.meteoritelibrary.base.BaseView;

/**
 * Created by firelotus on 2017/10/15.
 */

public interface SubContract {
    interface View extends BaseView<Presenter> {
        void success(String message);
    }
    interface Presenter extends BasePresenter {
        void getContent(String username,String password);
    }
}
