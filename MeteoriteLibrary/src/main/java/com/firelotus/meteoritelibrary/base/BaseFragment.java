package com.firelotus.meteoritelibrary.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

/**
 * Created by firelotus on 2017/10/14.
 */

public class BaseFragment extends Fragment implements IBaseView {

    protected BaseActivity baseActivity;
    protected String TAG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决软键盘弹出界面变型问题
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        baseActivity = (BaseActivity)getActivity();
        TAG = getClass().getSimpleName();
    }

    @Override
    public void showProgress() {
        baseActivity.showProgressDialog();
    }

    @Override
    public void dismissProgress() {
        baseActivity.dismissProgressDialog();
    }

    @Override
    public void showTip(String message) {
        baseActivity.showToast(message);
    }
}
