package com.singal.zy.optionitemview.activity.loadsir;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.loadsir.target.FragmentA;
import com.singal.zy.optionitemview.activity.loadsir.target.FragmentB;

import java.util.ArrayList;
import java.util.List;

public class MultiFragmentWithViewPagerActivity extends AppCompatActivity {
    private List<Fragment> fragments=new ArrayList<>();
    private String[]tabTitles={"Fragment A","Fragment B"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_fragment_with_view_pager);

        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tablayout = (TabLayout) findViewById(R.id.tablayout);

        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        viewpager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

}
