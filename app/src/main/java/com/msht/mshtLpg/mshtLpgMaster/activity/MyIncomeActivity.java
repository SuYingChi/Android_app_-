package com.msht.mshtLpg.mshtLpgMaster.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.MyIncomeBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IMyIncomePresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.MyIncomeAdapter;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IMyIncomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyIncomeActivity extends BaseActivity implements IMyIncomeView {

    @BindView(R.id.my_income_layout_topbar)
    TopBarView topBarView;
    @BindView(R.id.my_income_total_income)
    TextView tvTotalIncome;
    @BindView(R.id.tv_my_income_month_count)
    TextView tvMonthCount;
    @BindView(R.id.rcl_my_income)
    RecyclerView recyclerView;


    private Unbinder unbinder;
    private IMyIncomePresenter iMyIncomePresenter;
    private List<MyIncomeBean.DataBean.ListBean> dataList = new ArrayList<MyIncomeBean.DataBean.ListBean>();
    private MyIncomeAdapter myAdapter;
    private MyIncomeBean myIncomeBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_income_layout);
        unbinder = ButterKnife.bind(this);
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myAdapter = new MyIncomeAdapter(dataList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        iMyIncomePresenter = new IMyIncomePresenter(this);
        iMyIncomePresenter.getMyIncome();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onGetMyIncomeSuccess(MyIncomeBean myIncomeBean) {
        tvTotalIncome.setText(myIncomeBean.getData().getTotalIncome() + "");
        dataList.addAll(myIncomeBean.getData().getList());
        myAdapter.notifyDataSetChanged();
        tvMonthCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyIncomeActivity.this, MonthCountActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MyIncomeBean", myIncomeBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
