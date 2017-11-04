package com.firelotus.meteorite.ui.content;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firelotus.meteorite.R;
import com.firelotus.meteorite.ui.bean.EveryDayBean;
import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.BaseFragment;
import com.firelotus.meteoritelibrary.tools.GlideImageLoader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by firelotus on 2017/10/15.
 */

public class SubFragment extends BaseFragment implements SubContract.View{
    @BindView(R.id.xRecyclerView) XRecyclerView xRecyclerView;
    @BindView(R.id.banner) Banner banner;
    private SubContract.Presenter presenter;
    public static String INDEX = "position";
    private int pos = 0;
    private int pageIndex = 1;
    private int pageSize = 10;
    private String type = "Android";//福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all

    public static final String INTENT_STRING_TABNAME = "intent_String_tabName";
    public static final String INTENT_INT_POSITION = "intent_int_position";
    private String tabName;
    private int position;
    private TextView textView;
    private ProgressBar progressBar;
    private String[] images;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sub;
    }

    @Override
    protected void initView() {
        //showContentView();
        xRecyclerView = findViewById(R.id.xRecyclerView);
        pos = getArguments().getInt(INDEX);
        textView = findViewById(R.id.fragment_mainTab_item_textView);
        images= new String[] {
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1506510233d297d6a9ad1f5d728c78ab989c0e73db.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_15084703014624b835b7768a099213b2e4d4d1617b.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_15083977422c9bfc61c881a4fcc9142f6bf70aa4e1.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_150847891021bcf6ec15fc6632cd1fd4593d120459.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1508468206c5311a2eacd8d6649da69bf67ae0f979.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_150843285441a8ff2395f1bb3eaf87d12aa259caf4.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1508494984de7b6a8b67bc18a41ac9c9afb0919b67.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_508a2a70e59111e96bf77cd242d634c7.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_f8008d03b3a79ebcf5102ccac21263aa.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_e4edf96bc36f10e1c00e26466e8725c2.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_c4e765e217bf56a36542b20c78024757.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_610649c240443298854e651098537460.jpg",
                "http://business.cdn.qianqian.com/qianqian/pic/bos_client_ec9c0fe6a37f0c238c5ab41b52b77c84.jpg",
                "http://bpic.588ku.com/back_pic/00/04/27/49/34e896fb7cca1c93f170bfafd998913d.jpg",
                "http://bpic.588ku.com/back_pic/00/04/27/49/04860b670174ab717fe3a53a70f6fdb6.jpg",
                "http://bpic.588ku.com/back_pic/00/01/64/485607358ac0f4d.jpg",
                "http://bpic.588ku.com/back_pic/00/04/20/33/98f45729233f27a178f3b9bf7a401971.jpg",
                "http://bpic.588ku.com/back_pic/00/04/27/49/0b4f96c33bc865922473c0994a018ec6.jpg"
        };

        banner.setImages(Arrays.asList(images)).setImageLoader(new GlideImageLoader()).start();

        tabName = getArguments().getString(INTENT_STRING_TABNAME);
        position = getArguments().getInt(INTENT_INT_POSITION);
        textView.setText(position+"加载中....");
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

        textView.setText(tabName + " " + position + " 界面加载完毕");
        Logger.d("position="+String.valueOf(position));
    }

    @Override
    public void setPresenter(SubContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onError() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onContentSuccess(ArrayList<GankBean> list) {

    }

    @Override
    public void onEveryDaySuccess(EveryDayBean everyDayBean) {

    }
}
