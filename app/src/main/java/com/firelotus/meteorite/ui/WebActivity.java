package com.firelotus.meteorite.ui;

import android.content.Intent;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.firelotus.meteorite.R;
import com.firelotus.meteoritelibrary.base.BaseActivity;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;

import butterknife.BindView;

/**
 * Created by firelotus on 2017/12/10.
 */

public class WebActivity extends BaseActivity {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_URL = "url";
    @BindView(R.id.ll_webview)
    LinearLayout llWebview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        final String name = intent.getStringExtra(EXTRA_NAME);
        final String url = intent.getStringExtra(EXTRA_URL);
        setToolBarTitle(name);
        AgentWeb mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(llWebview, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {

                    }
                }) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(url);

    }
}
