package com.firelotus.meteoritelibrary.base;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.firelotus.meteoritelibrary.constants.Constants;
import com.firelotus.meteoritelibrary.toast.MToast;
import com.firelotus.meteoritelibrary.utils.DialogUtils;

/**
 * Created by firelotus on 2017/10/14.
 */

public class BaseActivity extends AppCompatActivity implements IBaseView{
    private Dialog progressDialog;
    protected String TAG;
    protected SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //解决软键盘弹出界面变型问题
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        progressDialog = DialogUtils.createProgressDialog(this);
        TAG = getClass().getSimpleName();
        sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void dismissProgress() {
        dismissProgressDialog();
    }

    @Override
    public void showTip(String message) {
        showToast(message);
    }

    public void showProgressDialog() {
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    public void showToast(String msg) {
        MToast.show(this, msg, Toast.LENGTH_SHORT);
    }
}
