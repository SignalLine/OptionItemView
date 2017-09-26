package com.singal.zy.optionitemview.activity.loadsir.target;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singal.zy.library.loadsir.callback.Callback;
import com.singal.zy.library.loadsir.core.LoadService;
import com.singal.zy.library.loadsir.core.LoadSir;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.loadsir.PostUtil;
import com.singal.zy.optionitemview.activity.loadsir.callback.CustomCallback;
import com.singal.zy.optionitemview.activity.loadsir.callback.LoadingCallback;

public class NormalFragment extends Fragment {
    private LoadService loadService;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.fragment_normal, null);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new CustomCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)
                .build();
        loadService = loadSir.register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showCallback(LoadingCallback.class);
                //do retry logic...

                //callback
                PostUtil.postSuccessDelayed(loadService);
            }
        });
        return loadService.getLoadLayout();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PostUtil.postCallbackDelayed(loadService, CustomCallback.class);
    }
}
