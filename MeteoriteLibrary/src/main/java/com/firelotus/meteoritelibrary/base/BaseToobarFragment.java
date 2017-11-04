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

public abstract class BaseToobarFragment extends Fragment implements IBaseView {

    protected BaseToobarActivity activity;
    protected String TAG;

    private View convertView;
    private SparseArray<View> mViews;

    //测试用变量
    private boolean isInit = false;
    private boolean isLoad = false;
    private boolean isVisible = false;
    private Unbinder mUnbinder;

    // fragment是否显示了
    protected boolean mIsVisible = false;
    // 加载中
    private LinearLayout mLlProgressBar;
    // 加载失败
    private LinearLayout mRefresh;
    // 内容布局
    protected RelativeLayout mContainer;
    // 动画
    private AnimationDrawable mAnimationDrawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Meteorite","onCreateView   " + this.getClass().getSimpleName());
        View fragment_base = inflater.inflate(R.layout.fragment_base, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        convertView = inflater.inflate(getLayoutId(), null, false);
        convertView.setLayoutParams(params);
        mContainer = (RelativeLayout) fragment_base.findViewById(R.id.container);
        mContainer.addView(convertView);

        mUnbinder = ButterKnife.bind(this,convertView);
        mViews = new SparseArray<>();
        initView();
        isInit = true;
        isCanLoadData();
        return fragment_base;
    }

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
        isVisible = false;
        mUnbinder.unbind();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决软键盘弹出界面变型问题
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        activity = (BaseToobarActivity)getActivity();
        TAG = getClass().getSimpleName();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLlProgressBar = getView(R.id.ll_progress_bar);
        ImageView img = getView(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        mRefresh = getView(R.id.ll_error_refresh);
        // 点击加载失败布局
        mRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        convertView.setVisibility(View.GONE);
    }

    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }

    /**
     * 显示加载中状态
     */
    protected void showLoading() {
        if (mLlProgressBar.getVisibility() != View.VISIBLE) {
            mLlProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (convertView.getVisibility() != View.GONE) {
            convertView.setVisibility(View.GONE);
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
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
        if (convertView.getVisibility() != View.VISIBLE) {
            convertView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
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
        if (convertView.getVisibility() != View.GONE) {
            convertView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Meteorite","onViewCreated   " + this.getClass().getSimpleName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Meteorite","onAttach" + "   " + this.getClass().getSimpleName());
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("Meteorite","setUserVisibleHint " + isVisibleToUser + "   " + this.getClass().getSimpleName());
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        isCanLoadData();
    }

    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract void initView();

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
        if (convertView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) convertView.findViewById(viewId);
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
    public void dismissProgress() {
        Log.d("Meteorite","Fragment dismissProgress");
        activity.dismissProgress();
    }

    @Override
    public void showTip(String message) {
        activity.showTip(message);
    }
}
