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
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IExchangeSteelBottleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeSteelBottleActivity extends BaseActivity implements ExchangeBottleRclAdapter.OnExchangeRclClicklistener, IExchangeSteelBottleView {
    @BindView(R.id.exchange_topbar)
    TopBarView topBarView;
    @BindView(R.id.rcl_exchange_steel_bottle)
    RecyclerView recyclerView;
    @BindView(R.id.discount_cost)
    TextView textView;
    @BindView(R.id.discount_save_btn)
    TextView saveBtn;
    @BindView(R.id.discount_cost)
    TextView tvTotalDiscount;
    private int remainFive;
    private int remainFifteen;
    private int remainFifty;
    private ArrayList<ExchangeRclBean> dataList = new ArrayList<>();
    private IExchangeSteelBottlePresenter iExchangeSteelBottlePresenter;
    private int discount;
    private double bottlePrice = 0;
    private ExchangeBottleRclAdapter myAdapter;
    private int weight;
    private String year;
    private int corrosionType;
    private double steelNum;
    private int rclItemPosition;
    private TextView tvItemAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_steel_bottle);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        remainFive = intent.getIntExtra(Constants.REMAIN_FIVE_NUM, 0);
        remainFifteen = intent.getIntExtra(Constants.REMAIN_FIFTEEN_NUM, 0);
        remainFifty = intent.getIntExtra(Constants.REMAIN_FIFTY_NUM, 0);
        iExchangeSteelBottlePresenter = new IExchangeSteelBottlePresenter(this);
        initView();
    }

    private void initView() {
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myAdapter = new ExchangeBottleRclAdapter(dataList, this, this, remainFive, remainFifteen, remainFifty);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回传自有产权折价价格给详情页
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("exchangelist", dataList);
                intent.putExtras(bundle);
                intent.putExtra(Constants.EXCHANGE_FEE, discount);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        tvTotalDiscount.setText(0+"");
    }


    @Override
    public void onBottleNumChange(int rclItemPosition, TextView tvAccount, TextView tvBottleNum, int steelNum) {
    //计算对应Item价格,并刷新总价
        Double itemDiscount= getItemDiscountOnBottleNumChange(steelNum);
        dataList.get(rclItemPosition).setDiscount(itemDiscount);
        dataList.get(rclItemPosition).setBottleNum(steelNum);
        tvBottleNum.setText(steelNum+"");
        tvAccount.setText(itemDiscount+"");
        tvTotalDiscount.setText(myAdapter.getTotalDiscount()+"");
    }

    private Double getItemDiscountOnSpinnerChange(int modelSelectIndex, int yearSelectIndex,int levelSelectIndex,int steelNum) {
     return null;
    }
    private Double getItemDiscountOnBottleNumChange(int steelNum) {
        return bottlePrice*steelNum;
    }

    @Override
    public void onSpinnerChange(int rclItemPosition, TextView tvAccount,int modelSelectIndex, int yearSelectIndex,int levelSelectIndex,int steelNum) {
     //计算对应Item价格,并刷新总价

        switch (modelSelectIndex){
            case 0:
                weight = 5;
                break;
            case 1:
                weight = 15;
                break;
            case 2:
                weight = 50;
                break;
            default:break;
        }
        switch (yearSelectIndex){
            case 0:
                year = "2018";
                break;
            case 1:
                year = "2017";
                break;
            case 2:
                year = "2016";
                break;
            case 3:
                year = "2015";
                break;
            case 4:
                year = "2014";
                break;
            case 5:
                year = "2013";
                break;
            case 6:
                year = "6年以上";
                break;

            default:break;
        }
        corrosionType = levelSelectIndex;
        this.steelNum = steelNum;
        this.rclItemPosition = rclItemPosition;
        this.tvItemAccount = tvAccount;
        iExchangeSteelBottlePresenter.getBottleReplacePrice();
    }

    //访问后台折价接口
    @Override
    public void onGetReplacePriceSuccess(BottleReplacePriceBean bean) {
        //解析拿到数据
        bottlePrice = 10;

        double itemDiscount = bottlePrice * steelNum;
        //在适配器那边已经刷新spinner的选中数据源
        dataList.get(rclItemPosition).setDiscount(itemDiscount);
        tvItemAccount.setText(itemDiscount+"");
        tvTotalDiscount.setText(myAdapter.getTotalDiscount()+"");

    }

    @Override
    public String getBottleWeight() {
        return weight+"";
    }

    @Override
    public String getBottleProduceDate() {
        return year;
    }

    @Override
    public String getCorrosionType() {
        return corrosionType +"";
    }


}
