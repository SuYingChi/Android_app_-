package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.BaseLazyFragmentPagerAdapter;
import com.msht.mshtLpg.mshtLpgMaster.customView.SlidableViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DispatchOrdersLazyFragement extends BaseLazyFragment {

    @BindView(R.id.return_btn)
    ImageView returnBtn;
    @BindView(R.id.vp)
    SlidableViewPager vp;
    @BindView(R.id.deliver_order)
    Button btnTab0;
    @BindView(R.id.recede_order)
    Button btnTab1;
    private DispatchSendOrdersLazyFragment f0;
    private DispatchReturnOrdersFragment f1;
    private List<BaseLazyFragment> list = new ArrayList<BaseLazyFragment>();
    private int ordersType;

    @Override
    protected int setLayoutId() {
        return R.layout.dispatch_orders_fragment_v2;
    }

    @Override
    protected void initView() {
        super.initView();
        f0 = new DispatchSendOrdersLazyFragment();
        f1 = new DispatchReturnOrdersFragment();
        list.add(f0);
        list.add(f1);
        initTopTab(0);
        vp.setAdapter(new BaseLazyFragmentPagerAdapter(getChildFragmentManager(), list));
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(2);
        vp.setScanScroll(false);
        vp.setPageTransformer(true, null);
    }

    private void initTopTab(int item) {
        if (item == 0) {
            ordersType = 1;
            btnTab0.setBackgroundResource(R.drawable.btn_left_corner_bg);
            btnTab1.setBackgroundResource(R.drawable.btn_right_corner_unselect_bg);

            btnTab0.setTextColor(ContextCompat.getColor(getContext(), R.color.bot_gray));
            btnTab1.setTextColor(ContextCompat.getColor(getContext(), R.color.text_enable_gray));

        }
        if (item == 1) {
            ordersType = 0;
            btnTab0.setBackgroundResource(R.drawable.btn_left_corner_unselect_bg);
            btnTab1.setBackgroundResource(R.drawable.btn_right_corner_bg);

            btnTab0.setTextColor(ContextCompat.getColor(getContext(), R.color.text_enable_gray));
            btnTab1.setTextColor(ContextCompat.getColor(getContext(), R.color.bot_gray));
        }

    }

    @OnClick({R.id.deliver_order, R.id.recede_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.deliver_order:
                if (ordersType != 1) {
                    initTopTab(0);
                    vp.setCurrentItem(0);
                    ordersType = 1;
                }
                break;
            case R.id.recede_order:
                if (ordersType != 0) {
                    initTopTab(1);
                    vp.setCurrentItem(1);
                    ordersType = 0;
                }
                break;

            default:
                break;
        }
    }
}
