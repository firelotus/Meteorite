package com.firelotus.meteorite.ui;

import android.content.Context;
import android.content.Intent;

import com.firelotus.meteorite.R;
import com.firelotus.meteoritelibrary.base.BaseActivity;

/**
 * Created by firelotus on 2017/10/15.
 */

public class AboutActivity extends BaseActivity {

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, AboutActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        setToolBarTitle("关于");
    }

    @Override
    protected void initData() {

    }

}
