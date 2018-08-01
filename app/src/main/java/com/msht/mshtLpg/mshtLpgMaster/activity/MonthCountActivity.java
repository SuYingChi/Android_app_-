package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.msht.mshtLpg.mshtLpgMaster.Bean.MonthCountBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IMonthCountPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.MonthcountAdapter;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IMonthCountView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MonthCountActivity extends BaseActivity implements IMonthCountView {
    @BindView(R.id.month_count_layout_topbar)
    TopBarView topBarView;
    @BindView(R.id.rcl_month_count)
    RecyclerView recyclerView;


    private Unbinder unbinder;
    private List<MonthCountBean.ListBean> dataList = new ArrayList<MonthCountBean.ListBean>();
    private MonthcountAdapter myAdapter;
    private IMonthCountPresenter iMonthCountPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_count_layout);
        unbinder = ButterKnife.bind(this);
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myAdapter = new MonthcountAdapter(dataList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        iMonthCountPresenter = new IMonthCountPresenter(this);
        iMonthCountPresenter.getMonthCount();
    }

    @Override
    public void onGetMonthCountSuccess(MonthCountBean bean) {
        dataList.addAll(bean.getListBean());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}