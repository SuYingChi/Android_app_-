package com.msht.mshtlpgmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtlpgmaster.Bean.TransferStorageListBean;
import com.msht.mshtlpgmaster.Bean.UpdateTransferBean;
import com.msht.mshtlpgmaster.Present.ITransferToStorageListPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.adapter.TransferToStorageRclAdapter;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.customView.TopBarView;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;
import com.msht.mshtlpgmaster.viewInterface.ITransferToStorageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TransferStorageListActivity extends BaseActivity implements ITransferToStorageView, OnRefreshListener, OnLoadMoreListener, TransferToStorageRclAdapter.onTransferToStorageRclAdapterClickListener {

    @BindView(R.id.my_setting_fragment_layout_topbar)
    TopBarView topBarView;
    @BindView(R.id.tablayout_home_orders_fragment)
    TabLayout tabLayout;
    @BindView(R.id.rcl_transfer_to_storage)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String state = "2";
    private int pageNum = 1;
    private ITransferToStorageListPresenter iTransferToStoragePresenter;
    private Unbinder unBinder;
    private final String[] titles = {"全部", "待验瓶", "已完成"};
    private TransferToStorageRclAdapter adapter;
    private List<TransferStorageListBean.DataBean.ListBean> list = new ArrayList<TransferStorageListBean.DataBean.ListBean>();
    private String id;
    private String transferType;
    private String fiveTemp;
    private String fifteenTemp;
    private String fiftyTemp;
    private String fivefullTemp;
    private String fifteenfullTemp;
    private String fiftyfullTemp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_storage);
        unBinder = ButterKnife.bind(this);
        transferType = getIntent().getStringExtra("TransferType");
        //统一调拨单
        //transferType = "2";
        iTransferToStoragePresenter = new ITransferToStorageListPresenter(this);
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();
                //调拨单状态：0-待验瓶， 1-已完成， 3-全部
                switch (tabPosition) {
                    //全部
                    case 0:
                        state = 3 + "";
                        break;
                    //待验瓶
                    case 1:
                        state = 0 + "";
                        break;
                    //已完成
                    case 2:
                        state = 1 + "";
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
        adapter = new TransferToStorageRclAdapter(this, this, list, Integer.valueOf(transferType));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        if (TextUtils.equals(transferType, "1")) {
            topBarView.setTitle("重瓶入库");
        } else if (TextUtils.equals(transferType, "0")) {
            topBarView.setTitle("空瓶出库");
        }else if(TextUtils.equals(transferType, "2")){
            topBarView.setTitle("统一调拨");
        }
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public String getSiteId() {
        String siteId = SharePreferenceUtil.getLoginSpStringValue("siteId");
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
        return "5";
    }

    @Override
    public void onGetListSuccess(TransferStorageListBean transferStorageListBean) {
        refreshLayout.finishRefresh();
        if (pageNum == 1) {
            list.clear();
        }
        refreshLayout.finishLoadMore();
        List<TransferStorageListBean.DataBean.ListBean> listTemp = transferStorageListBean.getData().getList();
        list.addAll(filterList(listTemp));
        if (pageNum == transferStorageListBean.getData().getPage().getPages()) {
            refreshLayout.setEnableAutoLoadMore(false);
        } else {
            refreshLayout.setEnableAutoLoadMore(true);
        }
        adapter.notifyDataSetChanged();
    }

    private List<TransferStorageListBean.DataBean.ListBean> filterList(List<TransferStorageListBean.DataBean.ListBean> listTemp) {
        List<TransferStorageListBean.DataBean.ListBean> list = new ArrayList<TransferStorageListBean.DataBean.ListBean>();
        for (TransferStorageListBean.DataBean.ListBean bean : listTemp) {
            if (bean.getTransformType() == Integer.valueOf(transferType)) {
                list.add(bean);
            }
        }
        return list;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFiveCount() {
        return fiveTemp;
    }

    @Override
    public String getFifteenCount() {
        return fifteenTemp;
    }

    @Override
    public String getFifthCount() {
        return fiftyTemp;
    }

    @Override
    public void onUpdateTransferSuccess(UpdateTransferBean updateTransferBean) {
        PopUtil.toastInBottom("修改调拨单钢瓶数量成功");
        pageNum = 1;
        iTransferToStoragePresenter.getTransferOrdersList();
    }

    @Override
    public String getFiveFullCount() {
        return fivefullTemp;
    }

    @Override
    public String getFifteenFullCount() {
        return fifteenfullTemp;
    }

    @Override
    public String getFifthFullCount() {
        return fiftyfullTemp;
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
        id = list.get(itemPosition).getId() + "";
        String fiveCount = list.get(itemPosition).getFiveCount() + "";
        String fifteenCount = list.get(itemPosition).getFifteenCount() + "";
        String fiftyCount = list.get(itemPosition).getFifthCount() + "";
        String fivefullCount = list.get(itemPosition).getFiveFullCount() + "";
        String fifteenfullCount = list.get(itemPosition).getFifteenFullCount() + "";
        String fiftyfullCount = list.get(itemPosition).getFiftyFullCount() + "";
        Intent intent = new Intent(this, ScanTransferStorageActivity.class);
        intent.putExtra(Constants.ORDER_ID, id);
        intent.putExtra(Constants.ORDER_FIVE_NUM, fiveCount);
        intent.putExtra(Constants.ORDER_FIFTEEN_NUM, fifteenCount);
        intent.putExtra(Constants.ORDER_FIFTY_NUM, fiftyCount);
        intent.putExtra(Constants.ORDER_FIVE_FULL_NUM, fivefullCount);
        intent.putExtra(Constants.ORDER_FIFTEEN_FULL_NUM, fifteenfullCount);
        intent.putExtra(Constants.ORDER_FIFTY_FULL_NUM, fiftyfullCount);
        intent.putExtra("TransferType", transferType + "");
        startActivity(intent);
    }

    @Override
    public void onClckModifyBtn(int position, String five, String fifteen, String fifty, String fivefull, String fifteenfull, String fiftyfull, String orderId) {
        PopUtil.showComfirmDialog(this, "修改调拨单", "确认提交修改调拨单钢瓶数量", "取消", "确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTransferToStoragePresenter.getTransferOrdersList();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = orderId;
                fiveTemp = five;
                fifteenTemp = fifteen;
                fiftyTemp = fifty;
                fivefullTemp  = fivefull;
                fifteenfullTemp = fifteen;
                fiftyfullTemp = fifty;
                iTransferToStoragePresenter.updateTransfer();
            }
        }, false);
    }

   /* @Override
    public void onClckModifyBtn(final int position, final String five, final String fifteen, final String fifty, final String orderId) {
        PopUtil.showComfirmDialog(this, "修改调拨单", "确认提交修改调拨单钢瓶数量", "取消", "确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTransferToStoragePresenter.getTransferOrdersList();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = orderId;
                fiveTemp = five;
                fifteenTemp = fifteen;
                fiftyTemp = fifty;
                iTransferToStoragePresenter.updateTransfer();
            }
        }, false);
    }*/

    @Override
    public void onError(String s) {
        super.onError(s);
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        list.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pageNum = 1;
        iTransferToStoragePresenter.getTransferOrdersList();
    }
}
