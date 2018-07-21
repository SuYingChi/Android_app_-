package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.HomeActivityFragmentPagerAdapter;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.SlidableViewPager;
import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseLazyFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.DispatchOrdersLazyFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MySettingFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.OrdersListLazyFragment;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {


     long exitTime;
     OrdersListLazyFragment f0;
     DispatchOrdersLazyFragment f1;
     MySettingFragment f2;
     List<BaseLazyFragment> list_fragment = new ArrayList<BaseLazyFragment>();
     @BindView(R.id.vp)
     SlidableViewPager vp;
    @BindView(R.id.iv_tab0)
     ImageView ivTab0;
    @BindView(R.id.iv_tab1)
     ImageView ivTab1;
    @BindView(R.id.iv_tab2)
     ImageView ivTab2;
    @BindView(R.id.tv_tab0)
     TextView tvTab0;
    @BindView(R.id.tv_tab1)
     TextView tvTab1;
    @BindView(R.id.tv_tab2)
     TextView tvTab2;
    @BindView(R.id.ll_tab0)
     LinearLayout llTab0;
    @BindView(R.id.ll_tab1)
     LinearLayout llTab1;
    @BindView(R.id.ll_tab2)
     LinearLayout llTab2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        f0 = new OrdersListLazyFragment();
        f1 = new DispatchOrdersLazyFragment();
        f2 = new MySettingFragment();
        list_fragment.add(f0);
        list_fragment.add(f1);
        list_fragment.add(f2);


        vp.setAdapter(new HomeActivityFragmentPagerAdapter(getSupportFragmentManager(), list_fragment));
        vp.setCurrentItem(SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM));
        initBottom(SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM));
        vp.setOffscreenPageLimit(3);

        vp.setScanScroll(false);
        vp.setPageTransformer(true, null);

    }

    private void initBottom(Integer item) {
        if (item == 0) {
            ivTab0.setImageResource(R.drawable.order);
            ivTab1.setImageResource(R.drawable.dispatch_order_unselected);
            ivTab2.setImageResource(R.drawable.my_setting_unselected);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));

        }
        if (item == 1) {
            ivTab0.setImageResource(R.drawable.order_unselected);
            ivTab1.setImageResource(R.drawable.dispatch_order);
            ivTab2.setImageResource(R.drawable.my_setting_unselected);


            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));

        }
        if (item == 2) {
            ivTab0.setImageResource(R.drawable.order_unselected);
            ivTab1.setImageResource(R.drawable.dispatch_order_unselected);
            ivTab2.setImageResource(R.drawable.my_setting);


            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));

        }

    }
    @OnClick({R.id.ll_tab0, R.id.ll_tab1, R.id.ll_tab2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab0:
                if (vp.getCurrentItem() != 0) {
                    initBottom(0);
                    vp.setCurrentItem(0, false);
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM, 0);
                }
                break;
            case R.id.ll_tab1:
                if (vp.getCurrentItem() != 1) {
                    initBottom(1);
                    vp.setCurrentItem(1, false);
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM, 1);
                }
                break;
            case R.id.ll_tab2:
                if (vp.getCurrentItem() != 2) {
                    vp.setCurrentItem(2, false);
                    initBottom(2);
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM, 2);
                }
                break;
           default:
               break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
               PopUtil.toastInBottom(R.string.exit_App);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        list_fragment.clear();
        f0 = null;
        f1 = null;
        f2 = null;
        super.onDestroy();
    }
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    //打开别的页面后再跳回主页时回调
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
