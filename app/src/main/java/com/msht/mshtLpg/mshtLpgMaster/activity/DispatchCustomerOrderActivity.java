package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.DeliveryBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.DeliverFareDialog;
import com.msht.mshtLpg.mshtLpgMaster.customView.TimeSelecteDialog;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;

import java.text.BreakIterator;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DispatchCustomerOrderActivity extends BaseActivity implements View.OnClickListener, TimeSelecteDialog.OnSelectTimeListener, IOrderDetailView {
    private static final int RETURN_BOTTLE = 2;
    private static final int SEND_BOTTLE = 1;
    @BindView(R.id.scan_delive_topbar)
    TopBarView topBarView;
    @BindView(R.id.id_select_address_layout)
    View layoutSelectAddress;
    @BindView(R.id.id_tv_address)
    TextView textView;
    @BindView(R.id.id_tv_username)
    TextView tvUserName;
    @BindView(R.id.id_tv_mobile)
    TextView tvMobile;
    @BindView(R.id.id_tv_elevator)
    TextView tvElevator;
    @BindView(R.id.id_tv_amount1)
    TextView tvFive;
    @BindView(R.id.id_tv_amount2)
    TextView tvFifteen;
    @BindView(R.id.id_tv_amount3)
    TextView tvFifty;
    @BindView(R.id.id_total_amount)
    TextView tvTotal;
    @BindView(R.id.id_tv_isdeliver)
    TextView tvIsDeliver;
    @BindView(R.id.id_delivery_time)
    TextView tvChooseTime;
    @BindView(R.id.rlt_delivery)
    RelativeLayout relativeLayout;
    @BindView(R.id.id_transportation_expense)
    TextView tvTotalAmount;
    @BindView(R.id.id_btn_send)
    Button btnSend;
    private static final int SELECT_SUCCESS_CODE = 1;
    private Context mContext;
    private Unbinder unbinder;
    private String mArea;
    private String mCity;
    private String locationName;
    private String addressDescribe;
    private String latitude;
    private String longitude;
    private BreakIterator tvAddress;
    private String fiveWeightCount;
    private String fifteenWeightCount;
    private String fiftyWeightCount;
    private String username;
    private String phone;
    private String isDelivery;
    private String name;
    private String sex;
    private String isElevator;
    private String ridgepole;
    private String floor;
    private String room;
    private String total;
    private TimeSelecteDialog timeSelecteDialog;
    private int systemYear;
    private int systemMonth;
    private int systemDate;
    private int sendOrderYear;
    private int sendOrderMonth;
    private int sendOrderDate;
    private IOrderDetailPresenter iOrderDetailPresenter;
    private Map<String, String> map = new HashMap<String, String>();
    private boolean isGetFourDeliverySuccess = false;
    private boolean isGetSixDeliverySuccess = false;
    private boolean isGetFirstDeliverySuccess = false;
    private boolean isGetSecondDeliverySuccess = false;
    private DeliverFareDialog deliverFareDialog;
    private double totalAmount = 0;
    private int systemHour;
    private int systemMinute;
    private int sendOrderHour;
    private int sendOrderMinute;
    private boolean validateDate = true;
    private boolean validateTime = true;
    private String systemDates;
    private String systemTime;
    private String sendDates;
    private String sendTime;
    private int dispatchOrdersType;
    private Timer time = new Timer();
    private TimerTask tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_customer_order);
        unbinder = ButterKnife.bind(this);
        if (TextUtils.equals(getIntent().getStringExtra("dispatchOrdersType"), "2")) {
            dispatchOrdersType = RETURN_BOTTLE;
        } else {
            dispatchOrdersType = SEND_BOTTLE;
        }
        mContext = this;
        layoutSelectAddress.setOnClickListener(this);
        systemYear = Calendar.getInstance().get(Calendar.YEAR);
        systemMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        systemDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        systemHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        systemMinute = Calendar.getInstance().get(Calendar.MINUTE);
        sendOrderYear = systemYear;
        sendOrderMonth = systemMonth;
        sendOrderDate = systemDate;
        sendOrderHour = systemHour;
        sendOrderMinute = systemMinute;
        systemDates = systemYear + "-" + ((systemMonth) < 10 ? "0" + (systemMonth) : (systemMonth)) + "-" + (systemDate < 10 ? "0" + systemDate : systemDate);
        systemTime = systemHour + "-" + ((systemMinute) < 10 ? "0" + (systemMinute) : (systemMinute));
        sendDates = systemDates;
        sendTime = systemTime;
        tvChooseTime.setText(new StringBuffer().append(systemDates).append("  ").append(systemTime).toString());
        tk = new TimerTask() {
            @Override
            public void run() {
                systemYear = Calendar.getInstance().get(Calendar.YEAR);
                systemMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
                systemDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                systemHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                systemMinute = Calendar.getInstance().get(Calendar.MINUTE);
                systemDates = systemYear + "-" + ((systemMonth) < 10 ? "0" + (systemMonth) : (systemMonth)) + "-" + (systemDate < 10 ? "0" + systemDate : systemDate);
                systemTime = systemHour + "-" + ((systemMinute) < 10 ? "0" + (systemMinute) : (systemMinute));
            }
        };
        int systemMillisecond = Calendar.getInstance().get(Calendar.MILLISECOND);
        int delay = 60000 - systemMillisecond;
        time.schedule(tk, delay, 60000);
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dispatchOrdersType == SEND_BOTTLE) {
                    if (TextUtils.isEmpty(addressDescribe) || TextUtils.isEmpty(locationName) || TextUtils.isEmpty(name) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(isElevator) || TextUtils.isEmpty(ridgepole) || TextUtils.isEmpty(floor) || TextUtils.isEmpty(room)) {
                        PopUtil.toastInBottom("请完善订单信息");
                    } else {
                        PopUtil.showComfirmDialog(DispatchCustomerOrderActivity.this, "提示", "下版本增加代客下退瓶订单功能，感谢新老用户的支持", "", "", null, null, true);
                    }
                } else {
                    if (TextUtils.isEmpty(addressDescribe) || TextUtils.isEmpty(locationName) || TextUtils.isEmpty(name) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(isElevator) || TextUtils.isEmpty(ridgepole) || TextUtils.isEmpty(floor) || TextUtils.isEmpty(room)) {
                        PopUtil.toastInBottom("请完善订单信息");
                    } else {
                        PopUtil.showComfirmDialog(DispatchCustomerOrderActivity.this, "提示", "下版本增加代客下退瓶订单功能，感谢新老用户的支持", "", "", null, null, true);
                    }
                }
            }
        });
        tvChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeSelectDialog();
            }
        });
        if (dispatchOrdersType == SEND_BOTTLE) {
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(floor)) {
                        PopUtil.toastInBottom("请编辑配送地址是否有电梯以及楼层");
                    } else if (!isGetFirstDeliverySuccess || !isGetFourDeliverySuccess || !isGetSixDeliverySuccess || !isGetSecondDeliverySuccess) {
                        PopUtil.toastInBottom("正在获取运费信息，请稍后再试");
                    } /*else if (deliverFareDialog == null) {
                        deliverFareDialog = new DeliverFareDialog(DispatchCustomerOrderActivity.this, map);
                        deliverFareDialog.show();
                    } else if (!deliverFareDialog.isShowing()) {
                        deliverFareDialog.show();
                    }*/else {
                        PopUtil.showWebViewDialog(DispatchCustomerOrderActivity.this, Constants.PEI_SONG_SHUO_MING);
                    }
                }
            });
        } else {
            relativeLayout.setVisibility(View.GONE);
        }
        Intent intent = getIntent();
        fiveWeightCount = intent.getStringExtra("fiveCount");
        fifteenWeightCount = intent.getStringExtra("fifteenWeightCount");
        fiftyWeightCount = intent.getStringExtra("fiftyWeightCount");
        tvFive.setText(fiveWeightCount);
        tvFifteen.setText(fifteenWeightCount);
        tvFifty.setText(fiftyWeightCount);
        isDelivery = intent.getStringExtra("isDelivery");
        tvIsDeliver.setText(isDelivery);
        total = intent.getStringExtra("total");
        tvTotal.setText(total);
        iOrderDetailPresenter = new IOrderDetailPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_select_address_layout:
                onStartDeliveryAddress();
                break;
            default:
                break;
        }
    }

    private void onStartDeliveryAddress() {
        Intent intent = new Intent(mContext, DispatchSendOrdersEditAddressActivity.class);
        startActivityForResult(intent, SELECT_SUCCESS_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        time.cancel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SELECT_SUCCESS_CODE:
                if (data != null) {
                    mArea = data.getStringExtra("mArea");
                    mCity = data.getStringExtra("mCity");
                    locationName = data.getStringExtra("addressName");
                    addressDescribe = data.getStringExtra("addressDescribe");
                    latitude = data.getStringExtra("lat");
                    longitude = data.getStringExtra("lon");
                    name = data.getStringExtra("name");
                    sex = data.getStringExtra("sex");
                    tvUserName.setText(name + "(" + sex + ")");
                    phone = data.getStringExtra("phone");
                    tvMobile.setText(phone);
                    isElevator = data.getStringExtra("isElevator");
                    tvElevator.setText(isElevator);
                    ridgepole = data.getStringExtra("Ridgepole");
                    floor = data.getStringExtra("Floor");
                    room = data.getStringExtra("Room");
                    textView.setText(addressDescribe + ridgepole + "栋" + floor + "楼" + room + "房");
                    if (dispatchOrdersType == SEND_BOTTLE) {
                        iOrderDetailPresenter.getFirstDelivery();
                        iOrderDetailPresenter.getSecondDelivery();
                        iOrderDetailPresenter.getFourDelivery();
                        iOrderDetailPresenter.getSixDelivery();
                    }
                }
                break;
            default:
                break;
        }

    }

    private void showTimeSelectDialog() {
        if (!isFinishing() && timeSelecteDialog == null) {
            timeSelecteDialog = new TimeSelecteDialog(this, systemYear, systemMonth, systemDate, systemHour, systemMinute, DispatchCustomerOrderActivity.this);
            timeSelecteDialog.show();
        } else if (!isFinishing() && !timeSelecteDialog.isShowing()) {
            timeSelecteDialog.setSystemTime(systemYear, systemMonth, systemDate, systemHour, systemMinute);
            timeSelecteDialog.show();
        }
    }


    private void hideTimeSelectDialog() {
        if (timeSelecteDialog != null && timeSelecteDialog.isShowing() && !isFinishing()) {
            timeSelecteDialog.dismiss();
        }
    }

    @Override
    public void onSelectDate(int year, int month, int date) {
        Log.d("SendCustomerOrder", "onSelectDate: systemYear=" + year + "systemMonth=" + month + "systemDate=" + date);
        validateDate = checkDate(year, month + 1, date);
        if (validateDate) {
            DispatchCustomerOrderActivity.this.sendOrderYear = year;
            DispatchCustomerOrderActivity.this.sendOrderMonth = month + 1;
            DispatchCustomerOrderActivity.this.sendOrderDate = date;
            sendDates = DispatchCustomerOrderActivity.this.sendOrderYear + "-" + ((DispatchCustomerOrderActivity.this.sendOrderMonth) < 10 ? "0" + (DispatchCustomerOrderActivity.this.sendOrderMonth) : (DispatchCustomerOrderActivity.this.sendOrderMonth)) + "-" + (DispatchCustomerOrderActivity.this.sendOrderDate < 10 ? "0" + DispatchCustomerOrderActivity.this.sendOrderDate : DispatchCustomerOrderActivity.this.sendOrderDate);
            tvChooseTime.setText(new StringBuffer().append(sendDates).append(" ").append(sendTime).toString());
        } else {
            PopUtil.toastInBottom("不能选择" + systemDates + "之前的日期");
        }
    }

    @Override
    public void onSelectTime(int hourOfDay, int minute) {
        validateTime = checkTime(hourOfDay, minute);
        if (validateTime) {
            DispatchCustomerOrderActivity.this.sendOrderHour = hourOfDay;
            DispatchCustomerOrderActivity.this.sendOrderMinute = minute;
            sendTime = sendOrderHour + "-" + ((sendOrderMinute) < 10 ? "0" + (sendOrderMinute) : (sendOrderMinute));
            tvChooseTime.setText(new StringBuffer().append(sendDates).append(" ").append(sendTime).toString());
        } else {
            PopUtil.toastInBottom("不能选择" + systemTime + "之前的日期");
            timeSelecteDialog.reverseTime(systemHour, systemMinute);
        }
    }

    private boolean checkTime(int hourOfDay, int minute) {
        return validateDate && (systemYear != sendOrderYear || systemMonth != sendOrderMonth || systemDate != sendOrderDate || systemHour <= hourOfDay && (systemHour != hourOfDay || systemMinute <= minute));
    }

    private boolean checkDate(int year, int month, int date) {
        return DispatchCustomerOrderActivity.this.systemYear <= year && (DispatchCustomerOrderActivity.this.systemYear != year || DispatchCustomerOrderActivity.this.systemMonth <= month && (DispatchCustomerOrderActivity.this.systemMonth != month || DispatchCustomerOrderActivity.this.systemDate <= date));
    }

    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean bean) {

    }

    @Override
    public String getOrderId() {
        return null;
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
        calculateDeliveryFare();
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
        calculateDeliveryFare();
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
        calculateDeliveryFare();
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
        calculateDeliveryFare();
    }

    private void calculateDeliveryFare() {
        if (TextUtils.equals("配送", isDelivery)) {
            if (isGetFirstDeliverySuccess && isGetFourDeliverySuccess && isGetSixDeliverySuccess && isGetSecondDeliverySuccess) {
                if (TextUtils.equals("有电梯", isElevator) || Integer.valueOf(floor) == 1) {
                    double fiveDeleivery = Double.valueOf(fiveWeightCount) * Double.valueOf(map.get("first5"));
                    double fifteenDeleivery = Double.valueOf(fifteenWeightCount) * Double.valueOf(map.get("first15"));
                    double fiftyDeleivery = Double.valueOf(fiftyWeightCount) * Double.valueOf(map.get("first50"));
                    totalAmount = fiveDeleivery + fifteenDeleivery + fiftyDeleivery;
                    tvTotalAmount.setText(totalAmount + "");
                } else if (TextUtils.equals("无电梯", isElevator)) {
                    if (Integer.valueOf(floor) <= 3) {
                        double fiveDeleivery = Double.valueOf(fiveWeightCount) * Double.valueOf(map.get("second5"));
                        double fifteenDeleivery = Double.valueOf(fifteenWeightCount) * Double.valueOf(map.get("second15"));
                        double fiftyDeleivery = Double.valueOf(fiftyWeightCount) * Double.valueOf(map.get("second50"));
                        totalAmount = fiveDeleivery + fifteenDeleivery + fiftyDeleivery;
                        tvTotalAmount.setText(totalAmount + "");
                    } else if (Integer.valueOf(floor) <= 5) {
                        double fiveDeleivery = Double.valueOf(fiveWeightCount) * Double.valueOf(map.get("four5"));
                        double fifteenDeleivery = Double.valueOf(fifteenWeightCount) * Double.valueOf(map.get("four15"));
                        double fiftyDeleivery = Double.valueOf(fiftyWeightCount) * Double.valueOf(map.get("four50"));
                        totalAmount = fiveDeleivery + fifteenDeleivery + fiftyDeleivery;
                        tvTotalAmount.setText(totalAmount + "");
                    } else {
                        double fiveDeleivery = Double.valueOf(fiveWeightCount) * Double.valueOf(map.get("six5"));
                        double fifteenDeleivery = Double.valueOf(fifteenWeightCount) * Double.valueOf(map.get("six15"));
                        double fiftyDeleivery = Double.valueOf(fiftyWeightCount) * Double.valueOf(map.get("six50"));
                        totalAmount = fiveDeleivery + fifteenDeleivery + fiftyDeleivery;
                        tvTotalAmount.setText(totalAmount + "");
                        ;
                    }
                }
            }
        }
    }
}
