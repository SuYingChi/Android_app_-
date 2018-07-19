package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ComfirmOrdersBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.DeliveryBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ExchangeRclBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.GasAndDepositBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IDeliveryPresenter;
import com.msht.mshtLpg.mshtLpgMaster.Present.IGasAndDepositPresenter;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPostPresenter;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.BottleCaculteUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IDeliveryView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailPostView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrdesDespositView;
import com.yanzhenjie.permission.Permission;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersDetailPostActivity extends BaseActivity implements IOrderDetailView, IOrdesDespositView, IDeliveryView, IOrderDetailPostView,PermissionUtils.PermissionRequestFinishListener {


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
    @BindView(R.id.post_orders_v2_cost)
    TextView tvCost;
    @BindView(R.id.post_orders_v2_btn)
    TextView tvPostBtn;
    @BindView(R.id.ll_discount)
    LinearLayout llDiscount;
    @BindView(R.id.ll_orders_detail_command_topbar_client_info)
    LinearLayout client_info;
    @BindView(R.id.ll_deposit_fare)
    LinearLayout llDepositFare;
    @BindView(R.id.floor)
    TextView tvFloor;
    @BindView(R.id.room)
    TextView tvRoom;
    @BindView(R.id.comman_topbar_call_phone_btn)
    LinearLayout callBtn;

    private int exchangeFee;
    private int floor;
    private int isElevator;
    private List<VerifyBottleBean> heavyBottleList;
    private IOrderDetailPresenter iOrderDetailPresenter;
    private List<VerifyBottleBean> emptyBottleList;
    private int orderId;
    private GasAndDepositBean gasAndDepositBean;
    private IGasAndDepositPresenter iGasAndDepositPresenter;
    private int fiveDeliveryFee;
    private int fifteenDeliveryFee;
    private int fiftyDeliveryFee;
    private double fiveDeposite;
    private double fifteenDeposite;
    private double fiftyDeposite;
    private IDeliveryPresenter iDeliveryPresenter;
    private double fiveGasFee;
    private double fifteenGasFee;
    private double fiftyGasFee;
    private double totalDeliveryfare;
    private double fiveGas;
    private double fifteenGas;
    private double fiftyGas;
    private double fiveTotalDeposite;
    private double fifteenteenTotalDeposite;
    private double fiftyTotalDeposite;
    private double totalDeposite;
    private double totalGas;
    private double totalfare;
    private double totalFiveDelivery;
    private double totalFifteenDelivery;
    private double totalFiftyDelivery;
    private int remain5;
    private int remain15;
    private int remain50;
    private String room;
  /*  private Handler handler = new MyHandler(this);
    private final int TOTAL_GAS = 101;
    private final int TOTAL_DEPOSITE = 102;
    private final int TOTAL_DELIVERY = 103;
    private final int TOTAL_EXCHANGE = 104;*/
    private OrderDetailBean orderDetailBean;
    private List<ExchangeRclBean> exchangeList;
    private DeliveryBean deliveryBean;


    /*@SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {
        private WeakReference<OrdersDetailPostActivity> ref;

        public MyHandler(OrdersDetailPostActivity activity) {
            if (activity != null) {
                ref = new WeakReference<OrdersDetailPostActivity>(activity);
            }
        }

        @Override
        public void handleMessage(Message msg) {
            if (ref == null) {
                return;
            }
            OrdersDetailPostActivity v = ref.get();
            if (v == null) {
                return;
            }
            switch (msg.what) {
                case TOTAL_GAS:
                    totalfare += totalGas;
                    tvCost.setText(totalfare + "");
                    break;
                case TOTAL_DELIVERY:
                    totalfare += totalDeliveryfare;
                    tvCost.setText(totalfare + "");
                    break;
                case TOTAL_DEPOSITE:
                    totalfare += totalDeposite;
                    tvCost.setText(totalfare + "");
                    break;
                case TOTAL_EXCHANGE:
                    totalfare -= exchangeFee;
                    tvCost.setText(totalfare + "");
                    break;
                default:
                    break;
            }

        }
    }*/

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_orders_v2);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //从扫码界面跳转过来
        if (bundle != null) {
            orderId = bundle.getInt(Constants.ORDER_ID, 0);
            heavyBottleList = (List<VerifyBottleBean>) bundle.getSerializable(Constants.HEAVY_BOTTLE_LIST);
            emptyBottleList = (List<VerifyBottleBean>) intent.getSerializableExtra(Constants.EMPTY_BOTTLE_LIST);
            //此时订单状态还只是待验瓶
            iOrderDetailPresenter = new IOrderDetailPresenter(this);
            iGasAndDepositPresenter = new IGasAndDepositPresenter(this);
            iDeliveryPresenter = new IDeliveryPresenter(this);

            iOrderDetailPresenter.getOrderDetail();
            iGasAndDepositPresenter.getGasAndDeposit();
            iDeliveryPresenter.getDelivery();
        }

        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //startActivityforresult 自有产权置换点击事件
        llDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OrdersDetailPostActivity.this, ExchangeSteelBottleActivity.class);
                intent.putExtra(Constants.REMAIN_FIVE_NUM, remain5);
                intent.putExtra(Constants.REMAIN_FIFTEEN_NUM, remain15);
                intent.putExtra(Constants.REMAIN_FIFTY_NUM, remain50);
                startActivityForResult(intent, Constants.EXCHANGE_EMPTY_BOTTLE_REQUEST_CODE);

            }
        });
        tvPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IOrderDetailPostPresenter(OrdersDetailPostActivity.this).postOrders();
            }
        });
        client_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrdersDetailPostActivity.this, EditLocationActivity.class);
                intent.putExtra(Constants.FLOOR, floor);
                intent.putExtra(Constants.IS_ELEVATOR, isElevator);
                startActivityForResult(intent, Constants.EDIT_FLOOR_REQUEST_CODE);

            }
        });
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.requestPermissions(OrdersDetailPostActivity.this, OrdersDetailPostActivity.this, Permission.CALL_PHONE);
            }
        });

    }

    private void initViewOnGetOrderDetailBeanSuccess() {
            tvLocation.setText(orderDetailBean.getData().getAddress());
            floor = orderDetailBean.getData().getFloor();
            tvFloor.setText(floor + "层");
            room = orderDetailBean.getData().getRoomNum();
            tvRoom.setText(room + "房");
            isElevator = orderDetailBean.getData().getIsElevator();
            tvElevator.setText(isElevator == 1 ? "(有电梯)" : "(无电梯)");
            tvUser.setText(new StringBuilder().append(orderDetailBean.getData().getBuyer()).append(orderDetailBean.getData().getSex() == 1 ? "(先生)" : "(女士)").toString());
            tvTel.setText(orderDetailBean.getData().getMobile());
            tvDay.setText(orderDetailBean.getData().getCreateDate());
            tvTime.setText(orderDetailBean.getData().getAppointmentTime());
            tvComment.setText(orderDetailBean.getData().getRemarks());
            orderId = orderDetailBean.getData().getOrderId();
            tvOrderId.setText(orderId + "");
            tvDispatchOrderTime.setText(orderDetailBean.getData().getCreateDate());
            tvDispatchBottleTime.setText(orderDetailBean.getData().getAppointmentTime());
        }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.EXCHANGE_EMPTY_BOTTLE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                exchangeFee = data.getIntExtra(Constants.EXCHANGE_FEE, 0);
                if(exchangeFee==0){
                    llDiscount.setVisibility(View.GONE);
                }else {
                    tvDiscount.setText(exchangeFee+"");
                }
                tvDiscount.setText(exchangeFee + "");
                totalfare -= exchangeFee;
                tvCost.setText(totalfare + "");
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    exchangeList = (List<ExchangeRclBean>) bundle.getSerializable("exchangelist");
                }
            }
        }
        if (requestCode == Constants.EDIT_FLOOR_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                floor = data.getIntExtra(Constants.FLOOR, 0);
                tvFloor.setText(floor + "层");
                isElevator = data.getIntExtra(Constants.IS_ELEVATOR, 0);
                tvElevator.setText(isElevator == 1 ? "(有电梯)" : "(无电梯)");
                if (isElevator != 1) {
                    iDeliveryPresenter.getDelivery();
                }

            }
        }

    }

    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean bean) {
        orderDetailBean = bean;
        initViewOnGetOrderDetailBeanSuccess();
    }

    @Override
    public void onPostOrdersSuccess(ComfirmOrdersBean bean) {
        Intent intent = new Intent(this, OrdersDetailPayActivity.class);
        intent.putExtra(Constants.ORDER_ID, orderId);
        startActivity(intent);
    }

    @Override
    public String getOrderId() {
        return orderId + "";
    }

    @Override
    public String getIsDelivery() {
        return orderDetailBean.getData().getIsDelivery()+"";
    }

    @Override
    public String getReFiveBottleCount() {
        return BottleCaculteUtil.getBottleNum(emptyBottleList, 5)+"";
    }

    @Override
    public String getReFifteenBottleCount() {
        return BottleCaculteUtil.getBottleNum(emptyBottleList, 15)+"";
    }

    @Override
    public String getReFiftyBottleCount() {
        return BottleCaculteUtil.getBottleNum(emptyBottleList, 50)+"";
    }

    @Override
    public String getFiveBottleCount() {
        return orderDetailBean.getData().getFiveBottleCount()+"";
    }

    @Override
    public String getFifteenBottleCount() {
        return orderDetailBean.getData().getFifteenBottleCount()+"";
    }

    @Override
    public String getFiftyBottleCount() {
        return orderDetailBean.getData().getFiftyBottleCount()+"";
    }

    @Override
    public String getDeliveryBottleIds() {
         StringBuffer sf = new StringBuffer();
        for(int i = 0; i< heavyBottleList.size(); i++){
            if(i==0){
                sf.append(heavyBottleList.get(i).getData().getId());
            }else {
                sf.append(",").append(heavyBottleList.get(i).getData().getId());
            }
        }
        return sf.toString();
    }

    @Override
    public String getFloor() {
        return orderDetailBean.getData().getFloor()+"";
    }

    @Override
    public String getRecycleBottleIds() {
        StringBuffer sf = new StringBuffer();
        for(int i = 0; i< emptyBottleList.size(); i++){
            if(i==0){
                sf.append(emptyBottleList.get(i).getData().getId());
            }else {
                sf.append(",").append(emptyBottleList.get(i).getData().getId());
            }
        }
        return sf.toString();
    }

    @Override
    public String getReplaceBottleStr() {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<exchangeList.size();i++){
           int level =  exchangeList.get(i).getmSelectBottleLevelIndex();
           int modelIndex = exchangeList.get(i).getSelectBottleModeIndex();
           int yearIndex = exchangeList.get(i).getSelectBottleYearsIndex();
           int weight= 0;
           String year = "";
           int num = exchangeList.get(i).getBottleNum();
           switch (modelIndex){
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
            switch (yearIndex){
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
            if(i==0){
                stringBuffer.append(weight).append(year).append(num).append(level);
            }else {
                stringBuffer.append("|").append(weight).append(year).append(num).append(level);
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public void onGasAndDepositGetSuccess(GasAndDepositBean bean) {
        this.gasAndDepositBean = bean;
        fiveDeposite = gasAndDepositBean.getData().getFiveDepositFee();
        fifteenDeposite = gasAndDepositBean.getData().getFifteenDepositFee();
        fiftyDeposite = gasAndDepositBean.getData().getFiftyDepositFee();
        fiveGas = gasAndDepositBean.getData().getFiveGasFee();
        fifteenGas = gasAndDepositBean.getData().getFifteenGasFee();
        fiftyGas = gasAndDepositBean.getData().getFiftyGasFee();

        //气价
        fiveGasFee = orderDetailBean.getData().getFiveBottleCount() * fiveGas;
        tvFiveGas.setText(fiveGasFee + "");
        fifteenGasFee = orderDetailBean.getData().getFifteenBottleCount() * fifteenGas;
        tvFifteenGas.setText(fifteenGasFee + "");
        fiftyGasFee = orderDetailBean.getData().getFiftyBottleCount() * fiftyGas;
        tvFiftyGas.setText(fiftyGasFee + "");
        totalGas = fiveGasFee + fifteenGasFee + fiftyGasFee;

        totalfare += totalGas;
        tvCost.setText(totalfare + "");
        tvGasFee.setText(totalGas + "");

        //押金
        remain5 = orderDetailBean.getData().getFiveBottleCount() - BottleCaculteUtil.getBottleNum(emptyBottleList, 5);
        BottleCaculteUtil.setRemian5(remain5);
        if (remain5 == 0) {
            llFiveDeposite.setVisibility(View.GONE);
        } else {
            llFiveDeposite.setVisibility(View.VISIBLE);
            fiveTotalDeposite = remain5 * fiveDeposite;
            tvFiveDeposite.setText(fiveTotalDeposite + "");
        }
        remain15 = orderDetailBean.getData().getFiveBottleCount() - BottleCaculteUtil.getBottleNum(emptyBottleList, 15);
        if (remain15 == 0) {
            llFifteenDeposite.setVisibility(View.GONE);
            BottleCaculteUtil.setRemian15(remain15);
        } else {
            llFifteenDeposite.setVisibility(View.VISIBLE);
            fifteenteenTotalDeposite = remain15 * fifteenDeposite;
            tvFifteenDeposite.setText(fifteenteenTotalDeposite + "");
        }
        remain50 = orderDetailBean.getData().getFiveBottleCount() - BottleCaculteUtil.getBottleNum(emptyBottleList, 50);
        BottleCaculteUtil.setRemian50(remain50);
        if (remain50 == 0) {
            llFiftyDeposite.setVisibility(View.GONE);
        } else {
            llFiftyDeposite.setVisibility(View.VISIBLE);
            fiftyTotalDeposite = remain50 * fiftyDeposite;
            tvFiftyGas.setText(fiftyTotalDeposite + "");
        }
        totalDeposite = fiveTotalDeposite + fifteenteenTotalDeposite + fiftyTotalDeposite;
        if (totalDeposite == 0) {
            llDepositFare.setVisibility(View.GONE);
        } else {
            llDepositFare.setVisibility(View.VISIBLE);
            totalfare += totalDeposite;
            tvCost.setText(totalfare + "");
            tvDepositeFee.setText(totalDeposite + "");
        }

    }

    @Override
    public String getSiteId() {
        return gasAndDepositBean.getData().getSiteId()+"";
    }


    @Override
    public void onGetDeliverySuccess(DeliveryBean bean) {
            this.deliveryBean = bean;
            fiveDeliveryFee = bean.getData().getDeliveryFee().getFiveDeliveryFee();
            fifteenDeliveryFee = bean.getData().getDeliveryFee().getFifteenDeliveryFee();
            fiftyDeliveryFee = bean.getData().getDeliveryFee().getFiftyDeliveryFee();
            totalFiveDelivery = orderDetailBean.getData().getFiveBottleCount() * fiveDeliveryFee;
            totalFifteenDelivery = orderDetailBean.getData().getFifteenBottleCount() * fifteenDeliveryFee;
            totalFiftyDelivery = orderDetailBean.getData().getFiftyBottleCount() * fiftyDeliveryFee;
            if(isElevator==1){
                totalDeliveryfare  = 0;
            }else{
                totalDeliveryfare = totalFiveDelivery + totalFifteenDelivery + totalFiftyDelivery;
            }

        totalfare += totalDeliveryfare;
        tvCost.setText(totalfare + "");
            tvDeliverFee.setText(totalDeliveryfare + "");

    }

    @Override
    public String getFloors() {
        return orderDetailBean.getData().getFloor()+"";
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
