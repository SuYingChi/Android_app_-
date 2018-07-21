package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ExchangeReviewBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IExchangeReviewPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.ExchangeReviewBottleRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IExchangeReviewView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeReviewActivity extends BaseActivity implements IExchangeReviewView {

    @BindView(R.id.exchange_topbar)
    TopBarView topBarView;
    @BindView(R.id.rcl_exchange_steel_bottle)
    RecyclerView recyclerView;;
    private IExchangeReviewPresenter iExchangeReviewPresenter;
    private String orderId;
    private List<ExchangeReviewBean.DataBean> dataList = new ArrayList<ExchangeReviewBean.DataBean>();
    private ExchangeReviewBottleRclAdapter myAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_review);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        iExchangeReviewPresenter = new IExchangeReviewPresenter(this);
        iExchangeReviewPresenter.getExchangeReview();
    }

    private void initView() {
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myAdapter = new ExchangeReviewBottleRclAdapter(dataList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onGetExchangeReviewSuccess(ExchangeReviewBean bean) {
        dataList.addAll(bean.getData());
        initView();
    }

    @Override
    public String getOrderId() {
        return orderId;
    }
}
