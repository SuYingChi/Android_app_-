package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.TransferStorageListBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.UpdateTransferBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.ITransferToStorageListPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.TransferToStorageRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ITransferToStorageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TransferStorageListActivity extends BaseActivity implements ITransferToStorageView, OnRefreshListener, OnLoadMoreListener,TransferToStorageRclAdapter.onTransferToStorageRclAdapterClickListener {

    @BindView(R.id.my_setting_fragment_layout_topbar)
    TopBarView topBarView;
    @BindView(R.id.tablayout_home_orders_fragment)
    TabLayout tabLayout;
    @BindView(R.id.rcl_transfer_to_storage)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String siteId;
    private String state = "2";
    private int pageNum = 1;
    private ITransferToStorageListPresenter iTransferToStoragePresenter;
    private Unbinder unBinder;
    private final String[] titles = {"全部", "待验瓶", "已完成"};
    private TransferToStorageRclAdapter adapter;
    private List<TransferStorageListBean.DataBean> list = new ArrayList<TransferStorageListBean.DataBean>();
    private String id;
    private String fiveCount;
    private String fifteenCount;
    private String fiftyCount;
    private String transferType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_storage);
        unBinder = ButterKnife.bind(this);
        transferType = getIntent().getStringExtra("transferType");
        iTransferToStoragePresenter = new ITransferToStorageListPresenter(this);
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();
                //调拨单状态：0-待验瓶， 1-已完成， 2-全部
                switch (tabPosition) {
                    //全部
                    case 0:
                        state = 2+"";
                        break;
                        //待验瓶
                    case 1:
                        state = 0+"";
                        break;
                        //已完成
                    case 2:
                        state = 1+"";
                        break;
                    default:
                        break;
                }

                onRefresh(refreshLayout);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        tabLayout.getTabAt(0).select();
        adapter = new TransferToStorageRclAdapter(this,this,list,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public String getSiteId() {
        siteId = SharePreferenceUtil.getLoginSpStringValue("siteId");
        return siteId;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getPageNum() {
        return pageNum + "";
    }

    @Override
    public String getPageSize() {
        return "2";
    }

    @Override
    public void onGetListSuccess(TransferStorageListBean transferStorageListBean) {
        refreshLayout.finishRefresh();
        if (pageNum == 1) {
            list.clear();
        }
        refreshLayout.finishLoadMore();
        list.addAll(transferStorageListBean.getData());
    /*    if (pageNum == transferStorageListBean.getData().getPageSize()) {
            refreshLayout.setEnableAutoLoadMore(false);
        } else {
            refreshLayout.setEnableAutoLoadMore(true);
        }*/
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFiveCount() {
        return fiveCount;
    }

    @Override
    public String getFifteenCount() {
        return fifteenCount;
    }

    @Override
    public String getFifthCount() {
        return fiftyCount;
    }

    @Override
    public void onUpdateTransferSuccess(UpdateTransferBean updateTransferBean) {
        pageNum = 1;
        iTransferToStoragePresenter.getTransferOrdersList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBinder.unbind();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNum++;
        iTransferToStoragePresenter.getTransferOrdersList();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageNum = 1;
        iTransferToStoragePresenter.getTransferOrdersList();
    }

    @Override
    public void onClickScanCodeBtn(int itemPosition, String orderId) {
        id = list.get(itemPosition).getId()+"";
        fiveCount = list.get(itemPosition).getFiveCount()+"";
        fifteenCount = list.get(itemPosition).getFiveCount()+"";
        fiftyCount = list.get(itemPosition).getFiveCount()+"";
        Intent intent = new Intent(this,ScanTransferStorageActivity.class);
        intent.putExtra(Constants.ORDER_ID,id);
        intent.putExtra(Constants.ORDER_FIVE_NUM,fiveCount);
        intent.putExtra(Constants.ORDER_FIFTEEN_NUM,fifteenCount);
        intent.putExtra(Constants.ORDER_FIFTY_NUM,fiftyCount);
        intent.putExtra("TransferType",list.get(itemPosition).getTransformType()+"");
        startActivity(intent);
    }

    @Override
    public void onClckModifyBtn(int position, int five, int fifteen, int itemPosition, String orderId) {
        id = list.get(itemPosition).getId()+"";
        fiveCount = list.get(itemPosition).getFiveCount()+"";
        fifteenCount = list.get(itemPosition).getFiveCount()+"";
        fiftyCount = list.get(itemPosition).getFiveCount()+"";
        iTransferToStoragePresenter.updateTransfer();
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }
}
