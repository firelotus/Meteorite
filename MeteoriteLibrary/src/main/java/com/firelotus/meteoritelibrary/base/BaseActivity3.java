package com.firelotus.meteoritelibrary.base;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.firelotus.meteoritelibrary.config.Constants;
import com.firelotus.meteoritelibrary.toast.MToast;
import com.firelotus.meteoritelibrary.utils.DialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by firelotus on 2017/10/14.
 */

public abstract class BaseActivity3 extends AppCompatActivity implements IBaseView{
    protected String TAG;
    protected SharedPreferences sp;
    protected Context context;
    private Dialog progressDialog;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //解决软键盘弹出界面变型问题
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(getLayoutId());
        context = this;
        mUnbinder = ButterKnife.bind(this);
        progressDialog = DialogUtils.createProgressDialog(this);
        TAG = getClass().getSimpleName();
        sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    /**
     * 加载页面布局文件
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 让布局中的view与activity中的变量建立起映射
     */
    protected abstract void initView();

    /**
     * 加载要显示的数据
     */
    protected abstract void initData();

    @Override
    public void showProgress() {
        Log.d("Meteorite","Activity showProgress");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        Log.d("Meteorite","Activity hideProgress");
        progressDialog.dismiss();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showTip(String message) {
        MToast.show(this, message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showError() {

    }
}
