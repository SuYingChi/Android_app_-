package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

public class BaseLazyFragmentPagerAdapter extends FragmentPagerAdapter {
    private  List<BaseLazyFragment> list_fragment;

    public BaseLazyFragmentPagerAdapter(FragmentManager supportFragmentManager, List<BaseLazyFragment> list_fragment) {
        super(supportFragmentManager);
        this.list_fragment = list_fragment;
    }


    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }
}
