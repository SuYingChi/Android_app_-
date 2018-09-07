package com.msht.mshtlpgmaster.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.msht.mshtlpgmaster.Bean.DeliveryBean;
import com.msht.mshtlpgmaster.Bean.EmpPayBean;
import com.msht.mshtlpgmaster.Bean.OrderDetailBean;
import com.msht.mshtlpgmaster.Bean.PayFinishBean;
import com.msht.mshtlpgmaster.Present.ICashPayPresenter;
import com.msht.mshtlpgmaster.Present.IOrderDetailPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.customView.DeliverFareDialog;
import com.msht.mshtlpgmaster.customView.TopBarView;
import com.msht.mshtlpgmaster.util.PermissionUtils;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.ICashPayView;
import com.msht.mshtlpgmaster.viewInterface.IOrderDetailView;
import com.pingplusplus.android.Pingpp;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SendBottleOrdersDetailPayActivity extends BaseActivity implements IOrderDetailView, ICashPayView, PermissionUtils.PermissionRequestFinishListener {
    @BindView(R.id.pay_orders_v2_topbar)
    TopBarView topBarView;
    @BindView(R.id.location)
    TextView tvLocation;
    @BindView(R.id.elevator)
    TextView tvElevator;
    @BindView(R.id.comman_topbar_user)
    TextView tvUser;
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

    private DeliverFareDialog deliverFareDialog;
    private Map<String, String> map = new HashMap<String, String>();
    private boolean isGetFourDeliverySuccess = false;
    private boolean isGetSixDeliverySuccess = false;
    private boolean isGetFirstDeliverySuccess = false;
    private boolean isGetSecondDeliverySuccess = false;
    private ICashPayPresenter iCashPayPresenter;
    private String channel = 1 + "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_orders);
        unbinder = ButterKnife.bind(this);
        //从首页订单列表跳转过来
        Intent intent = getIntent();
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        IOrderDetailPresenter iOrderDetailPresenter = new IOrderDetailPresenter(this);
        iCashPayPresenter = new ICashPayPresenter(this);
        iOrderDetailPresenter.getOrderDetail();
        iOrderDetailPresenter.getFirstDelivery();
        iOrderDetailPresenter.getFourDelivery();
        iOrderDetailPresenter.getSixDelivery();
        iOrderDetailPresenter.getSecondDelivery();
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendBottleOrdersDetailPayActivity.this, ExchangeReviewActivity.class);
                intent.putExtra(Constants.ORDER_ID, orderId);
                startActivity(intent);
            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean orderDetailBean) {

        String floor = orderDetailBean.getData().getFloor() + "";
        String room = orderDetailBean.getData().getRoomNum();
        String address = orderDetailBean.getData().getAddress();
        tvLocation.setText(new StringBuilder().append(address).append(floor).append("层").append(room).append("房").append(orderDetailBean.getData().getIsDelivery() == 1 ? "(自提单)" : "(配送单)").toString());
        int isElevator = orderDetailBean.getData().getIsElevator();
        tvElevator.setText(isElevator == 1 ? "(有电梯)" : "(无电梯)");
        tvUser.setText(new StringBuilder().append(orderDetailBean.getData().getBuyer()).append(orderDetailBean.getData().getSex() == 0 ? "(先生)" : "(女士)").toString());
        tvTel.setText(orderDetailBean.getData().getMobile());
        tvDay.setText(new StringBuilder().append(orderDetailBean.getData().getAppointmentTime()).toString());
        tvComment.setText(new StringBuilder().append("内部备注：").append(orderDetailBean.getData().getRemarks()).toString());
        orderId = orderDetailBean.getData().getOrderId() + "";
        tvOrderId.setText(orderId);
        tvDispatchOrderTime.setText(new StringBuilder().append("下单时间：").append(orderDetailBean.getData().getCreateDate()).toString());
        tvDispatchBottleTime.setText(new StringBuilder().append("发货时间：").append(orderDetailBean.getData().getSendTime()));
        //气价
        double fiveGasFee = orderDetailBean.getData().getFiveBottleCount() * orderDetailBean.getData().getFiveGasFee();
        tvFiveGas.setText(String.valueOf(fiveGasFee));
        double fifteenGasFee = orderDetailBean.getData().getFifteenBottleCount() * orderDetailBean.getData().getFifteenGasFee();
        tvFifteenGas.setText(String.valueOf(fifteenGasFee));
        double fiftyGasFee = orderDetailBean.getData().getFiftyBottleCount() * orderDetailBean.getData().getFiftyGasFee();
        tvFiftyGas.setText(String.valueOf(fiftyGasFee));
        double totalGas = fiveGasFee + fifteenGasFee + fiftyGasFee;
        tvGasFee.setText(String.valueOf(totalGas));

        //押金
        int remain5 = orderDetailBean.getData().getFiveBottleCount() - orderDetailBean.getData().getReFiveBottleCount();
        if (remain5 == 0) {
            llFiveDeposite.setVisibility(View.GONE);
        } else {
            llFiveDeposite.setVisibility(View.VISIBLE);
            fiveTotalDeposite = remain5 * orderDetailBean.getData().getFiveDepositFee();
            tvFiveDeposite.setText(String.valueOf(fiveTotalDeposite));
        }
        int remain15 = orderDetailBean.getData().getFifteenBottleCount() - orderDetailBean.getData().getReFifteenBottleCount();
        if (remain15 == 0) {
            llFifteenDeposite.setVisibility(View.GONE);
        } else {
            llFifteenDeposite.setVisibility(View.VISIBLE);
            fifteenteenTotalDeposite = remain15 * orderDetailBean.getData().getFifteenDepositFee();
            tvFifteenDeposite.setText(String.valueOf(fifteenteenTotalDeposite));
        }
        int remain50 = orderDetailBean.getData().getFiftyBottleCount() - orderDetailBean.getData().getReFiftyBottleCount();
        if (remain50 == 0) {
            llFiftyDeposite.setVisibility(View.GONE);
        } else {
            llFiftyDeposite.setVisibility(View.VISIBLE);
            fiftyTotalDeposite = remain50 * orderDetailBean.getData().getFiftyDepositFee();
            tvFiftyDeposite.setText(String.valueOf(fiftyTotalDeposite));
        }
        double totalDeposite = fiveTotalDeposite + fifteenteenTotalDeposite + fiftyTotalDeposite;
        if (totalDeposite == 0) {
            llDepositFare.setVisibility(View.GONE);
        } else {
            llDepositFare.setVisibility(View.VISIBLE);
            tvDepositeFee.setText(String.valueOf(totalDeposite));
        }
        //运费
        double fiveDeliveryFee;
        double fifteenDeliveryFee;
        double fiftyDeliveryFee;
        double totalFiveDelivery;
        double totalFifteenDelivery;
        double totalFiftyDelivery;
        double totalDeliveryfare;
        if (orderDetailBean.getData().getIsDelivery() == 1) {
            totalDeliveryfare = 0;
        } else {
            fiveDeliveryFee = orderDetailBean.getData().getFiveDeliveryFee();
            fifteenDeliveryFee = orderDetailBean.getData().getFifteenDeliveryFee();
            fiftyDeliveryFee = orderDetailBean.getData().getFiftyDeliveryFee();
            totalFiveDelivery = orderDetailBean.getData().getFiveBottleCount() * fiveDeliveryFee;
            totalFifteenDelivery = orderDetailBean.getData().getFifteenBottleCount() * fifteenDeliveryFee;
            totalFiftyDelivery = orderDetailBean.getData().getFiftyBottleCount() * fiftyDeliveryFee;
            totalDeliveryfare = totalFiveDelivery + totalFifteenDelivery + totalFiftyDelivery;
        }
        tvDeliverFee.setText(String.valueOf(totalDeliveryfare));
        double exchange = orderDetailBean.getData().getRetrieveAmount();
        tvDiscount.setText(String.valueOf(exchange));
        totalfare = totalGas + totalDeposite + totalDeliveryfare - exchange;
        if (((totalfare + "").length()) > 6) {
            tvTotal.setText((totalfare + "").substring(0, 5));
        } else {
            tvTotal.setText((totalfare + ""));
        }
        layoutWeiChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderType = "1";
                payType = "12";
                Intent intent = new Intent(SendBottleOrdersDetailPayActivity.this, QRCodeReceiptActivity.class);
                intent.putExtra(Constants.ORDER_ID, orderId);
                intent.putExtra(Constants.URL_PARAMS_ORDER_TYPE, orderType);
                intent.putExtra(Constants.PAY_AMOUNT, totalfare + "");
                intent.putExtra(Constants.PAY_TYPE, payType);
                startActivity(intent);
                finish();
            }
        });
        lldeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGetFirstDeliverySuccess || !isGetFourDeliverySuccess || !isGetSixDeliverySuccess || !isGetSecondDeliverySuccess) {
                    PopUtil.toastInBottom("正在获取运费信息，请稍后再试");
                } /*else if (deliverFareDialog == null) {
                    deliverFareDialog = new DeliverFareDialog(SendBottleOrdersDetailPayActivity.this, map);
                    deliverFareDialog.show();
                } else if (!deliverFareDialog.isShowing()) {
                    deliverFareDialog.show();
                }*/else {
                    PopUtil.showWebViewDialog(SendBottleOrdersDetailPayActivity.this, Constants.PEI_SONG_SHUO_MING);
                }
            }
        });
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.showComfirmDialog(SendBottleOrdersDetailPayActivity.this, "拨打电话", "请确认是否要拨打电话" + tvTel.getText().toString(), "取消", "确认", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PermissionUtils.requestPermissions(SendBottleOrdersDetailPayActivity.this, SendBottleOrdersDetailPayActivity.this, Permission.CALL_PHONE);
                    }
                }, true);

            }
        });
        layoutAliPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*      PopUtil.showComfirmDialog(SendBottleOrdersDetailPayActivity.this, "现金代付", "是否确认现金代付", "", "", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iCashPayPresenter.cashPay();
                    }
                });*/
                payDialog(SendBottleOrdersDetailPayActivity.this, "选择支付方式", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iCashPayPresenter.cashPay();
                    }
                });
            }
        });
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getPayChannel() {
        return channel;
    }

    @Override
    public String getMsbUserId() {
        return 4 + "";
    }

    @Override
    public void onCashPaySuceess(EmpPayBean empPayBean) {

        String charge = "";
        try {
            charge = empPayBean.getData().getCharge();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(this.toString(),
                "数据=" + charge);
        Pingpp.createPayment(SendBottleOrdersDetailPayActivity.this, charge);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            //if (requestCode == REQUEST_CODE_PAYMENT){
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                String errorMsg = data.getStringExtra("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                    PopUtil.toastInBottom("error_msg=="+errorMsg+"extra_msg=="+extraMsg);
                if (TextUtils.equals(result, "success")) {
                    Intent intent = new Intent(this, SendBottleOrdersDetailFinishActivity.class);
                    intent.putExtra(Constants.ORDER_ID, orderId);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    @Override
    public void onGetFourDeliverySuccess(DeliveryBean bean) {
        String four5 = bean.getData().getDeliveryFee().getFiveDeliveryFee() + "";
        String four15 = bean.getData().getDeliveryFee().getFifteenDeliveryFee() + "";
        String four50 = bean.getData().getDeliveryFee().getFiftyDeliveryFee() + "";
        map.put("four5", four5);
        map.put("four15", four15);
        map.put("four50", four50);
        isGetFourDeliverySuccess = true;
    }

    @Override
    public void onGetSixDeliverySuccess(DeliveryBean bean) {
        String six5 = bean.getData().getDeliveryFee().getFiveDeliveryFee() + "";
        String six15 = bean.getData().getDeliveryFee().getFifteenDeliveryFee() + "";
        String six50 = bean.getData().getDeliveryFee().getFiftyDeliveryFee() + "";
        map.put("six5", six5);
        map.put("six15", six15);
        map.put("six50", six50);
        isGetSixDeliverySuccess = true;
    }

    @Override
    public void onGetFirstDeliverySuccess(DeliveryBean bean) {
        String first5 = bean.getData().getDeliveryFee().getFiveDeliveryFee() + "";
        String first15 = bean.getData().getDeliveryFee().getFifteenDeliveryFee() + "";
        String first50 = bean.getData().getDeliveryFee().getFiftyDeliveryFee() + "";
        map.put("first5", first5);
        map.put("first15", first15);
        map.put("first50", first50);
        isGetFirstDeliverySuccess = true;
    }

    @Override
    public void onGetSecondDeliverySuccess(DeliveryBean bean) {
        String second5 = bean.getData().getDeliveryFee().getFiveDeliveryFee() + "";
        String second15 = bean.getData().getDeliveryFee().getFifteenDeliveryFee() + "";
        String second50 = bean.getData().getDeliveryFee().getFiftyDeliveryFee() + "";
        map.put("second5", second5);
        map.put("second15", second15);
        map.put("second50", second50);
        isGetSecondDeliverySuccess = true;
    }


    @Override
    public void onBackFromSettingPage() {

    }

    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        PopUtil.toastInBottom("请允许LPG拨打电话");
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvTel.getText().toString()));
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PayFinishBean event) {
       finish();
    }
    private void payDialog(Context mContext, String title,
                           final View.OnClickListener onCancel,
                           final View.OnClickListener onOK) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(
                R.layout.dialog_pay, null);
        final AlertDialog tel_dialog = new AlertDialog.Builder(mContext).create();

        TextView tvTitle = (TextView) layout.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.INVISIBLE);
        }
        tel_dialog.show();
        tel_dialog.getWindow().setContentView(layout);
        TextView btnCancel = (TextView) layout.findViewById(R.id.dialog_btn_cancel);
        TextView btnOk = (TextView) layout.findViewById(R.id.dialog_btn_ok);
        RadioGroup radioGroup = (RadioGroup) layout.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectRadioButton = (RadioButton) layout.findViewById(checkedId);
                if (TextUtils.equals(selectRadioButton.getText(), "支付宝")) {
                    channel = 1 + "";
                } else if (TextUtils.equals(selectRadioButton.getText(), "微信支付")) {
                    channel = 5 + "";
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onCancel != null)
                    onCancel.onClick(v);

            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onOK != null)
                    onOK.onClick(v);

            }
        });
    }
}