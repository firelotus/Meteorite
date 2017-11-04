package com.firelotus.meteoritelibrary.base;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firelotus.meteoritelibrary.R;
import com.firelotus.meteoritelibrary.config.Constants;
import com.firelotus.meteoritelibrary.toast.MToast;
import com.firelotus.meteoritelibrary.utils.DialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 内置toolbar,加载，异常界面
 * Created by firelotus on 2017/10/14.
 */

public abstract class BaseToobarActivity extends AppCompatActivity implements IBaseView{
    private Dialog progressDialog;
    protected String TAG;
    protected SharedPreferences sp;
    protected Context context;
    private Unbinder mUnbinder;

    private View activity_base;
    private View activity;
    protected LayoutInflater inflate;

    private LinearLayout llProgressBar;
    private View refresh;
    private AnimationDrawable mAnimationDrawable;
    private Toolbar toolBar;
    private boolean mIsSupportToolBar = true;

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

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
    public void setContentView(@LayoutRes int layoutResID) {
        //super.setContentView(layoutResID);
        inflate = LayoutInflater.from(this);
        activity_base = inflate.inflate(R.layout.activity_base, null, false);
        activity = inflate.inflate(layoutResID, null, false);

        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        activity.setLayoutParams(params);
        RelativeLayout mContainer = (RelativeLayout) activity_base.findViewById(R.id.container);
        mContainer.addView(activity);
        getWindow().setContentView(activity_base);

        llProgressBar = getView(R.id.ll_progress_bar);
        refresh = getView(R.id.ll_error_refresh);
        ImageView img = getView(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        if(mIsSupportToolBar){
            setToolBar();
        }else{
            toolBar = getView(R.id.tool_bar);
            toolBar.setVisibility(View.GONE);
        }

        // 点击加载失败布局
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        activity.setVisibility(View.GONE);
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        toolBar = getView(R.id.tool_bar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setSupportActionBar(boolean isSupport){
        this.mIsSupportToolBar = isSupport;
    }
    public void setToolBarTitle(CharSequence text) {
        toolBar.setTitle(text);
    }

    protected void showLoading() {
        if (llProgressBar.getVisibility() != View.VISIBLE) {
            llProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (activity_base.getVisibility() != View.GONE) {
            activity_base.setVisibility(View.GONE);
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (activity.getVisibility() != View.VISIBLE) {
            activity.setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.VISIBLE) {
            refresh.setVisibility(View.VISIBLE);
        }
        if (activity.getVisibility() != View.GONE) {
            activity.setVisibility(View.GONE);
        }
    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

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
        showProgressDialog();
    }

    @Override
    public void dismissProgress() {
        Log.d("Meteorite","Activity dismissProgress");
        dismissProgressDialog();
    }

    @Override
    public void showTip(String message) {
        showToast(message);
    }

    private void showProgressDialog() {
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    private void showToast(String msg) {
        MToast.show(this, msg, Toast.LENGTH_SHORT);
    }

}
