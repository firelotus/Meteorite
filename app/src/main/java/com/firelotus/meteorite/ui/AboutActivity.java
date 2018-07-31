package com.firelotus.meteorite.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.firelotus.meteorite.R;
import com.firelotus.meteoritelibrary.base.BaseActivity;
import com.firelotus.meteoritelibrary.utils.UpdateAppHttpUtil;
import com.vector.update_app.UpdateAppManager;

import butterknife.BindView;

/**
 * Created by firelotus on 2017/10/15.
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener{

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, AboutActivity.class);
        mContext.startActivity(intent);
    }
    @BindView(R.id.tv_update)
    TextView tv_update;

    @BindView(R.id.tv_star)
    TextView tv_star;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        setToolBarTitle("关于");
        tv_update.setOnClickListener(this);
        tv_star.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_update:
                new UpdateAppManager
                        .Builder()
                        //当前Activity
                        .setActivity(AboutActivity.this)
                        //更新地址
                        .setUpdateUrl("https://raw.githubusercontent.com/firelotus/Meteorite/develop/updateJson.txt")
                        //实现httpManager接口的对象
                        .setHttpManager(new UpdateAppHttpUtil())
                        .build()
                        .update();
                break;
            case R.id.tv_star:
                WebActivity.loadUrl(AboutActivity.this, "https://github.com/firelotus/Meteorite","Meteorite");
                break;
            default:
                break;
        }
    }
}
