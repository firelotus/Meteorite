package com.firelotus.meteorite.ui;

import android.content.Context;
import android.content.Intent;

import com.firelotus.meteoritelibrary.base.BaseActivity;

/**
 * Created by firelotus on 2017/10/15.
 */

public class AboutActivity extends BaseActivity{

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, AboutActivity.class);
        mContext.startActivity(intent);
    }
}
