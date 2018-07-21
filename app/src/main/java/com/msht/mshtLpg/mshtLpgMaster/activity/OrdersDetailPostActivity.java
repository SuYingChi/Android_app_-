package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    private String orderId;
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
    private List<ExchangeRclBean> exchangeList = new ArrayList<ExchangeRclBean>();
    private DeliveryBean deliveryBean;
    private String siteId ="";
    private int orderFiveNum;
    private int orderFifteenNum;
    private int orderFiftyNum;
    private String isDelivery;
    private IOrderDetailPostPresenter iPostPresenter;
    private String emptyFive;
    private String emptyFifteen;
    private String emptyFifyt;
    private Unbinder unbinder;

    //更规范的写法是写个handler在子线程执行完后，调度其他子线程的开启，后边再优化
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
       unbinder =  ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //从扫码界面跳转过来
        if (bundle != null) {
            orderId = bundle.getString(Constants.ORDER_ID);
            heavyBottleList = (List<VerifyBottleBean>) bundle.getSerializable(Constants.HEAVY_BOTTLE_LIST);
            emptyBottleList = (List<VerifyBottleBean>) bundle.getSerializable(Constants.EMPTY_BOTTLE_LIST);
            //此时订单状态还只是待验瓶
            iOrderDetailPresenter = new IOrderDetailPresenter(this);
            iGasAndDepositPresenter = new IGasAndDepositPresenter(this);
            iDeliveryPresenter = new IDeliveryPresenter(this);
            iPostPresenter = new IOrderDetailPostPresenter(this);
            iOrderDetailPresenter.getOrderDetail();
            emptyFive = BottleCaculteUtil.getBottleNum(emptyBottleList, 5)+"";
            emptyFifteen = BottleCaculteUtil.getBottleNum(emptyBottleList, 15)+"";
            emptyFifyt = BottleCaculteUtil.getBottleNum(emptyBottleList, 50)+"";
        }

        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        siteId = bean.getData().getSiteId()+"";
        floor = bean.getData().getFloor();
        orderId = bean.getData().getOrderId()+"";
        room = bean.getData().getRoomNum();
        isElevator = bean.getData().getIsElevator();
        orderFiveNum= bean.getData().getFiveBottleCount();
        orderFifteenNum= bean.getData().getFifteenBottleCount();
        orderFiftyNum= bean.getData().getFiftyBottleCount();
        isDelivery = bean.getData().getIsDelivery()+"";
        remain5 = orderFiveNum- BottleCaculteUtil.getBottleNum(emptyBottleList, 5);
        remain15 = orderFiveNum- BottleCaculteUtil.getBottleNum(emptyBottleList, 15);
        remain50 = orderFiveNum- BottleCaculteUtil.getBottleNum(emptyBottleList, 50);
        exchangeFee = 0;
        tvDiscount.setText(exchangeFee+"");
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
        tvLocation.setText(orderDetailBean.getData().getAddress());
        tvFloor.setText(floor + "层");
        tvRoom.setText(room + "房");
        tvElevator.setText(isElevator == 1 ? "(有电梯)" : "(无电梯)");
        tvUser.setText(new StringBuilder().append(orderDetailBean.getData().getBuyer()).append(orderDetailBean.getData().getSex() == 1 ? "(先生)" : "(女士)").toString());
        tvTel.setText(orderDetailBean.getData().getMobile());
        tvDay.setText(orderDetailBean.getData().getCreateDate());
        tvTime.setText(orderDetailBean.getData().getAppointmentTime());
        if(TextUtils.isEmpty(orderDetailBean.getData().getRemarks())){
            tvComment.setVisibility(View.GONE);
        }else {
            tvComment.setVisibility(View.VISIBLE);
            tvComment.setText(orderDetailBean.getData().getRemarks());
        }
        tvOrderId.setText(orderId );
        tvDispatchOrderTime.setText(orderDetailBean.getData().getCreateDate());
        tvDispatchBottleTime.setText(orderDetailBean.getData().getAppointmentTime());
        iGasAndDepositPresenter.getGasAndDeposit();
    }

    @Override
    public void onPostOrdersSuccess(ComfirmOrdersBean bean) {
        Intent intent = new Intent(this, OrdersDetailPayActivity.class);
        intent.putExtra(Constants.ORDER_ID, orderId);
        startActivity(intent);
    }

    @Override
    public String getOrderId() {
        return orderId ;
    }

    @Override
    public String getIsDelivery() {
        return isDelivery;
    }

    @Override
    public String getReFiveBottleCount() {
        return emptyFive;
    }

    @Override
    public String getReFifteenBottleCount() {
        return emptyFifteen;
    }

    @Override
    public String getReFiftyBottleCount() {
        return emptyFifyt;
    }

    @Override
    public String getFiveBottleCount() {
        return orderFiveNum+"";
    }

    @Override
    public String getFifteenBottleCount() {
        return orderFifteenNum+"";
    }

    @Override
    public String getFiftyBottleCount() {
        return orderFiftyNum+"";
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
        return floor+"";
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
        if(exchangeList.size()!=0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < exchangeList.size(); i++) {
                int levelIndex = exchangeList.get(i).getmSelectBottleLevelIndex();
                int modelIndex = exchangeList.get(i).getSelectBottleModeIndex();
                int year = exchangeList.get(i).getSelectBottleYearsIndex();
                int weight = 0;
                int num = exchangeList.get(i).getBottleNum();
                switch (modelIndex) {
                    case 0:
                        weight = 5;
                        break;
                    case 1:
                        weight = 15;
                        break;
                    case 2:
                        weight = 50;
                        break;
                    default:
                        break;
                }
                String level = "";
                switch (levelIndex) {
                    case 0:
                        level = "A";
                        break;
                    case 1:
                        level = "B";
                        break;
                    case 2:
                        level = "C";
                        break;
                    case 3:
                        level = "D";
                        break;
                    default:
                        break;
                }
                if (i == 0) {
                    stringBuffer.append(weight).append(year).append(num).append(level);
                } else {
                    stringBuffer.append("|").append(weight).append(year).append(num).append(level);
                }
            }
            return stringBuffer.toString();
        }else{
            return "";
        }

    }

    @Override
    public void onGasAndDepositGetSuccess(GasAndDepositBean bean) {
        this.gasAndDepositBean = bean;
        fiveDeposite = bean.getData().getDepositPrice().getFiveDepositPrice();
        fifteenDeposite = bean.getData().getDepositPrice().getFifteenDepositPrice();
        fiftyDeposite = bean.getData().getDepositPrice().getFiftyDepositPrice();
        fiveGas = bean.getData().getGasPrice().getFivePrice();
        fifteenGas = bean.getData().getGasPrice().getFifteenPrice();
        fiftyGas = bean.getData().getGasPrice().getFiftyPrice();

        //气价
        fiveGasFee = orderFiveNum * fiveGas;
        tvFiveGas.setText(fiveGasFee + "");
        fifteenGasFee = orderFifteenNum * fifteenGas;
        tvFifteenGas.setText(fifteenGasFee + "");
        fiftyGasFee = orderFiftyNum * fiftyGas;
        tvFiftyGas.setText(fiftyGasFee + "");
        totalGas = fiveGasFee + fifteenGasFee + fiftyGasFee;

        totalfare += totalGas;
        tvCost.setText(totalfare + "");
        tvGasFee.setText(totalGas + "");

        //押金

        if (remain5 == 0) {
            llFiveDeposite.setVisibility(View.GONE);
        } else {
            llFiveDeposite.setVisibility(View.VISIBLE);
            fiveTotalDeposite = remain5 * fiveDeposite;
            tvFiveDeposite.setText(fiveTotalDeposite + "");
        }
        if (remain15 == 0) {
            llFifteenDeposite.setVisibility(View.GONE);
        } else {
            llFifteenDeposite.setVisibility(View.VISIBLE);
            fifteenteenTotalDeposite = remain15 * fifteenDeposite;
            tvFifteenDeposite.setText(fifteenteenTotalDeposite + "");
        }
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
        if (isElevator != 1) {
            iDeliveryPresenter.getDelivery();
        }
    }

    @Override
    public String getSiteId() {
        return siteId;
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


        tvPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPostPresenter.postOrders();
            }
        });
    }

    @Override
    public String getFloors() {
        return floor+"";
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