package com.firelotus.meteoritelibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by firelotus on 2017/10/14.
 * 懒加载,不受viewPager.setOffscreenPageLimit();个数限制
 */

public abstract class BaseFragment3 extends Fragment implements IBaseView {

    protected BaseActivity3 activity;
    protected String TAG;

    private View convertView;
    private SparseArray<View> mViews;

    //测试用变量
    private boolean isInit = false;
    private boolean isLoad = false;
    private boolean isVisible = false;
    private Unbinder mUnbinder;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决软键盘弹出界面变型问题
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        activity = (BaseActivity3)getActivity();
        TAG = getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Meteorite","onCreateView   " + this.getClass().getSimpleName());
        convertView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this,convertView);
        mViews = new SparseArray<>();
        initView();
        isInit = true;
        isCanLoadData();
        return convertView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Meteorite","onViewCreated   " + this.getClass().getSimpleName());
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

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract void initView();

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
    public void hideProgress() {
        Log.d("Meteorite","Fragment hideProgress");
        activity.hideProgress();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showTip(String message) {
        activity.showTip(message);
    }

    @Override
    public void showError() {

    }
}
