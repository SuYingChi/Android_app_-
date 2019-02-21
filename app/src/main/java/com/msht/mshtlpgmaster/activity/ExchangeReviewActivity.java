package com.msht.mshtlpgmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtlpgmaster.Bean.ExchangeReviewBean;
import com.msht.mshtlpgmaster.Present.IExchangeReviewPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.adapter.ExchangeReviewBottleRclAdapter;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.customView.TopBarView;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IExchangeReviewView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ExchangeReviewActivity extends BaseActivity implements IExchangeReviewView {

    @BindView(R.id.exchange_topbar)
    TopBarView topBarView;
    @BindView(R.id.rcl_exchange_steel_bottle)
    RecyclerView recyclerView;
    @BindView(R.id.level)
    TextView textView;
    private IExchangeReviewPresenter iExchangeReviewPresenter;
    private String orderId;
    private List<ExchangeReviewBean.DataBean> dataList = new ArrayList<ExchangeReviewBean.DataBean>();
    private ExchangeReviewBottleRclAdapter myAdapter;
    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_review);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        iExchangeReviewPresenter = new IExchangeReviewPresenter(this);
        iExchangeReviewPresenter.getExchangeReview();
        initView();
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
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.showWebViewDialog(ExchangeReviewActivity.this, Constants.HUI_SHOU_ZHE_JIA);
            }
        });
    }

    @Override
    public void onGetExchangeReviewSuccess(ExchangeReviewBean bean) {
        dataList.addAll(bean.getData());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
