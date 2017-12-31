package com.firelotus.meteorite.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.firelotus.meteorite.R;
import com.firelotus.meteoritelibrary.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by firelotus on 2017/12/31.
 */

public class FeedBackActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.tv_issues)
    public TextView issues;

    @BindView(R.id.tv_qq)
    public TextView qq;

    @BindView(R.id.tv_email)
    public TextView email;

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, FeedBackActivity.class);
        mContext.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        setToolBarTitle("问题反馈");
        issues.setOnClickListener(this);
        qq.setOnClickListener(this);
        email.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_issues:
                WebActivity.loadUrl(v.getContext(),"https://github.com/firelotus/Meteorite/issues","Issues");
                break;
            case R.id.tv_qq:
                String url = "mqqwpa://im/chat?chat_type=wpa&uin=271512473";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
            case R.id.tv_email:
                Intent data = new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:271512473@qq.com"));
                startActivity(data);
                break;
        }
    }
}
