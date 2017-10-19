package com.firelotus.meteorite.ui.content;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.firelotus.meteorite.R;
import com.firelotus.meteorite.ui.bean.GankBean;
import com.firelotus.meteoritelibrary.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;

import java.util.ArrayList;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sub;
    }

    @Override
    protected void initView() {
        xRecyclerView = findViewById(R.id.xRecyclerView);
        pos = getArguments().getInt(INDEX);
        textView = findViewById(R.id.fragment_mainTab_item_textView);
        textView.setText(pos+"加载中....");

        //banner.setImages(images).setImageLoader(new GlideImageLoader()).start();

    }

    @Override
    protected void initData() {
        presenter = new SubPresenter(getActivity(),this);
        presenter.getContent(type,pageIndex,pageSize);

        tabName = getArguments().getString(INTENT_STRING_TABNAME);
        position = getArguments().getInt(INTENT_INT_POSITION);
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
    public void onSuccess(ArrayList<GankBean> list) {

    }
}
