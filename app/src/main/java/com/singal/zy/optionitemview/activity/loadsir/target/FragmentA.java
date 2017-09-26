package com.singal.zy.optionitemview.activity.loadsir.target;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.loadsir.BaseFragment;
import com.singal.zy.optionitemview.activity.loadsir.PostUtil;
import com.singal.zy.optionitemview.activity.loadsir.callback.ErrorCallback;
import com.singal.zy.optionitemview.activity.loadsir.callback.LoadingCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public  class FragmentA extends BaseFragment {

    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_;
    }

    @Override
    protected void loadNet() {
        // do net here...
        // call back
        PostUtil.postCallbackDelayed(mBaseLoadService, ErrorCallback.class);
    }

    @Override
    protected void onNetReload(View v) {
        Toast.makeText(getContext(),"reload in Fragment A",Toast.LENGTH_SHORT).show();
        mBaseLoadService.showCallback(LoadingCallback.class);
        //do retry logic...

        //callback
        PostUtil.postSuccessDelayed(mBaseLoadService);
    }
}
