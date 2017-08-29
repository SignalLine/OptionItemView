package com.singal.zy.optionitemview.activity;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.ganged.CheckListener;
import com.singal.zy.optionitemview.activity.ganged.RvListener;
import com.singal.zy.optionitemview.activity.ganged.SortAdapter;
import com.singal.zy.optionitemview.fragment.SortDetailFragment;

import java.util.Arrays;
import java.util.List;

public class GangedRecycleActivity extends AppCompatActivity implements CheckListener{

    private RecyclerView rvSort;
    private SortAdapter mSortAdapter;
    private SortDetailFragment mSortDetailFragment;
    private Context mContext;
    public static boolean left;
    public static int finalNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganged_recycle);
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initData();
    }

    private void initData() {
        String[] classify = getResources().getStringArray(R.array.pill);
        List<String> list = Arrays.asList(classify);
        mSortAdapter = new SortAdapter(mContext, list, new RvListener() {
            @Override
            public void onItemClick(int id, int position) {
                if (mSortDetailFragment != null) {
                    setChecked(position, true);
                }
            }
        });
        rvSort.setAdapter(mSortAdapter);
        createFragment();
    }

    public void createFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mSortDetailFragment = new SortDetailFragment();
        mSortDetailFragment.setListener(this);
        fragmentTransaction.add(R.id.lin_fragment, mSortDetailFragment);
        fragmentTransaction.commit();
    }

    private void setChecked(int position, boolean isLeft) {
        finalNumber = position;
        left = isLeft;
        Log.d("boolean---->", String.valueOf(left));
        mSortAdapter.setCheckedPosition(position);
        if (isLeft) {
            mSortDetailFragment.setData(position * 10 + position);
        }

    }


    private void initView() {
        rvSort = (RecyclerView) findViewById(R.id.rv_sort);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvSort.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        rvSort.addItemDecoration(decoration);

    }

    @Override
    public void check(int position, boolean isChecked) {
        setChecked(position, isChecked);

    }
}
