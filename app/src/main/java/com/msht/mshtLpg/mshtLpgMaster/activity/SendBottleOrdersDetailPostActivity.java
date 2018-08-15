package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.msht.mshtLpg.mshtLpgMaster.customView.DeliverFareDialog;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.BottleCaculteUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IDeliveryView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailPostView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SendBottleOrdersDetailPostActivity extends BaseActivity implements IOrderDetailView, /*IOrdesDespositView,*/ IDeliveryView, IOrderDetailPostView, PermissionUtils.PermissionRequestFinishListener {


    private static final String TAG = "OrdersDetailPost";
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
    @BindView(R.id.comman_topbar_call_phone_btn)
    LinearLayout callBtn;
    @BindView(R.id.ll_deliver_fare)
    LinearLayout lldeliver;
    private double exchangeFee;
    private String floor;
    private String isElevator;
    private List<VerifyBottleBean> heavyBottleList;
    private IOrderDetailPresenter iOrderDetailPresenter;
    private List<VerifyBottleBean> emptyBottleList;
    private String orderId;
    private GasAndDepositBean gasAndDepositBean;
    private IGasAndDepositPresenter iGasAndDepositPresenter;
    private double fiveDeliveryFee;
    private double fifteenDeliveryFee;
    private double fiftyDeliveryFee;
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
    private double totalfare = 0;
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
    private int orderFiveNum;
    private int orderFifteenNum;
    private int orderFiftyNum;
    private String isDelivery;
    private IOrderDetailPostPresenter iPostPresenter;
    private String emptyFive;
    private String emptyFifteen;
    private String emptyFifyt;
    private Unbinder unbinder;
    private DeliverFareDialog deliverFareDialog;
    private Map<String,String> map= new HashMap<String,String>();
    private boolean isGetFourDeliverySuccess = false;
    private boolean isGetSixDeliverySuccess = false;
    private boolean isGetFirstDeliverySuccess  =false;
    private String address;
    private boolean isGetSecondDeliverySuccess = false;
    //更规范的写法是写个handler在子线程执行完后，调度其他子线程的开启，后边再优化
    /*@SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {
        private WeakReference<SendBottleOrdersDetailPostActivity> ref;

        public MyHandler(SendBottleOrdersDetailPostActivity activity) {
            if (activity != null) {
                ref = new WeakReference<SendBottleOrdersDetailPostActivity>(activity);
            }
        }

        @Override
        public void handleMessage(Message msg) {
            if (ref == null) {
                return;
            }
            SendBottleOrdersDetailPostActivity v = ref.get();
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
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //从扫码界面跳转过来
        if (bundle != null) {
            orderId = bundle.getString(Constants.ORDER_ID);
            heavyBottleList = (List<VerifyBottleBean>) bundle.getSerializable(Constants.HEAVY_BOTTLE_LIST);
            emptyBottleList = (List<VerifyBottleBean>) bundle.getSerializable(Constants.EMPTY_BOTTLE_LIST);
            //此时订单状态还只是待验瓶
            iOrderDetailPresenter = new IOrderDetailPresenter(this);
           // iGasAndDepositPresenter = new IGasAndDepositPresenter(this);
            iDeliveryPresenter = new IDeliveryPresenter(this);
            iPostPresenter = new IOrderDetailPostPresenter(this);

            emptyFive = BottleCaculteUtil.getBottleNum(emptyBottleList, 5) + "";
            emptyFifteen = BottleCaculteUtil.getBottleNum(emptyBottleList, 15) + "";
            emptyFifyt = BottleCaculteUtil.getBottleNum(emptyBottleList, 50) + "";

            iOrderDetailPresenter.getOrderDetail();
            iOrderDetailPresenter.getFirstDelivery();
            iOrderDetailPresenter.getFourDelivery();
            iOrderDetailPresenter.getSixDelivery();
            iOrderDetailPresenter.getSecondDelivery();
        }

        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.showTipsDialog(SendBottleOrdersDetailPostActivity.this, "放弃订单", "确认放弃提交该订单", "取消", "确认", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.EXCHANGE_EMPTY_BOTTLE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                exchangeFee = data.getDoubleExtra(Constants.EXCHANGE_FEE, 0);
                tvDiscount.setText(exchangeFee + "");
                tvDiscount.setText(exchangeFee + "");
                totalfare -= exchangeFee;
                if (((totalfare + "").length()) > 6) {
                    tvCost.setText((totalfare + "").substring(0, 5));
                } else {
                    tvCost.setText((totalfare + ""));
                }
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    exchangeList = (List<ExchangeRclBean>) bundle.getSerializable("exchangelist");
                }
            }
        } else if (requestCode == Constants.EDIT_FLOOR_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                floor = data.getStringExtra(Constants.FLOOR);
                tvLocation.setText(new StringBuilder().append(address).append(floor).append("层").append(room).append("房").toString());
                isElevator = data.getStringExtra(Constants.IS_ELEVATOR);
                tvElevator.setText("1".equals(isElevator) ? "(有电梯)" : "(无电梯)");
                if(Integer.valueOf(isDelivery) == 0){
                    if (!"1".equals(isElevator)) {
                        iDeliveryPresenter.getDelivery();
                    } else {
                        iDeliveryPresenter.getElevatorDelivery();
                    }
                }
            }
        }

    }

    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean bean) {
        orderDetailBean = bean;
        floor = bean.getData().getFloor() + "";
        orderId = bean.getData().getOrderId() + "";
        address = bean.getData().getAddress();
        room = bean.getData().getRoomNum();
        isElevator = bean.getData().getIsElevator() + "";
        orderFiveNum = bean.getData().getFiveBottleCount();
        orderFifteenNum = bean.getData().getFifteenBottleCount();
        orderFiftyNum = bean.getData().getFiftyBottleCount();
        isDelivery = bean.getData().getIsDelivery() + "";
        remain5 = orderFiveNum - BottleCaculteUtil.getBottleNum(emptyBottleList, 5);
        remain15 = orderFifteenNum - BottleCaculteUtil.getBottleNum(emptyBottleList, 15);
        remain50 = orderFiftyNum - BottleCaculteUtil.getBottleNum(emptyBottleList, 50);
        exchangeFee = 0;
        tvDiscount.setText(exchangeFee + "");
        //startActivityforresult 自有产权置换点击事件
        llDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remain15 > 0) {
                    totalfare+=exchangeFee;
                    exchangeFee=0;
                    if (((totalfare + "").length()) > 6) {
                        tvCost.setText((totalfare + "").substring(0, 5));
                    } else {
                        tvCost.setText((totalfare + ""));
                    }
                    Intent intent = new Intent(SendBottleOrdersDetailPostActivity.this, ExchangeSteelBottleActivity.class);
                    intent.putExtra(Constants.REMAIN_FIVE_NUM, remain5);
                    intent.putExtra(Constants.REMAIN_FIFTEEN_NUM, remain15);
                    intent.putExtra(Constants.REMAIN_FIFTY_NUM, remain50);
                    startActivityForResult(intent, Constants.EXCHANGE_EMPTY_BOTTLE_REQUEST_CODE);
                } else {
                    PopUtil.toastInBottom("只能置换15KG空瓶，暂不支持置换5KG和50KG空瓶");
                }


            }
        });
        client_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalfare -= totalDeliveryfare;
                Intent intent = new Intent(SendBottleOrdersDetailPostActivity.this, EditLocationActivity.class);
                intent.putExtra(Constants.FLOOR, floor);
                intent.putExtra(Constants.IS_ELEVATOR, isElevator);
                startActivityForResult(intent, Constants.EDIT_FLOOR_REQUEST_CODE);

            }
        });
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.showTipsDialog(SendBottleOrdersDetailPostActivity.this, "拨打电话", "请确认是否要拨打电话" + tvTel.getText().toString(), "取消", "确认", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PermissionUtils.requestPermissions(SendBottleOrdersDetailPostActivity.this,SendBottleOrdersDetailPostActivity.this, Permission.CALL_PHONE);
                    }
                });

            }
        });
        tvLocation.setText(new StringBuilder().append(address).append(floor).append("层").append(room).append("房").append(bean.getData().getIsDelivery()== 1?"(自提单)":"(配送单)").toString());
        tvElevator.setText("1".equals(isElevator) ? "(有电梯)" : "(无电梯)");
        tvUser.setText(new StringBuilder().append(orderDetailBean.getData().getBuyer()).append(orderDetailBean.getData().getSex() == 0 ? "(先生)" : "(女士)").toString());
        tvTel.setText(orderDetailBean.getData().getMobile());
        tvDay.setText(orderDetailBean.getData().getCreateDate());
        tvTime.setText(orderDetailBean.getData().getAppointmentTime());
        if (TextUtils.isEmpty(orderDetailBean.getData().getRemarks())) {
            tvComment.setVisibility(View.GONE);
        } else {
            tvComment.setVisibility(View.VISIBLE);
            tvComment.setText(orderDetailBean.getData().getRemarks());
        }
        tvOrderId.setText(orderId);
        tvDispatchOrderTime.setText(orderDetailBean.getData().getCreateDate());
        tvDispatchBottleTime.setText(orderDetailBean.getData().getAppointmentTime());
        //iGasAndDepositPresenter.getGasAndDeposit();


        fiveDeposite = bean.getData().getFiveDepositFee();
        fifteenDeposite = bean.getData().getFifteenDepositFee();
        fiftyDeposite = bean.getData().getFiftyDepositFee();
        fiveGas = bean.getData().getFiveGasFee();
        fifteenGas = bean.getData().getFifteenGasFee();
        fiftyGas = bean.getData().getFiftyGasFee();

        //气价
        fiveGasFee = orderFiveNum * fiveGas;
        tvFiveGas.setText(fiveGasFee + "");
        fifteenGasFee = orderFifteenNum * fifteenGas;
        tvFifteenGas.setText(fifteenGasFee + "");
        fiftyGasFee = orderFiftyNum * fiftyGas;
        tvFiftyGas.setText(fiftyGasFee + "");
        totalGas = fiveGasFee + fifteenGasFee + fiftyGasFee;

       /* totalfare += totalGas;
        if (((totalfare + "").length()) > 6) {
            tvCost.setText((totalfare + "").substring(0, 5));
        } else {
            tvCost.setText((totalfare + ""));
        }*/
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
            tvFiftyDeposite.setText(fiftyTotalDeposite + "");
        }
        totalDeposite = fiveTotalDeposite + fifteenteenTotalDeposite + fiftyTotalDeposite;
        if (totalDeposite == 0) {
            llDepositFare.setVisibility(View.GONE);
        } else {
            llDepositFare.setVisibility(View.VISIBLE);
            totalfare += totalDeposite;
            if (((totalfare + "").length()) > 6) {
                tvCost.setText((totalfare + "").substring(0, 5));
            } else {
                tvCost.setText((totalfare + ""));
            }
            tvDepositeFee.setText(totalDeposite + "");
        }

            fiveDeliveryFee = bean.getData().getFiveDeliveryFee();
            fifteenDeliveryFee = bean.getData().getFifteenDeliveryFee();
            fiftyDeliveryFee = bean.getData().getFiftyDeliveryFee();
            Log.d(TAG, "onGetOrdersDetailSuccess: fiveDeliveryFee="+fiveDeliveryFee+"fifteenDeliveryFee="+fifteenDeliveryFee+"fiftyDeliveryFee="+fiftyDeliveryFee);
            totalFiveDelivery = orderFiveNum * fiveDeliveryFee;
            totalFifteenDelivery = orderFifteenNum * fifteenDeliveryFee;
            totalFiftyDelivery = orderFiftyNum * fiftyDeliveryFee;
        Log.d(TAG, "onGetOrdersDetailSuccess: totalFiveDelivery="+totalFiveDelivery+"totalFifteenDelivery="+totalFifteenDelivery+"totalFiftyDelivery="+totalFiftyDelivery);
            totalDeliveryfare = totalFiveDelivery + totalFifteenDelivery + totalFiftyDelivery;

            totalfare = totalDeposite+totalGas+totalDeliveryfare;
            if (((totalfare + "").length()) > 6) {
                tvCost.setText((totalfare + "").substring(0, 5));
            } else {
                tvCost.setText((totalfare + ""));
            }
            tvDeliverFee.setText(totalDeliveryfare + "");

        tvPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.showTipsDialog(SendBottleOrdersDetailPostActivity.this, "提交送气订单", "确认已将钢瓶交付给客户并提交送气订单？", "取消", "确认", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iPostPresenter.postOrders();
                    }
                });
            }
        });
        lldeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isGetFirstDeliverySuccess||!isGetFourDeliverySuccess||!isGetSixDeliverySuccess||!isGetSecondDeliverySuccess){
                    PopUtil.toastInBottom("正在获取运费信息，请稍后再试");
                }
                else if(deliverFareDialog == null) {
                    deliverFareDialog = new DeliverFareDialog(SendBottleOrdersDetailPostActivity.this,map);
                    deliverFareDialog.show();
                }else if(!deliverFareDialog.isShowing()){
                    deliverFareDialog.show();
                }
            }
        });
    }

    @Override
    public void onPostOrdersSuccess(ComfirmOrdersBean bean) {
        Intent intent = new Intent(this, SendBottleOrdersDetailPayActivity.class);
        intent.putExtra(Constants.ORDER_ID, orderId);
        startActivity(intent);
        finish();
    }

    @Override
    public String getOrderId() {
        return orderId;
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
    public void onGetSecondDeliverySuccess(DeliveryBean bean) {
        String second5 = bean.getData().getDeliveryFee().getFiveDeliveryFee()+"";
        String second15 = bean.getData().getDeliveryFee().getFifteenDeliveryFee()+"";
        String second50 =bean.getData().getDeliveryFee().getFiftyDeliveryFee()+"";
        map.put("second5",second5);
        map.put("second15",second15);
        map.put("second50",second50);
        isGetSecondDeliverySuccess = true;
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
        return orderFiveNum + "";
    }

    @Override
    public String getFifteenBottleCount() {
        return orderFifteenNum + "";
    }

    @Override
    public String getFiftyBottleCount() {
        return orderFiftyNum + "";
    }

    @Override
    public String getDeliveryBottleIds() {
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < heavyBottleList.size(); i++) {
            if (i == 0) {
                sf.append(heavyBottleList.get(i).getData().getId());
            } else {
                sf.append(",").append(heavyBottleList.get(i).getData().getId());
            }
        }
        return sf.toString();
    }

    @Override
    public String getFloor() {
        if (isElevator.equals("1")) {
            floor = "1";
        }
        return floor + "";
    }

    @Override
    public String getRecycleBottleIds() {
        StringBuilder sf = new StringBuilder();
        for (int i = 0; i < emptyBottleList.size(); i++) {
            if (i == 0) {
                sf.append(emptyBottleList.get(i).getData().getId());
            } else {
                sf.append(",").append(emptyBottleList.get(i).getData().getId());
            }
        }
        return sf.toString();
    }

    @Override
    public String getReplaceBottleStr() {
        if (exchangeList.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < exchangeList.size(); i++) {
                int levelIndex = exchangeList.get(i).getmSelectBottleLevelIndex();
                int modelIndex = exchangeList.get(i).getSelectBottleModeIndex();
                int year = exchangeList.get(i).getSelectBottleYearsIndex() + 1;
                int weight = 0;
                int num = exchangeList.get(i).getBottleNum();
              /*  switch (modelIndex) {
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
                }*/
                weight = 15;
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
                    stringBuilder.append(weight).append(",").append(year).append(",").append(num).append(",").append(level);
                } else {
                    stringBuilder.append("|").append(weight).append(",").append(year).append(",").append(num).append(",").append(level);
                }
            }
            return stringBuilder.toString();
        } else {
            return "";
        }

    }

    @Override
    public String getIsElevator() {
        return isElevator + "";
    }

   /* @Override
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
        if (((totalfare + "").length()) > 6) {
            tvCost.setText((totalfare + "").substring(0, 5));
        } else {
            tvCost.setText((totalfare + ""));
        }
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
            tvFiftyDeposite.setText(fiftyTotalDeposite + "");
        }
        totalDeposite = fiveTotalDeposite + fifteenteenTotalDeposite + fiftyTotalDeposite;
        if (totalDeposite == 0) {
            llDepositFare.setVisibility(View.GONE);
        } else {
            llDepositFare.setVisibility(View.VISIBLE);
            totalfare += totalDeposite;
            if (((totalfare + "").length()) > 6) {
                tvCost.setText((totalfare + "").substring(0, 5));
            } else {
                tvCost.setText((totalfare + ""));
            }
            tvDepositeFee.setText(totalDeposite + "");
        }
        if (!isElevator.equals("1")) {
            iDeliveryPresenter.getDelivery();
        } else {
            iDeliveryPresenter.getElevatorDelivery();
        }
    }*/

   /* @Override
    public String getSiteId() {
        return siteId;
    }*/


    @Override
    public void onGetDeliverySuccess(DeliveryBean bean) {
        totalfare-=totalDeliveryfare;
        fiveDeliveryFee = bean.getData().getDeliveryFee().getFiveDeliveryFee();
        fifteenDeliveryFee = bean.getData().getDeliveryFee().getFifteenDeliveryFee();
        fiftyDeliveryFee = bean.getData().getDeliveryFee().getFiftyDeliveryFee();
        totalFiveDelivery = orderFiveNum * fiveDeliveryFee;
        totalFifteenDelivery = orderFifteenNum * fifteenDeliveryFee;
        totalFiftyDelivery = orderFiftyNum * fiftyDeliveryFee;
        totalDeliveryfare = totalFiveDelivery + totalFifteenDelivery + totalFiftyDelivery;
        totalfare += totalDeliveryfare;
        if (((totalfare + "").length()) > 6) {
            tvCost.setText((totalfare + "").substring(0, 5));
        } else {
            tvCost.setText((totalfare + ""));
        }
        tvDeliverFee.setText(totalDeliveryfare + "");

    }

    @Override
    public String getFloors() {
        return floor + "";
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
