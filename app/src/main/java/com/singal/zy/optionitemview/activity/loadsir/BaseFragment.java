package com.singal.zy.optionitemview.activity.loadsir;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singal.zy.library.loadsir.callback.Callback;
import com.singal.zy.library.loadsir.core.LoadService;
import com.singal.zy.library.loadsir.core.LoadSir;

/**
 */
public abstract class BaseFragment extends Fragment {

    protected LoadService mBaseLoadService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View rootView = View.inflate(getActivity(), onCreateFragmentView(), null);
        mBaseLoadService = LoadSir.getDefault().register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onNetReload(v);
            }
        });
        return mBaseLoadService.getLoadLayout();
    }

    protected abstract int onCreateFragmentView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadNet();
    }

    protected abstract void loadNet();

    protected abstract void onNetReload(View v);
}
