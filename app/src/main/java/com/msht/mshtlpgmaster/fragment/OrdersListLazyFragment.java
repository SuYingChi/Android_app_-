package com.msht.mshtlpgmaster.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.msht.mshtlpgmaster.Bean.OrdersListBeanV2;
import com.msht.mshtlpgmaster.Present.IOrdersListPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.activity.BackBottleOrdersCancleActivity;
import com.msht.mshtlpgmaster.activity.BackBottleOrdersDetailActivity;
import com.msht.mshtlpgmaster.activity.BackBottleOrdersFinishActivity;
import com.msht.mshtlpgmaster.activity.SendBottleOrdersDetailActivity;
import com.msht.mshtlpgmaster.activity.SendBottleOrdersDetailCancleActivity;
import com.msht.mshtlpgmaster.activity.SendBottleOrdersDetailFinishActivity;
import com.msht.mshtlpgmaster.activity.SendBottleOrdersDetailPayActivity;
import com.msht.mshtlpgmaster.adapter.OrdersListRclAdapter;
import com.msht.mshtlpgmaster.application.LPGApplication;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.util.PermissionUtils;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;
import com.msht.mshtlpgmaster.viewInterface.IOrderView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrdersListLazyFragment extends BaseLazyFragment implements IOrderView, OnRefreshListener, OrdersListRclAdapter.OnOrdersFragmentRclClicklistener, PermissionUtils.PermissionRequestFinishListener, OnLoadMoreListener {
    private IOrdersListPresenter iOrdersListPresenter;
    @BindView(R.id.rcl_home_orders_fragment)
    RecyclerView rclHome;
    @BindView(R.id.refreshLayout_home_orders_fragment)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tablayout_home_orders_fragment)
    TabLayout tabLayout;
    @BindView(R.id.deliver_order)
    Button btnTab0;
    @BindView(R.id.recede_order)
    Button btnTab1;


    private List<OrdersListBeanV2.DataBean.ListBean> list = new ArrayList<OrdersListBeanV2.DataBean.ListBean>();
    private OrdersListRclAdapter adapter;
    private int ordersType;
    private int ordersStatus;
    //private AutoLoadMoreAdapter autoLoadMoreAdapter;
    private int page;
    private final String[] tabTitles = {"全部", "待验瓶", "待付款", "已完成"};
    private final String[] tabTitlesReturnBottle = {"全部", "待验瓶", "已完成", "已取消"};
    private String mobile;


    @Override
    protected int setLayoutId() {
        return R.layout.orders_lazy_fragment;
    }

    @Override
    protected void initView() {

        iOrdersListPresenter = new IOrdersListPresenter(OrdersListLazyFragment.this);
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        initTopTab(SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_TOP_CITEM));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ordersStatus = tab.getPosition();
                SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_ORDERS_SCHEDULE_CITEM, ordersStatus);

                onRefresh(refreshLayout);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.getTabAt(SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_ORDERS_SCHEDULE_CITEM)).select();
        ordersStatus = SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_ORDERS_SCHEDULE_CITEM);
        adapter = new OrdersListRclAdapter(list, getActivity(), this);
        rclHome.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rclHome.setAdapter(adapter);
    }

    private void initTopTab(int item) {
        if (item == 0) {
            ordersType = 1;
            btnTab0.setBackgroundResource(R.drawable.btn_left_corner_bg);
            btnTab1.setBackgroundResource(R.drawable.btn_right_corner_unselect_bg);

            btnTab0.setTextColor(ContextCompat.getColor(getContext(), R.color.msb_color));
            btnTab1.setTextColor(ContextCompat.getColor(getContext(), R.color.btn_right_corner_unselect_bg));

            for (String title : tabTitles) {
                tabLayout.addTab(tabLayout.newTab().setText(title));
            }
        }
        if (item == 1) {
            ordersType = 0;
            btnTab0.setBackgroundResource(R.drawable.btn_left_corner_unselect_bg);
            btnTab1.setBackgroundResource(R.drawable.btn_right_corner_bg);

            btnTab0.setTextColor(ContextCompat.getColor(getContext(), R.color.btn_right_corner_unselect_bg));
            btnTab1.setTextColor(ContextCompat.getColor(getContext(), R.color.msb_color));
            for (String title : tabTitlesReturnBottle) {
                tabLayout.addTab(tabLayout.newTab().setText(title));
            }

        }
        SharePreferenceUtil.getInstance().setOrderType(ordersType + "");

    }

    @OnClick({R.id.deliver_order, R.id.recede_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.deliver_order:
                if (ordersType != 1) {
                    initTopTab(0);
                    ordersType = 1;
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_FRAGMENT_TOP_TAB_ITEM, 0);
                    tabLayout.removeAllTabs();
                    for (String title : tabTitles) {
                        tabLayout.addTab(tabLayout.newTab().setText(title));
                    }

                }
                break;
            case R.id.recede_order:
                if (ordersType != 0) {
                    initTopTab(1);
                    ordersType = 0;
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_FRAGMENT_TOP_TAB_ITEM, 1);
                    tabLayout.removeAllTabs();
                    for (String title : tabTitlesReturnBottle) {
                        tabLayout.addTab(tabLayout.newTab().setText(title));
                    }

                }
                break;

            default:
                break;
        }
        onRefresh(refreshLayout);
    }

    @Override
    protected void initData() {
        page = 1;
        iOrdersListPresenter.getOrders();
    }


    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getOrdersStatus() {
        return ordersStatus;
    }

    @Override
    public void onGetOrdersSuccess(OrdersListBeanV2 ordersBean) {
        refreshLayout.finishRefresh();
        if (page == 1) {
            list.clear();
        }
        refreshLayout.finishLoadMore();
        list.addAll(ordersBean.getData().getList());
        if (page == ordersBean.getData().getPage().getPages()) {
            refreshLayout.setEnableAutoLoadMore(false);
        } else {
            refreshLayout.setEnableAutoLoadMore(true);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        iOrdersListPresenter.getOrders();
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        refreshLayout.finishRefresh();
        //autoLoadMoreAdapter.finishLoading();
        refreshLayout.finishLoadMore();
        switch (s) {
            case "未知的订单状态":
                break;

            default:

                break;
        }

    }


    @Override
    public void onClickCallButton(String mobile) {
        this.mobile = mobile;
        PopUtil.showComfirmDialog(getContext(), "拨打电话", "请确认是否要拨打电话" + mobile, "取消", "确认", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.requestPermissions(getContext(), OrdersListLazyFragment.this, Permission.CALL_PHONE);
            }
        }, true);
    }


    @Override
    public void onClckOrderButton(int orderId, int orderType) {
        if (orderType == 0) {
            Intent intent = new Intent(getActivity(), SendBottleOrdersDetailActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 1) {
            Intent intent = new Intent(getActivity(), SendBottleOrdersDetailPayActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 2) {
            Intent intent = new Intent(getActivity(), SendBottleOrdersDetailFinishActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 3) {
            Intent intent = new Intent(getActivity(), BackBottleOrdersDetailActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 4) {
            Intent intent = new Intent(getActivity(), BackBottleOrdersFinishActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 5) {
            Intent intent = new Intent(getActivity(), BackBottleOrdersCancleActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        }else if (orderType == 6) {
            Intent intent = new Intent(getActivity(), SendBottleOrdersDetailCancleActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        }
    }


    @Override
    public void onClickItem(int orderId, int orderType) {
        if (orderType == 0) {
            Intent intent = new Intent(getActivity(), SendBottleOrdersDetailActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 1) {
            Intent intent = new Intent(getActivity(), SendBottleOrdersDetailPayActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 2) {
            Intent intent = new Intent(getActivity(), SendBottleOrdersDetailFinishActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 3) {
            Intent intent = new Intent(getActivity(), BackBottleOrdersDetailActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 4) {
            Intent intent = new Intent(getActivity(), BackBottleOrdersFinishActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 5) {
            Intent intent = new Intent(getActivity(), BackBottleOrdersCancleActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        } else if (orderType == 6) {
            Intent intent = new Intent(getActivity(), SendBottleOrdersDetailCancleActivity.class);
            intent.putExtra(Constants.ORDER_ID, orderId + "");
            startActivity(intent);
        }
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

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        Log.d("suyingchi", "onLoadMore: page = " + page);
        iOrdersListPresenter.getOrders();
    }
}
