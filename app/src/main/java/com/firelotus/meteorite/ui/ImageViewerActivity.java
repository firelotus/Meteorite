package com.firelotus.meteorite.ui;

import android.content.Context;
import android.content.Intent;

import com.bumptech.glide.Glide;
import com.firelotus.meteorite.R;
import com.firelotus.meteoritelibrary.base.BaseActivity;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;

/**
 * Created by firelotus on 2017/12/31.
 */

public class ImageViewerActivity extends BaseActivity {
    public static final String URL = "url";
    @BindView(R.id.pv_viewer)
    public PhotoView photoView;

    public static void start(Context context,String url){
        Intent intent = new Intent(context,ImageViewerActivity.class);
        intent.putExtra(URL,url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_imageviewer;
    }

    @Override
    protected void initView() {
        Glide.with(this).load(getIntent().getStringExtra(URL)).into(photoView);
    }

    @Override
    protected void initData() {

    }
}
