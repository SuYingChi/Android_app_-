package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
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
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.OrdersLazyFragment;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {


    private long exitTime;
    private OrdersLazyFragment f0;
    private DispatchOrdersLazyFragment f1;
    private MyFragment f2;
    private ArrayList<BaseLazyFragment> list_fragment;
    private SlidableViewPager vp;
    @BindView(R.id.iv_tab0)
    private ImageView ivTab0;
    @BindView(R.id.iv_tab1)
    private ImageView ivTab1;
    @BindView(R.id.iv_tab2)
    private ImageView ivTab2;
    @BindView(R.id.tv_tab0)
    private TextView tvTab0;
    @BindView(R.id.tv_tab1)
    private TextView tvTab1;
    @BindView(R.id.tv_tab2)
    private TextView tvTab2;
    @BindView(R.id.ll_tab0)
    private LinearLayout llTab0;
    @BindView(R.id.ll_tab1)
    private LinearLayout llTab1;
    @BindView(R.id.ll_tab2)
    private LinearLayout llTab2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        f0 = new OrdersLazyFragment();
        f1 = new DispatchOrdersLazyFragment();
        f2 = new MyFragment();
        list_fragment.add(f0);
        list_fragment.add(f1);
        list_fragment.add(f2);


        vp.setAdapter(new HomeActivityFragmentPagerAdapter(getSupportFragmentManager(), list_fragment));

        vp.setCurrentItem(Integer.valueOf(SharePreferenceUtil.getLoginSpStringValue("citem")));
        initBottom(Integer.valueOf(SharePreferenceUtil.getLoginSpStringValue("citem")));
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
                    SharePreferenceUtil.setLoginSpStringValue("citem", "0");
                }
                break;
            case R.id.ll_tab1:
                if (vp.getCurrentItem() != 1) {
                    initBottom(1);
                    vp.setCurrentItem(1, false);
                    SharePreferenceUtil.setLoginSpStringValue("citem", "1");
                }
                break;
            case R.id.ll_tab2:
                if (vp.getCurrentItem() != 2) {
                    vp.setCurrentItem(2, false);
                    initBottom(2);
                    SharePreferenceUtil.setLoginSpStringValue("citem", "2");
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
        System.gc();
        super.onDestroy();
//        FloatWindow.destroy();
    }
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
}
