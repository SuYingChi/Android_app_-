package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrdersPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.LoginActivity;
import com.msht.mshtLpg.mshtLpgMaster.activity.OrdersDetailActivity;
import com.msht.mshtLpg.mshtLpgMaster.activity.SplashActivity;
import com.msht.mshtLpg.mshtLpgMaster.adapter.OrdersFragmentRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrdersLazyFragment extends BaseLazyFragment implements IOrderView, OnRefreshListener, OrdersFragmentRclAdapter.OnOrdersFragmentRclClicklistener, PermissionUtils.PermissionRequestFinishListener {
    private IOrdersPresenter iOrdersPresenter;
    @BindView(R.id.rcl_home_orders_fragment)
    RecyclerView rclHome;
    @BindView(R.id.refreshLayout_home_orders_fragment)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tablayout_home_orders_fragment)
    SlidingTabLayout tabLayout;
    @BindView(R.id.deliver_order)
    Button btnTab0;
    @BindView(R.id.recede_order)
    Button btnTab1;


    private List<OrdersBean.DataBean.ListBean> list = new ArrayList<OrdersBean.DataBean.ListBean>();
    private OrdersFragmentRclAdapter adapter;
    private int ordersType;
    private int ordersStatus;
    private AutoLoadMoreAdapter autoLoadMoreAdapter;
    private int page;
    private final String[] tabTitles = {"全部", "待验瓶", "待付款", "已完成"};
    private String mobile;


    @Override
    protected int setLayoutId() {
        return R.layout.orders_lazy_fragment;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this, mRootView);
        iOrdersPresenter = new IOrdersPresenter(OrdersLazyFragment.this);
        rclHome.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);
        initTopTab(SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_TOP_CITEM));
        for (String title : tabTitles) {
            tabLayout.addNewTab(title);
        }
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                ordersStatus = position;
                SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_ORDERS_SCHEDULE_CITEM, ordersStatus);

                onRefresh(refreshLayout);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        tabLayout.setCurrentTab(SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_ORDERS_SCHEDULE_CITEM));
        ordersStatus = SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_ORDERS_SCHEDULE_CITEM);
    }

    private void initTopTab(int item) {
        ordersType = item;
        if (item == 0) {
            btnTab0.setBackgroundResource(R.drawable.btn_left_corner_bg);
            btnTab1.setBackgroundResource(R.drawable.btn_right_corner_unselect_bg);

            btnTab0.setTextColor(ContextCompat.getColor(getContext(), R.color.bot_gray));
            btnTab1.setTextColor(ContextCompat.getColor(getContext(), R.color.text_enable_gray));


        }
        if (item == 1) {
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
                if (ordersType != 0) {
                    initTopTab(0);
                    ordersType = 0;
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_FRAGMENT_TOP_TAB_ITEM, 0);

                }
                break;
            case R.id.recede_order:
                if (ordersType != 1) {
                    initTopTab(1);
                    ordersType = 1;
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_FRAGMENT_TOP_TAB_ITEM, 1);

                }
                break;

            default:
                break;
        }
        onRefresh(refreshLayout);
    }

    @Override
    protected void initData() {
        adapter = new OrdersFragmentRclAdapter(list, getActivity(), this);
        autoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
        autoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                page++;
                iOrdersPresenter.getOrders();
            }
        });
        rclHome.setAdapter(autoLoadMoreAdapter);
        ((DefaultItemAnimator) rclHome.getItemAnimator()).setSupportsChangeAnimations(false);

        onRefresh(refreshLayout);
    }


    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getOrdersType() {
        return ordersType;
    }

    @Override
    public int getOrdersStatus() {
        return ordersStatus;
    }

    @Override
    public void onGetOrdersSuccess(OrdersBean ordersBean) {
        refreshLayout.finishRefresh(1000);
        if (page == 1) {
            list.clear();
        } else {
            autoLoadMoreAdapter.finishLoading();
        }

        if (page == ordersBean.getData().getPage().getPages()) {
            autoLoadMoreAdapter.disable();
        } else {
            autoLoadMoreAdapter.enable();
        }
        list.addAll(ordersBean.getData().getList());
        autoLoadMoreAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        iOrdersPresenter.getOrders();
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        refreshLayout.finishRefresh();
        autoLoadMoreAdapter.finishLoading();
        switch (s) {
            case "未登录":
                Intent goLogin = new Intent(this.getActivity(), LoginActivity.class);
                startActivity(goLogin);
                break;
            case "未知的订单状态":
                break;

            default:

                break;
        }

    }


    @Override
    public void onClickCallButton(String mobile) {
        this.mobile = mobile;
        PermissionUtils.requestPermissions(getContext(), this, Permission.CALL_PHONE);
    }

    @Override
    public void onClckOrderButton(int orderId) {
        Intent intent = new Intent(getActivity(), OrdersDetailActivity.class);
        intent.putExtra("orderId", orderId);
        startActivity(intent);
    }

    @Override
    public void onClickItem() {

    }

    @Override
    public void onBackFromSettingPage() {

    }

    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        PopUtil.toastInBottom("请允许LPG拨打电话");
    }

    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        if (ActivityCompat.checkSelfPermission(LPGApplication.getLPGApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
        startActivity(intent);


    }

}
