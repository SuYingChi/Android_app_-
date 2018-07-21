package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersDetailFinishActivity extends BaseActivity  implements IOrderDetailView,PermissionUtils.PermissionRequestFinishListener{
    @BindView(R.id.pay_orders_v2_topbar)
    TopBarView topBarView;
    @BindView(R.id.location)
    TextView tvLocation;
    @BindView(R.id.elevator)
    TextView tvElevator;
    @BindView(R.id.comman_topbar_user)
    TextView tvUser;
    @BindView(R.id.comman_topbar_time)
    TextView tvTime;
    @BindView(R.id.comman_topbar_day)
    TextView tvDay;
    @BindView(R.id.comman_topbar_telephone)
    TextView tvTel;
    @BindView(R.id.comman_topbar_comment)
    TextView tvComment;
    @BindView(R.id.tv_orderid)
    TextView tvOrderId;
    @BindView(R.id.ll_orders_detail_five_deposit)
    LinearLayout llFiveDeposite;
    @BindView(R.id.ll_orders_detail_fifteen_deposit)
    LinearLayout llFifteenDeposite;
    @BindView(R.id.ll_orders_detail_fifty_deposit)
    LinearLayout llFiftyDeposite;
    @BindView(R.id.five_gas)
    TextView tvFiveGas;
    @BindView(R.id.fifteen_gas)
    TextView tvFifteenGas;
    @BindView(R.id.fifty_gas)
    TextView tvFiftyGas;
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
    @BindView(R.id.ll_discount)
    LinearLayout llDiscount;
    @BindView(R.id.ll_deposit_fare)
    LinearLayout llDepositFare;
    @BindView(R.id.floor)
    TextView tvFloor;
    @BindView(R.id.room)
    TextView tvroom;
    @BindView(R.id.payAmount)
    TextView tvPayAmount;
    @BindView(R.id.comman_topbar_call_phone_btn)
    LinearLayout callBtn;
    private OrderDetailBean bean;
    private int floor;
    private String room;
    private int isElevator;
    private String orderId;
    private double fiveGasFee;
    private double fifteenGasFee;
    private double fiftyGasFee;
    private double totalGas;
    private int remain5;
    private double fiveTotalDeposite;
    private int remain15;
    private double fifteenteenTotalDeposite;
    private int remain50;
    private double fiftyTotalDeposite;
    private double totalDeposite;
    private double fiveDeliveryFee;
    private double fifteenDeliveryFee;
    private double fiftyDeliveryFee;
    private double totalFiveDelivery;
    private double totalFifteenDelivery;
    private double totalFiftyDelivery;
    private double totalDeliveryfare;
    private double exchange;
    private double totalfare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_finish);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        new IOrderDetailPresenter(this).getOrderDetail();
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean orderDetailBean) {

        this.bean = orderDetailBean;
        tvLocation.setText(orderDetailBean.getData().getAddress());
        floor = orderDetailBean.getData().getFloor();
        tvFloor.setText(floor + "层");
        room = orderDetailBean.getData().getRoomNum();
        tvroom.setText(room + "房");
        isElevator = orderDetailBean.getData().getIsElevator();
        tvElevator.setText(isElevator == 1 ? "(有电梯)" : "(无电梯)");
        tvUser.setText(new StringBuilder().append(orderDetailBean.getData().getBuyer()).append(orderDetailBean.getData().getSex() == 1 ? "(先生)" : "(女士)").toString());
        tvTel.setText(orderDetailBean.getData().getMobile());
        tvDay.setText(orderDetailBean.getData().getCreateDate());
        tvTime.setText(orderDetailBean.getData().getAppointmentTime());
        tvComment.setText(orderDetailBean.getData().getRemarks());
        orderId = orderDetailBean.getData().getOrderId()+"";
        tvOrderId.setText(orderId + "");
        tvDispatchOrderTime.setText(orderDetailBean.getData().getCreateDate());
        tvDispatchBottleTime.setText(orderDetailBean.getData().getAppointmentTime());
        //气价
        fiveGasFee = orderDetailBean.getData().getFiveBottleCount() * orderDetailBean.getData().getFiveGasFee();
        tvFiveGas.setText(fiveGasFee + "");
        fifteenGasFee = orderDetailBean.getData().getFifteenBottleCount() * orderDetailBean.getData().getFifteenGasFee();
        tvFifteenGas.setText(fifteenGasFee + "");
        fiftyGasFee = orderDetailBean.getData().getFiftyBottleCount() * orderDetailBean.getData().getFiftyGasFee();
        tvFiftyGas.setText(fiftyGasFee + "");
        totalGas = fiveGasFee + fifteenGasFee + fiftyGasFee;
        tvGasFee.setText(totalGas + "");

        //押金
        remain5 = orderDetailBean.getData().getFiveBottleCount() - orderDetailBean.getData().getReFiveBottleCount();
        if (remain5 == 0) {
            llFiveDeposite.setVisibility(View.GONE);
        } else {
            llFiveDeposite.setVisibility(View.VISIBLE);
            fiveTotalDeposite = remain5 * orderDetailBean.getData().getFiveDepositFee();
            tvFiveDeposite.setText(fiveTotalDeposite + "");
        }
        remain15 = orderDetailBean.getData().getFifteenBottleCount() - orderDetailBean.getData().getReFifteenBottleCount();
        if (remain15 == 0) {
            llFifteenDeposite.setVisibility(View.GONE);
        } else {
            llFifteenDeposite.setVisibility(View.VISIBLE);
            fifteenteenTotalDeposite = remain15 * orderDetailBean.getData().getFifteenDepositFee();
            tvFifteenDeposite.setText(fifteenteenTotalDeposite + "");
        }
        remain50 = orderDetailBean.getData().getFiftyBottleCount() - orderDetailBean.getData().getReFiftyBottleCount();
        if (remain50 == 0) {
            llFiftyDeposite.setVisibility(View.GONE);
        } else {
            llFiftyDeposite.setVisibility(View.VISIBLE);
            fiftyTotalDeposite = remain50 * orderDetailBean.getData().getFiftyDepositFee();
            tvFiftyDeposite.setText(fiftyTotalDeposite + "");
        }
        totalDeposite = fiveTotalDeposite + fifteenteenTotalDeposite + fiftyTotalDeposite;
        if (totalDeposite == 0) {
            llDepositFare.setVisibility(View.GONE);
        } else {
            llDepositFare.setVisibility(View.VISIBLE);
            tvDepositeFee.setText(totalDeposite + "");
        }
        //运费

        fiveDeliveryFee = orderDetailBean.getData().getFiveDeliveryFee();
        fifteenDeliveryFee = orderDetailBean.getData().getFifteenDeliveryFee();
        fiftyDeliveryFee = orderDetailBean.getData().getFiftyDeliveryFee();
        totalFiveDelivery = orderDetailBean.getData().getFiveBottleCount() * fiveDeliveryFee;
        totalFifteenDelivery = orderDetailBean.getData().getFifteenBottleCount() * fifteenDeliveryFee;
        totalFiftyDelivery = orderDetailBean.getData().getFiftyBottleCount() * fiftyDeliveryFee;
        totalDeliveryfare = totalFiveDelivery + totalFifteenDelivery + totalFiftyDelivery;
        tvDeliverFee.setText(totalDeliveryfare + "");
        exchange = orderDetailBean.getData().getRetrieveAmount();
        if(exchange==0){
            llDiscount.setVisibility(View.GONE);
        }else {
            tvDiscount.setText(exchange+"");
        }
        tvDiscount.setText(exchange + "");
        totalfare = totalGas+totalDeposite+totalDeliveryfare-exchange;
        tvPayAmount.setText(totalfare+"");
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.requestPermissions(OrdersDetailFinishActivity.this, OrdersDetailFinishActivity.this, Permission.CALL_PHONE);
            }
        });
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public void onBackFromSettingPage() {

    }

    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        PopUtil.toastInBottom("请允许LPG拨打电话");
    }

    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvTel.getText().toString()));
        startActivity(intent);
    }
}
