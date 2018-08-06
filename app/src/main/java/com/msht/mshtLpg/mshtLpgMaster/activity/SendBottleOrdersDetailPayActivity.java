package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.msht.mshtLpg.mshtLpgMaster.Bean.DeliveryBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.DeliverFareDialog;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;
import com.yanzhenjie.permission.Permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SendBottleOrdersDetailPayActivity extends BaseActivity implements IOrderDetailView ,PermissionUtils.PermissionRequestFinishListener{
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
    @BindView(R.id.receipt)
    TextView tvTotal;
    @BindView(R.id.weixin_pay)
    TextView tvpay;
    @BindView(R.id.comman_topbar_call_phone_btn)
    LinearLayout callBtn;
    @BindView(R.id.id_weichat_layout)
    View layoutWeiChat;
    @BindView(R.id.id_aliPay_layout)
    View layoutAliPay;
    @BindView(R.id.ll_deliver_fare)
    LinearLayout lldeliver;
    private String orderId;
    private double fiveTotalDeposite;
    private double fifteenteenTotalDeposite;
    private double fiftyTotalDeposite;
    private double totalfare;
    private String orderType;
    private String payType;
    private Unbinder unbinder;
    private Context mContext;
    private DeliverFareDialog deliverFareDialog;
    private Map<String,String> map = new HashMap<String,String>();
    private boolean isGetFourDeliverySuccess = false;
    private boolean isGetSixDeliverySuccess = false;
    private boolean isGetFirstDeliverySuccess  =false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_orders);
        unbinder = ButterKnife.bind(this);
        mContext=this;
        //从首页订单列表跳转过来
        Intent intent = getIntent();
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        IOrderDetailPresenter iOrderDetailPresenter = new IOrderDetailPresenter(this);
        iOrderDetailPresenter.getOrderDetail();
        iOrderDetailPresenter.getFirstDelivery();
        iOrderDetailPresenter.getFourDelivery();
        iOrderDetailPresenter.getSixDelivery();
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent =  new Intent(SendBottleOrdersDetailPayActivity.this,ExchangeReviewActivity.class);
               intent.putExtra(Constants.ORDER_ID,orderId);
               startActivity(intent);
            }
        });
    }

    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean orderDetailBean) {

        String floor = orderDetailBean.getData().getFloor() + "";
        String room = orderDetailBean.getData().getRoomNum();
        String address = orderDetailBean.getData().getAddress();
        tvLocation.setText(new StringBuilder().append(address).append(floor).append("层").append(room).append("房").toString());
        int isElevator = orderDetailBean.getData().getIsElevator();
        tvElevator.setText(isElevator == 1 ? "(有电梯)" : "(无电梯)");
        tvUser.setText(new StringBuilder().append(orderDetailBean.getData().getBuyer()).append(orderDetailBean.getData().getSex() == 1 ? "(先生)" : "(女士)").toString());
        tvTel.setText(orderDetailBean.getData().getMobile());
        tvDay.setText(orderDetailBean.getData().getCreateDate());
        tvTime.setText(orderDetailBean.getData().getAppointmentTime());
        tvComment.setText(orderDetailBean.getData().getRemarks());
        orderId = orderDetailBean.getData().getOrderId()+"";
        tvOrderId.setText(orderId );
        tvDispatchOrderTime.setText(orderDetailBean.getData().getCreateDate());
        tvDispatchBottleTime.setText(orderDetailBean.getData().getAppointmentTime());
        //气价
        double fiveGasFee = orderDetailBean.getData().getFiveBottleCount() * orderDetailBean.getData().getFiveGasFee();
        tvFiveGas.setText(fiveGasFee + "");
        double fifteenGasFee = orderDetailBean.getData().getFifteenBottleCount() * orderDetailBean.getData().getFifteenGasFee();
        tvFifteenGas.setText(fifteenGasFee + "");
        double fiftyGasFee = orderDetailBean.getData().getFiftyBottleCount() * orderDetailBean.getData().getFiftyGasFee();
        tvFiftyGas.setText(fiftyGasFee + "");
        double totalGas = fiveGasFee + fifteenGasFee + fiftyGasFee;
        tvGasFee.setText(totalGas + "");

        //押金
        int remain5 = orderDetailBean.getData().getFiveBottleCount() - orderDetailBean.getData().getReFiveBottleCount();
        if (remain5 == 0) {
            llFiveDeposite.setVisibility(View.GONE);
        } else {
            llFiveDeposite.setVisibility(View.VISIBLE);
            fiveTotalDeposite = remain5 * orderDetailBean.getData().getFiveDepositFee();
            tvFiveDeposite.setText(fiveTotalDeposite + "");
        }
        int remain15 = orderDetailBean.getData().getFifteenBottleCount() - orderDetailBean.getData().getReFifteenBottleCount();
        if (remain15 == 0) {
            llFifteenDeposite.setVisibility(View.GONE);
        } else {
            llFifteenDeposite.setVisibility(View.VISIBLE);
            fifteenteenTotalDeposite = remain15 * orderDetailBean.getData().getFifteenDepositFee();
            tvFifteenDeposite.setText(fifteenteenTotalDeposite + "");
        }
        int remain50 = orderDetailBean.getData().getFiftyBottleCount() - orderDetailBean.getData().getReFiftyBottleCount();
        if (remain50 == 0) {
            llFiftyDeposite.setVisibility(View.GONE);
        } else {
            llFiftyDeposite.setVisibility(View.VISIBLE);
            fiftyTotalDeposite = remain50 * orderDetailBean.getData().getFiftyDepositFee();
            tvFiftyDeposite.setText(fiftyTotalDeposite + "");
        }
        double totalDeposite = fiveTotalDeposite + fifteenteenTotalDeposite + fiftyTotalDeposite;
        if (totalDeposite == 0) {
            llDepositFare.setVisibility(View.GONE);
        } else {
            llDepositFare.setVisibility(View.VISIBLE);
            tvDepositeFee.setText(totalDeposite + "");
        }
        //运费

        double fiveDeliveryFee = orderDetailBean.getData().getFiveDeliveryFee();
        double fifteenDeliveryFee = orderDetailBean.getData().getFifteenDeliveryFee();
        double fiftyDeliveryFee = orderDetailBean.getData().getFiftyDeliveryFee();
        double totalFiveDelivery = orderDetailBean.getData().getFiveBottleCount() * fiveDeliveryFee;
        double totalFifteenDelivery = orderDetailBean.getData().getFifteenBottleCount() * fifteenDeliveryFee;
        double totalFiftyDelivery = orderDetailBean.getData().getFiftyBottleCount() * fiftyDeliveryFee;
        double totalDeliveryfare = totalFiveDelivery + totalFifteenDelivery + totalFiftyDelivery;
        tvDeliverFee.setText(totalDeliveryfare + "");
        double exchange = orderDetailBean.getData().getRetrieveAmount();
        tvDiscount.setText(exchange + "");
        totalfare = totalGas + totalDeposite + totalDeliveryfare - exchange;
        //tvTotal.setText(totalfare+"");
        tvTotal.setText(orderDetailBean.getData().getRealAmount()+"");
        layoutWeiChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderType = "1";
                payType = "12";
                Intent intent = new Intent(SendBottleOrdersDetailPayActivity.this, QRCodeReceiptActivity.class);
                intent.putExtra(Constants.ORDER_ID,orderId);
                intent.putExtra(Constants.URL_PARAMS_ORDER_TYPE,orderType);
                intent.putExtra(Constants.PAY_AMOUNT,totalfare+"");
                intent.putExtra(Constants.PAY_TYPE,payType);
                startActivity(intent);
            }
        });
        lldeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isGetFirstDeliverySuccess||!isGetFourDeliverySuccess||!isGetSixDeliverySuccess){
                    PopUtil.toastInBottom("正在获取运费信息，请稍后再试");
                }
                else if(deliverFareDialog == null) {
                    deliverFareDialog = new DeliverFareDialog(SendBottleOrdersDetailPayActivity.this,map);
                }else if(!deliverFareDialog.isShowing()){
                    deliverFareDialog.show();
                }
            }
        });
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.requestPermissions(SendBottleOrdersDetailPayActivity.this, SendBottleOrdersDetailPayActivity.this, Permission.CALL_PHONE);
            }
        });
        layoutAliPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        onRequestServer();
            }
        });
    }
    private void onRequestServer() {
        Toast.makeText(mContext,"现金支付请求接口,接口未完成", Toast.LENGTH_LONG).show();
    }
    @Override
    public String getOrderId() {
        return orderId + "";
    }

    @Override
    public void onGetFourDeliverySuccess(DeliveryBean bean) {
        String four5 = bean.getData().getDeliveryFee().getFiveDeliveryFee()+"";
        String four15 = bean.getData().getDeliveryFee().getFifteenDeliveryFee()+"";
        String four50 = bean.getData().getDeliveryFee().getFiftyDeliveryFee()+"";
        map.put("four5",four5);
        map.put("four15",four15);
        map.put("four50",four50);
        isGetFourDeliverySuccess = true;
    }

    @Override
    public void onGetSixDeliverySuccess(DeliveryBean bean) {
        String  six5 = bean.getData().getDeliveryFee().getFiveDeliveryFee()+"";
        String six15 = bean.getData().getDeliveryFee().getFifteenDeliveryFee()+"";
        String six50 =bean.getData().getDeliveryFee().getFiftyDeliveryFee()+"";
        map.put("six5",six5);
        map.put("six15",six15);
        map.put("six50",six50);
        isGetSixDeliverySuccess = true;
    }

    @Override
    public void onGetFirstDeliverySuccess(DeliveryBean bean) {
        String first5 = bean.getData().getDeliveryFee().getFiveDeliveryFee()+"";
        String first15 = bean.getData().getDeliveryFee().getFifteenDeliveryFee()+"";
        String first50 =bean.getData().getDeliveryFee().getFiftyDeliveryFee()+"";
        map.put("first5",first5);
        map.put("first15",first15);
        map.put("first50",first50);
        isGetFirstDeliverySuccess = true;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}