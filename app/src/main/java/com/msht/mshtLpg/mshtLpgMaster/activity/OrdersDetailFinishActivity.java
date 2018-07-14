package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersDetailFinishActivity extends BaseActivity  {
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.elevator)
    TextView elevator;
    @BindView(R.id.comman_topbar_user)
    TextView comman_topbar_user;
    @BindView(R.id.comman_topbar_day)
    TextView comman_topbar_day;
    @BindView(R.id.comman_topbar_time)
    TextView comman_topbar_time;
    @BindView(R.id.comman_topbar_comment)
    TextView comman_topbar_comment;
    @BindView(R.id.tv_orderid)
    TextView tv_orderid;
    @BindView(R.id.five_num)
    TextView tvFive;
    @BindView(R.id.fifteen_num)
    TextView tvFifteen;
    @BindView(R.id.fifty_num)
    TextView tvFifty;
    @BindView(R.id.five_deposit)
    TextView tvFiveDeposite;
    @BindView(R.id.fifteen_deposit)
    TextView tvFifteenDeposite;
    @BindView(R.id.fifty_deposit)
    TextView tvFiftyDeposite;
    @BindView(R.id.gas_fee)
    TextView tvGasFee;
    @BindView(R.id.deposit_fee)
    TextView tvDepositeFee;
    @BindView(R.id.deliver_fee)
    TextView tvDeliverFee;
    @BindView(R.id.item_rcl_order_detail_pay_v6_discount)
    TextView tvDiscount;
    @BindView(R.id.dispatch_orders_time)
    TextView tvDispatchOrderTime;
    @BindView(R.id.dispatch_bottle_time)
    TextView tvDispatchBottleTime;
    @BindView(R.id.payAmount)
    TextView payAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_finish);
        ButterKnife.bind(this);

    }
}
