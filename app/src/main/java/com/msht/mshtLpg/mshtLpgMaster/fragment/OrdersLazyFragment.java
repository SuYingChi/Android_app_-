package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import com.msht.mshtLpg.mshtLpgMaster.adapter.RclHomeAdapter;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrdersLazyFragment extends BaseLazyFragment implements IOrderView, OnRefreshListener {
    private IOrdersPresenter iOrdersPresenter;
    @BindView(R.id.rcl_home)
    RecyclerView rclHome;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tablayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.deliver_order)
    private Button btnTab0;
    @BindView(R.id.recede_order)
    private Button btnTab1;

    private List<OrdersBean> list;
    private RclHomeAdapter adapter;
    private int ordersType;
    private int deliveryPage0;
    private int ordersScheduleType;
    private int retreatOrderPage0;
    private int deliveryPage1;
    private int deliveryPage2;
    private int deliveryPage3;
    private int retreatOrderPage1;
    private int retreatOrderPage2;
    private int retreatOrderPage3;
    private AutoLoadMoreAdapter autoLoadMoreAdapter;
    private int page;




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
        initTopTab(Integer.valueOf(SharePreferenceUtil.getLoginSpStringValue(Constants.HOME_TOP_CITEM)));
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                ordersScheduleType = position;
                SharePreferenceUtil.setLoginSpStringValue(Constants.HOME_ORDERS_CITEM,ordersScheduleType+"");
                initPage();
                iOrdersPresenter.getOrders();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        tabLayout.setCurrentTab(Integer.valueOf(SharePreferenceUtil.getLoginSpStringValue(Constants.HOME_ORDERS_CITEM)));
        ordersScheduleType = Integer.valueOf(SharePreferenceUtil.getLoginSpStringValue(Constants.HOME_ORDERS_CITEM));

    }

    private void initTopTab(Integer item) {
        ordersType= item;
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
                    SharePreferenceUtil.setLoginSpStringValue("HOME_TOP_CITEM", "0");

                }
                break;
            case R.id.recede_order:
                if (ordersType != 1) {
                    initTopTab(1);
                    ordersType = 1;
                    SharePreferenceUtil.setLoginSpStringValue("HOME_TOP_CITEM", "1");

                }
                break;

            default:
                break;
        }
        initPage();
        iOrdersPresenter.getOrders();
    }
    @Override
    protected void initData() {
        initPage();
        iOrdersPresenter.getOrders();
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
    public int getOrdersScheduleType() {
        return ordersScheduleType;
    }

    @Override
    public void onGetOrdersSuccess(OrdersBean s) {
        refreshLayout.finishRefresh(1000);
        if (adapter == null) {
            rclHome.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new RclHomeAdapter(list, getActivity());
            autoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
            autoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
                @Override
                public void onRetry() {

                }

                @Override
                public void onLoadMore() {
                    onLoadMoreInitPage();
                    iOrdersPresenter.getOrders();
                }
            });
            rclHome.setAdapter(autoLoadMoreAdapter);
        }


        if (page == 1) {
            list.clear();
        } else {
            autoLoadMoreAdapter.finishLoading();
        }
     /*   list.addAll(s.getData().getList());
        if (s.getData().getPaging().isIs_end()) {
            autoLoadMoreAdapter.disable();
        }*/
        autoLoadMoreAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page=1;
        iOrdersPresenter.getOrders();
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        refreshLayout.finishRefresh();
    }

  private void onLoadMoreInitPage(){
       switch (ordersType){
           case 0:
               switch (ordersScheduleType){
                   case 0:
                       deliveryPage0++;
                       page=deliveryPage0;
                       break;
                   case 1:
                       deliveryPage1++;
                       page=deliveryPage1;
                       break;
                   case 2:
                       deliveryPage2++;
                       page=deliveryPage2;
                       break;
                   case 3:
                       deliveryPage3++;
                       page=deliveryPage3;
                       break;
                   default:
                       break;
               }
               break;
           case 1:
               switch (ordersScheduleType){
                   case 0:
                       retreatOrderPage0++;
                       page=retreatOrderPage0;
                       break;
                   case 1:
                       retreatOrderPage1++;
                       page=retreatOrderPage1;
                       break;
                   case 2:
                       retreatOrderPage2++;
                       page=retreatOrderPage2;
                       break;
                   case 3:
                       retreatOrderPage3++;
                       page=retreatOrderPage3;
                       break;
                   default:
                       break;
               }
               break;
           default:
               break;
       }
    }

    private void initPage() {
       deliveryPage0 = 1;
       deliveryPage1 = 1;
       deliveryPage2 = 1;
       deliveryPage3 = 1;
        retreatOrderPage0 =1;
        retreatOrderPage1 =1;
        retreatOrderPage2 =1;
        retreatOrderPage3 =1;
        page = 1;
    }
}
