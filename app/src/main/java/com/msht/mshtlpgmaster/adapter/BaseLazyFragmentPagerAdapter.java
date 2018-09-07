package com.msht.mshtlpgmaster.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.msht.mshtlpgmaster.fragment.BaseLazyFragment;

import java.util.List;

public class BaseLazyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<BaseLazyFragment> list_fragment;

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
