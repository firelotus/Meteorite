package com.firelotus.meteorite.ui.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firelotus.meteorite.R;
import com.firelotus.meteoritelibrary.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;

/**
 * Created by firelotus on 2017/10/15.
 */

public class SubFragment extends BaseFragment implements SubContract.View{
    private XRecyclerView xRecyclerView;
    private SubContract.Presenter presenter;
    public static String INDEX = "position";
    private int pos = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub, container, false);
        xRecyclerView = (XRecyclerView)view.findViewById(R.id.xRecyclerView);
        pos = getArguments().getInt(INDEX);
        Logger.d("Meteorite",String.valueOf(pos));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SubPresenter(getActivity(),this);
        presenter.getContent("aaa","bbb");
    }

    @Override
    public void setPresenter(SubContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onError() {

    }

    @Override
    public void success(String message) {
        Logger.d("message==>>"+message);
        showTip(message);
    }
}
