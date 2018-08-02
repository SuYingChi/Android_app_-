package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.msht.mshtLpg.mshtLpgMaster.Bean.MonthCountBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.MyIncomeBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IMonthCountPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.MonthcountAdapter;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MonthCountActivity extends BaseActivity  {
    @BindView(R.id.month_count_layout_topbar)
    TopBarView topBarView;
    @BindView(R.id.rcl_month_count)
    RecyclerView recyclerView;


    private Unbinder unbinder;
    private List<MonthCountBean> dataList = new ArrayList<MonthCountBean>();
    private IMonthCountPresenter iMonthCountPresenter;
    private MyIncomeBean bean;

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
        Bundle bundle = getIntent().getExtras();
        bean = (MyIncomeBean)bundle.getSerializable("MyIncomeBean");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        List<MonthCountBean> list = filterListByMonth(bean,year,month);
        dataList.addAll(list);
        MonthcountAdapter myAdapter = new MonthcountAdapter(dataList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

    }

    private List<MonthCountBean> filterListByMonth(MyIncomeBean bean, int year, int month) {
        int ii;
        List<MonthCountBean> list= new ArrayList<MonthCountBean>();
        List<MyIncomeBean.DataBean.ListBean> listBeans = bean.getData().getList();
        for(int i=year;i>year-2;--i) {
            if(i==year){
                ii = month;
            }else {
                ii = 12;
            }
            for (;ii>0;ii--) {
                double fee=0;
                for (MyIncomeBean.DataBean.ListBean listBean : listBeans) {
                    if (Integer.valueOf(listBean.getYear()) == i && Integer.valueOf(listBean.getMonth()) == ii) {
                        fee += listBean.getDeliveryFee();
                    }
                }
                list.add(new MonthCountBean(fee, i + "", ii + ""));
            }
        }
        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}