package com.firelotus.meteorite.ui;

/**
 * Created by firelotus on 2018/1/1.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.bumptech.glide.Glide;
import com.firelotus.meteorite.R;
import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.BaseActivity;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class ViewPagerActivity extends BaseActivity {
    private int pos;
    private ArrayList<GankBean> list = new ArrayList<>();
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(list);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if(intent == null){
            return;
        }
        pos = intent.getIntExtra("pos",0);
        ArrayList<GankBean> tmpList = (ArrayList<GankBean>) intent.getSerializableExtra("list");
        if(tmpList != null){
            list.clear();
            list.addAll(tmpList);
            adapter.notifyDataSetChanged();
        }

        //切换到选中页
        viewPager.setCurrentItem(pos);
    }

    static class ViewPagerAdapter extends PagerAdapter {
        private ArrayList<GankBean> gankBeans = new ArrayList<>();

        public ViewPagerAdapter(ArrayList<GankBean> list){
            this.gankBeans = list;
        }

        @Override
        public int getCount() {
            return gankBeans.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            //photoView.setImageURI(Uri.parse(gankBeans.get(position).getUrl()));
            Glide.with(container.getContext()).load(gankBeans.get(position).getUrl()).into(photoView);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    public static void start(Context context, int pos, ArrayList<GankBean> gankBeans){
        Intent intent = new Intent(context,ViewPagerActivity.class);
        intent.putExtra("pos",pos);
        intent.putExtra("list",gankBeans);
        context.startActivity(intent);
    }
}