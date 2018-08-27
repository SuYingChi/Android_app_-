package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.BottleReplacePriceBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ExchangeRclBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IExchangeSteelBottlePresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.ExchangeBottleRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IExchangeSteelBottleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ExchangeSteelBottleActivity extends BaseActivity implements ExchangeBottleRclAdapter.OnExchangeRclClicklistener, IExchangeSteelBottleView {
    @BindView(R.id.exchange_topbar)
    TopBarView topBarView;
    @BindView(R.id.rcl_exchange_steel_bottle)
    RecyclerView recyclerView;
    @BindView(R.id.discount_save_btn)
    TextView saveBtn;
    @BindView(R.id.discount_cost)
    TextView tvTotalDiscount;
    @BindView(R.id.level)
    TextView tvLevel;
    private ArrayList<ExchangeRclBean> dataList;
    private IExchangeSteelBottlePresenter iExchangeSteelBottlePresenter;
    private double bottlePrice = 0;
    private ExchangeBottleRclAdapter myAdapter;
    private int weight;
    private String year;
    private String corrosionType;
    private double steelNum;
    private int rclItemPosition;
    private TextView tvItemAccount;
    private Unbinder unbinder;
    private ArrayList<ExchangeRclBean> dataListOriginal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_steel_bottle);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        int remainFive = intent.getIntExtra(Constants.REMAIN_FIVE_NUM, 0);
        int remainFifteen = intent.getIntExtra(Constants.REMAIN_FIFTEEN_NUM, 0);
        int remainFifty = intent.getIntExtra(Constants.REMAIN_FIFTY_NUM, 0);
        double exchangeFee = intent.getDoubleExtra(Constants.EXCHANGE_FEE, 0);
        double exchangeFeeOriginal = exchangeFee;
        tvTotalDiscount.setText(exchangeFee + "");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            dataList = (ArrayList<ExchangeRclBean>) bundle.getSerializable("exchangelist");
            dataListOriginal = dataList;

        }
        iExchangeSteelBottlePresenter = new IExchangeSteelBottlePresenter(this);
        myAdapter = new ExchangeBottleRclAdapter(dataList, this, this, remainFive, remainFifteen, remainFifty);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.showTipsDialog(ExchangeSteelBottleActivity.this, "放弃空瓶折价", "放弃自有产权钢瓶折价", "取消", "确认", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("exchangelist", dataListOriginal);
                        intent.putExtras(bundle);
                        intent.putExtra(Constants.EXCHANGE_FEE, exchangeFeeOriginal);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.showTipsDialog(ExchangeSteelBottleActivity.this, "保存折价", "保存自有产权钢瓶折价信息", "取消", "确认", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //回传自有产权折价价格给详情页
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("exchangelist", dataList);
                        intent.putExtras(bundle);
                        intent.putExtra(Constants.EXCHANGE_FEE, myAdapter.getTotalDiscount());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        });
        tvLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.showExchangeBottleTipsDialog(ExchangeSteelBottleActivity.this);
            }
        });
    }


    @Override
    public void onBottleNumChange(int rclItemPosition, TextView tvAccount, TextView tvBottleNum, int steelNum) {
        //计算对应Item价格,并刷新总价
        Double itemDiscount = getItemDiscountOnBottleNumChange(steelNum);
        dataList.get(rclItemPosition).setDiscount(itemDiscount);
        dataList.get(rclItemPosition).setBottleNum(steelNum);
        tvBottleNum.setText(steelNum + "");
        tvAccount.setText(itemDiscount + "");
        tvTotalDiscount.setText(myAdapter.getTotalDiscount() + "");
    }

    private Double getItemDiscountOnBottleNumChange(int steelNum) {
        return bottlePrice * steelNum;
    }

    @Override
    public void onSpinnerChange(int rclItemPosition, TextView tvAccount, int modelSelectIndex, int yearSelectIndex, int levelSelectIndex, int steelNum) {
        //计算对应Item价格,并刷新总价
        weight = 15;
        switch (levelSelectIndex) {
            case 0:
                corrosionType = "A";
                break;
            case 1:
                corrosionType = "B";
                break;
            case 2:
                corrosionType = "C";
                break;
            case 3:
                corrosionType = "D";
                break;
            default:
                break;
        }
        year = yearSelectIndex + 1 + "";
        this.steelNum = steelNum;
        this.rclItemPosition = rclItemPosition;
        this.tvItemAccount = tvAccount;
        iExchangeSteelBottlePresenter.getBottleReplacePrice();
    }

    //访问后台折价接口
    @Override
    public void onGetReplacePriceSuccess(BottleReplacePriceBean bean) {

        bottlePrice = bean.getData().getBottlePrice();
        double itemDiscount = bottlePrice * steelNum;
        //在适配器那边已经刷新spinner的选中数据源
        dataList.get(rclItemPosition).setDiscount(itemDiscount);
        tvItemAccount.setText(itemDiscount + "");
        tvTotalDiscount.setText(myAdapter.getTotalDiscount() + "");

    }

    @Override
    public String getBottleWeight() {
        return weight + "";
    }

    @Override
    public String getBottleProduceDate() {
        return year;
    }

    @Override
    public String getCorrosionType() {
        return corrosionType;
    }

    @Override
    public String getYear() {
        return year;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
