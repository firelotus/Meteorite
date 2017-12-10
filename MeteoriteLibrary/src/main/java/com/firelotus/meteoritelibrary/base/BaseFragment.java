package com.firelotus.meteoritelibrary.base;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.firelotus.meteoritelibrary.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 内置加载，异常界面
 * Created by firelotus on 2017/10/14.
 * 懒加载,不受viewPager.setOffscreenPageLimit();个数限制
 */

public abstract class BaseFragment extends Fragment implements IBaseView {

    protected BaseActivity activity;
    protected String TAG;
    // fragment是否显示了
    protected boolean mIsVisible = false;
    // 内容布局
    protected RelativeLayout mContainer;
    private View contentView;
    private SparseArray<View> mViews;
    //测试用变量
    private boolean isInit = false;
    private boolean isLoad = false;
    private boolean isVisible = false;
    private Unbinder mUnbinder;
    // 加载中
    private LinearLayout mLlProgressBar;
    // 加载失败
    private LinearLayout mRefresh;
    // 动画
    private AnimationDrawable mAnimationDrawable;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("Meteorite","setUserVisibleHint " + isVisibleToUser + "   " + this.getClass().getSimpleName());
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        isCanLoadData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Meteorite","onAttach" + "   " + this.getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Meteorite","onCreateView   " + this.getClass().getSimpleName());

        //解决软键盘弹出界面变型问题
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        activity = (BaseActivity)getActivity();
        TAG = getClass().getSimpleName();

        View fragment_base = activity.getLayoutInflater().inflate(R.layout.fragment_base, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView = inflater.inflate(getLayoutId(), null, false);
        contentView.setLayoutParams(params);
        mContainer = (RelativeLayout) fragment_base.findViewById(R.id.container);
        mContainer.addView(contentView);
        mUnbinder = ButterKnife.bind(this, contentView);
        return fragment_base;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Meteorite","onViewCreated   " + this.getClass().getSimpleName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViews = new SparseArray<>();

        mLlProgressBar = getView(R.id.ll_progress_bar);
        ImageView img = getView(R.id.img_progress);
        mRefresh = getView(R.id.ll_error_refresh);
        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();

        initView();
        isInit = true;
        isCanLoadData();

        Log.d("Meteorite","onActivityCreated   mAnimationDrawable.isRunning() = "+mAnimationDrawable.isRunning());

        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        // 点击加载失败布局
        mRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        //contentView.setVisibility(View.GONE);
    }

    protected <T extends View> T getView(int id) {
        //此处之前犯了个低级错误，用的getActivity()结果调了一天，动画死活不动……
        return (T) getView().findViewById(id);
    }

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract void initView();

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
        isVisible = false;
        mUnbinder.unbind();
    }

    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int getLayoutId();

    private void isCanLoadData(){
        Log.d("Meteorite","!isInit="+(!isInit)+"    getUserVisibleHint() = "+getUserVisibleHint()+"    "+this.getClass().getSimpleName());
        if(!isInit){
            return;
        }
        if(isVisible){
            initData();
            isLoad = true;
        }
    }

    /**
     * 加载要显示的数据
     */
    protected abstract void initData();

    /**
     * fragment中可以通过这个方法直接找到需要的view，而不需要进行类型强转
     * @param viewId
     * @param <E>
     * @return
     */
    protected <E extends View> E findViewById(int viewId) {
        if (contentView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) contentView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    @Override
    public void showProgress() {
        Log.d("Meteorite","Fragment showProgress");
        activity.showProgress();
    }

    @Override
    public void hideProgress() {
        Log.d("Meteorite","Fragment hideProgress");
        activity.hideProgress();
    }

    /**
     * 显示加载中状态
     */
    @Override
    public void showLoading() {
        if (mLlProgressBar.getVisibility() != View.VISIBLE) {
            mLlProgressBar.setVisibility(View.VISIBLE);
        }

        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (contentView.getVisibility() != View.GONE) {
            contentView.setVisibility(View.GONE);
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
    }

    /**
     * 加载完成的状态
     */
    @Override
    public void hideLoading() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (contentView.getVisibility() != View.VISIBLE) {
            contentView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showTip(String message) {
        activity.showTip(message);
    }

    /**
     * 加载失败点击重新加载的状态
     */
    @Override
    public void showError() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.VISIBLE) {
            mRefresh.setVisibility(View.VISIBLE);
        }
        if (contentView.getVisibility() != View.GONE) {
            contentView.setVisibility(View.GONE);
        }

        onError();
    }

    /**
     * 出错回调处理
     */
    protected void onError(){

    }
}
