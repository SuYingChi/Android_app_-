package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseLazyFragment;

import java.util.ArrayList;

public class HomeActivityFragmentPagerAdapter extends PagerAdapter {
    public HomeActivityFragmentPagerAdapter(FragmentManager supportFragmentManager, ArrayList<BaseLazyFragment> list_fragment) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
