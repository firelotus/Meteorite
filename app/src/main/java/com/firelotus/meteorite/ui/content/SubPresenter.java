package com.firelotus.meteorite.ui.content;

import android.content.Context;

/**
 * Created by firelotus on 2017/10/15.
 */

public class SubPresenter implements SubContract.Presenter {
    private SubContract.View view;
    private Context context;

    public SubPresenter(Context context, SubContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.context = context;
    }
    @Override
    public void getContent(String username, String password) {
        view.showProgress();
        //novate do some thing
        view.success("network finished");
        view.dismissProgress();
    }
}
