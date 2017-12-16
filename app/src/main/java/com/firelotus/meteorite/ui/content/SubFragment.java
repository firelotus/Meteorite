package com.firelotus.meteorite.ui.content;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firelotus.meteorite.R;
import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.BaseFragment;
import com.firelotus.meteoritelibrary.tools.GlideImageLoader;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by firelotus on 2017/10/15.
 */

public class SubFragment extends BaseFragment implements SubContract.View{
    public static final String INTENT_STRING_TABNAME = "intent_String_tabName";
    public static final String INTENT_INT_POSITION = "intent_int_position";
    public static String INDEX = "position";
    @BindView(R.id.xRecyclerView) XRecyclerView xRecyclerView;
    @BindView(R.id.banner) Banner banner;
    @BindView(R.id.fragment_mainTab_item_textView) TextView textView;
    private CommonAdapter<GankBean> adapter;
    private List<GankBean> gankBeans = new ArrayList<>();

    private SubContract.Presenter presenter;
    private int pos = 0;
    private int pageIndex = 1;
    private int pageSize = 10;
    private String type = "Android";//福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    private String tabName;
    private int position;
    //private TextView textView;
    private String[] images;

    @Override
    protected void initView() {
        showLoading();
        //xRecyclerView = findViewById(R.id.xRecyclerView);
        pos = getArguments().getInt(INDEX);
        //textView = findViewById(R.id.fragment_mainTab_item_textView);
        images= new String[] {
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1506510233d297d6a9ad1f5d728c78ab989c0e73db.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_15084703014624b835b7768a099213b2e4d4d1617b.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_15083977422c9bfc61c881a4fcc9142f6bf70aa4e1.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_150847891021bcf6ec15fc6632cd1fd4593d120459.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1508468206c5311a2eacd8d6649da69bf67ae0f979.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_150843285441a8ff2395f1bb3eaf87d12aa259caf4.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1508494984de7b6a8b67bc18a41ac9c9afb0919b67.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1509680647c45d70cf96acd19150989cede412c35e.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_15095969207f4971cccc0cb7d3271da0956bb40567.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1509625469d1688480b8b6756dfe0bfca25c5ea7f5.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1509633617a8b82fea0a3a75c5d48916d7a2d193ce.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1509708074e6292172a5c99e0934ff393d330e7231.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1509080352ad7aa30ca379da3ba1d750dc58aa3f5a.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1509036531c0d6c0f65bfe25af7080855f88f82608.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1509082596e9b61e773687f2d45faebedcb2953255.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1508927939c970d94eb180f0a00658f0c17d61fe8a.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1509046891388ac9acccc29b1ef826dbea8f0a86ea.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_508a2a70e59111e96bf77cd242d634c7.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_f8008d03b3a79ebcf5102ccac21263aa.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_e4edf96bc36f10e1c00e26466e8725c2.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_c4e765e217bf56a36542b20c78024757.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_610649c240443298854e651098537460.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_ec9c0fe6a37f0c238c5ab41b52b77c84.jpg",
                "http://musicdata.baidu.com/data2/pic/440e008f592070337624d1fa7d6d7ed2/562788619/562788619.jpg",
                "http://musicdata.baidu.com/data2/pic/0cec69ddc1a475b28204251a7b929045/562368742/562368742.jpg",
                "http://musicdata.baidu.com/data2/pic/d740e22fef19fc521dafc2738340946e/562689379/562689379.jpg",
                "http://musicdata.baidu.com/data2/pic/59ff17415f203dc7730cdb9f30cf6735/562654086/562654086.jpg",
                "http://musicdata.baidu.com/data2/pic/a7a3ad7932cdf665ec68c8b7e18323bd/562306970/562306970.jpg",
                "http://musicdata.baidu.com/data2/pic/fe5e646ff49c74df1ca70e79f0f16124/562193230/562193230.jpg",
                "http://musicdata.baidu.com/data2/pic/2e619defac444b3a503bf9ce37b4dd5d/562209099/562209099.jpg",
                "http://musicdata.baidu.com/data2/pic/1fa54c07b163aae7c2c9234346da6b00/561734711/561734711.jpg",
                "http://musicdata.baidu.com/data2/pic/dcdac9c3c0156c89e63e2a44c40de4ee/561738955/561738955.jpg",
                "http://musicdata.baidu.com/data2/pic/9774dad2e457f339ee393c5c6d275cb1/560633226/560633226.jpg",
                "http://bpic.588ku.com/back_pic/00/04/27/49/34e896fb7cca1c93f170bfafd998913d.jpg",
                "http://bpic.588ku.com/back_pic/00/04/27/49/04860b670174ab717fe3a53a70f6fdb6.jpg",
                "http://bpic.588ku.com/back_pic/00/01/64/485607358ac0f4d.jpg",
                "http://bpic.588ku.com/back_pic/00/04/20/33/98f45729233f27a178f3b9bf7a401971.jpg",
                "http://bpic.588ku.com/back_pic/00/04/27/49/0b4f96c33bc865922473c0994a018ec6.jpg"
        };

        banner.setImages(Arrays.asList(images)).setImageLoader(new GlideImageLoader()).start();

        tabName = getArguments().getString(INTENT_STRING_TABNAME);
        position = getArguments().getInt(INTENT_INT_POSITION);
        //textView.setText(position+"加载中....");
        if(position == 1){
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        }else{
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(layoutManager);
        }

        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        adapter = new CommonAdapter<GankBean>(activity,R.layout.item_sub,gankBeans) {
            @Override
            protected void convert(ViewHolder holder, GankBean gankBean, int position) {
                if(type.equals("福利")){
                    holder.getView(R.id.iv_all_welfare).setVisibility(View.VISIBLE);
                    holder.getView(R.id.ll_welfare_other).setVisibility(View.GONE);
                    holder.getView(R.id.rl_welfare_other).setVisibility(View.GONE);
                    Glide.with(activity).load(gankBean.getUrl()).into((ImageView)holder.getView(R.id.iv_all_welfare));
                } else {
                    holder.getView(R.id.iv_all_welfare).setVisibility(View.GONE);
                }

                holder.setText(R.id.tv_content_type,gankBean.getType());
                holder.setText(R.id.tv_des,gankBean.getDesc());
                holder.setText(R.id.tv_who,gankBean.getWho());
                holder.setText(R.id.tv_time,gankBean.getCreatedAt());
            }
        };
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                initData();
            }

            @Override
            public void onLoadMore() {
                pageIndex++;
                initData();
            }
        });

    }

    @Override
    protected void onRefresh() {
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }
    
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sub;
    }

    @Override
    protected void initData() {
        presenter = new SubPresenter(getActivity(),this);
        switch (position){
            case 0:
                presenter.getEveryDay("2017","10","20");
                banner.setVisibility(View.VISIBLE);
                break;
            case 1:
                type = "福利";
                presenter.getContent(type,pageIndex,pageSize);
                break;
            case 2:
                type = "Android";
                presenter.getContent(type,pageIndex,pageSize);
                break;
            case 3:
                type = "iOS";
                presenter.getContent(type,pageIndex,pageSize);
                break;
        }
        hideLoading();
        textView.setText(tabName + " " + position + " 界面加载完毕");
        Logger.d("position="+String.valueOf(position));
    }

    @Override
    protected void onError() {
        if(pageIndex > 1){
            pageIndex--;
        }

        Logger.d("onError  pageIndex="+pageIndex);
    }

    @Override
    public void onContentSuccess(ArrayList<GankBean> list) {
        if(list != null){
            if (pageIndex == 1) {
                //第一页
                this.gankBeans.clear();
                if (list.size() < pageSize) {
                    //如果返回结果小于页面显示数量，无需加load
                    if (list.size() < 1) {
                        //tv_empty.setVisibility(View.VISIBLE);
                    } else {
                        //tv_empty.setVisibility(View.GONE);
                    }
                }
                xRecyclerView.refreshComplete();
            }else{
                //非第一页
                if (list.size() < pageSize) {
                    //如果返回结果小于页面显示数量，无需加load
                    xRecyclerView.setNoMore(true);
                }else {
                    xRecyclerView.loadMoreComplete();
                }
            }
            this.gankBeans.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onEveryDaySuccess(ArrayList<GankBean> list) {
        this.gankBeans.clear();
        this.gankBeans.addAll(list);
        adapter.notifyDataSetChanged();
        xRecyclerView.setNoMore(true);
    }
}
