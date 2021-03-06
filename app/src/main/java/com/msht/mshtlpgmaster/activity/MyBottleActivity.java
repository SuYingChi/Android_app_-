package com.msht.mshtlpgmaster.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtlpgmaster.Bean.MyBottleListBean;
import com.msht.mshtlpgmaster.Present.IMyBottlePresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.adapter.MyBottleRclAdapter;
import com.msht.mshtlpgmaster.customView.TopBarView;
import com.msht.mshtlpgmaster.util.BottleCaculteUtil;
import com.msht.mshtlpgmaster.viewInterface.IMyBottleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyBottleActivity extends BaseActivity implements IMyBottleView {
    @BindView(R.id.topbar)
    TopBarView topBarView;
    @BindView(R.id.scan_rcl_deliver_steel_bottle)
    RecyclerView recyclerView;
    @BindView(R.id.total_bottle)
    TextView tvTotal;
    @BindView(R.id.scan_qrcode_delive_five_steel_bottle_number)
    TextView tvFive;
    @BindView(R.id.scan_qrcode_delive_fifteen_steel_bottle_number)
    TextView tvFifteen;
    @BindView(R.id.scan_qrcode_delive_fifty_steel_bottle_number)
    TextView tvFifty;
    private Unbinder unbinder;
    private IMyBottlePresenter iMyBottlePresenter;
    private List<MyBottleListBean.DataBean.ListBean> dataList = new ArrayList<MyBottleListBean.DataBean.ListBean>();
    private MyBottleRclAdapter myAdapter;
    private int fiveCount;
    private int fifteenCount;
    private int fiftyCount;
    private int total;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bottle);
        unbinder = ButterKnife.bind(this);

        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myAdapter = new MyBottleRclAdapter(dataList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        iMyBottlePresenter = new IMyBottlePresenter(this);
        iMyBottlePresenter.getMyBottleList();

    }

    @Override
    public void onGetMyBottleListSuccess(MyBottleListBean myBottleListBean) {
        dataList.addAll(myBottleListBean.getData().getList());
        myAdapter.notifyDataSetChanged();
        fiveCount = BottleCaculteUtil.getMyBottleNum(dataList, 5);
        fifteenCount = BottleCaculteUtil.getMyBottleNum(dataList, 15);
        fiftyCount = BottleCaculteUtil.getMyBottleNum(dataList, 50);
        total = dataList.size();
        tvFive.setText(fiveCount + "");
        tvFifteen.setText(fifteenCount + "");
        tvFifty.setText(fiftyCount + "");
        tvTotal.setText(total + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
