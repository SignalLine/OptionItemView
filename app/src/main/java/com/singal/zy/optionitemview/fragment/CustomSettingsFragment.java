package com.singal.zy.optionitemview.fragment;

import android.os.Bundle;

import com.singal.zy.normal_libs.web.WebSettings;
import com.singal.zy.optionitemview.web.CustomSettings;



public class CustomSettingsFragment extends AgentWebFragment {

    public static AgentWebFragment getInstance(Bundle bundle) {

        CustomSettingsFragment mCustomSettingsFragment = new CustomSettingsFragment();
        if (bundle != null)
            mCustomSettingsFragment.setArguments(bundle);

        return mCustomSettingsFragment;

    }


    @Override
    public WebSettings getSettings() {

        return new CustomSettings();
    }
}
