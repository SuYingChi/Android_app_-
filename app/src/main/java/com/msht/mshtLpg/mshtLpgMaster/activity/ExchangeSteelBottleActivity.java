package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ExchangeRclBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IGetSubstitutionListPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.ExchangeBottleRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IExchangeSteelBottleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeSteelBottleActivity extends BaseActivity implements ExchangeBottleRclAdapter.OnExchangeRclClicklistener,IExchangeSteelBottleView {
    @BindView(R.id.exchange_topbar)
    TopBarView topBarView;
    @BindView(R.id.rcl_exchange_steel_bottle)
    RecyclerView recyclerView;
    private int remainFive;
    private int remainFifteen;
    private int remainFifty;
    private ArrayList<ExchangeRclBean> myDataset = new ArrayList<>();
    private IGetSubstitutionListPresenter iGetSubstitutionListPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_steel_bottle);
        ButterKnife.bind(this);
        Intent intent = getIntent();
         remainFive = intent.getIntExtra(Constants.REMAIN_FIVE_NUM,0);
         remainFifteen = intent.getIntExtra(Constants.REMAIN_FIFTEEN_NUM,0);
         remainFifty = intent.getIntExtra(Constants.REMAIN_FIFTY_NUM,0);
         iGetSubstitutionListPresenter = new IGetSubstitutionListPresenter(this);
         initView();
    }

    private void initView() {
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ExchangeBottleRclAdapter myAdapter = new ExchangeBottleRclAdapter(myDataset,this,this,remainFive,remainFifteen,remainFifty);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        iGetSubstitutionListPresenter.GetSubstitutionList();
    }



    @Override
    public void onBottleNumChange(int steelNum, TextView tvAccount, int modelSelectIndex, int yearSelectIndex) {

    }

    @Override
    public void onSpinnerChange(int modelIndex, int yearsIndex, int bottleNum, TextView tvAccount) {

    }
//访问后台折价接口
    @Override
    public void onGetSubstitutionListSuccess() {

    }
}
