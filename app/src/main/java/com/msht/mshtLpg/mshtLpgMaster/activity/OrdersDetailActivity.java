package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrdersDetailActivity extends BaseActivity implements IOrderDetailView, PermissionUtils.PermissionRequestFinishListener {
    @BindView(R.id.return_btn)
    ImageView returnBtn;
    @BindView(R.id.location)
    TextView tvLocation;
    @BindView(R.id.elevator)
    TextView tvElevator;
    @BindView(R.id.comman_topbar_user)
    TextView tvUser;
    @BindView(R.id.comman_topbar_telephone)
    TextView tvTelephone;
    @BindView(R.id.comman_topbar_call_phone_btn)
    ImageView callBtn;
    @BindView(R.id.comman_topbar_day)
    TextView tvDay;
    @BindView(R.id.comman_topbar_time)
    TextView tvTime;
    @BindView(R.id.comman_topbar_comment)
    TextView tvComment;
    @BindView(R.id.five_gas)
    TextView fiveFee;
    @BindView(R.id.fifteen_gas)
    TextView fifteenFee;
    @BindView(R.id.fifty_gas)
    TextView fiftyFee;
    @BindView(R.id.total_fee)
    TextView totalFee;
    @BindView(R.id.dispatch_orders_time)
    TextView dispatchOrdersTime;
    @BindView(R.id.hand_over_steel_bottle)
    Button handOverSteelBottle;
    @BindView(R.id.tv_orderid)
    TextView tvOrderId;
    @BindView(R.id.deliver_fee)
    TextView deliverFee;
    private String orderId;
    private IOrderDetailPresenter iOrderDetailPresenter;
    private OrderDetailBean bean;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_detail_layout_deliver_steel_bottle);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        iOrderDetailPresenter = new IOrderDetailPresenter(this);
        iOrderDetailPresenter.getOrderDetail();
    }

    @OnClick({R.id.return_btn, R.id.comman_topbar_call_phone_btn, R.id.hand_over_steel_bottle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_btn:
                finish();
                break;
            case R.id.comman_topbar_call_phone_btn:
                PermissionUtils.requestPermissions(this, this, Permission.CALL_PHONE);
                break;
            case R.id.hand_over_steel_bottle:
                Intent intent = new Intent(this, ScanCodeDeliverSteelBottleActivity.class);
                intent.putExtra(Constants.ORDER_ID,orderId);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean bean) {
        this.bean = bean;
        tvLocation.setText(new StringBuilder().append(bean.getData().getAddress()).append(bean.getData().getFloor()).append(bean.getData().getRoomNum()).toString());
        tvElevator.setText(bean.getData().getIsElevator() == 1 ? "(有电梯)" : "(无电梯)");
        tvUser.setText(new StringBuilder().append(bean.getData().getBuyer()).append(bean.getData().getSex() == 1 ? "(先生)" : "(女士)").toString());
        tvTelephone.setText(bean.getData().getMobile());
        tvDay.setText(bean.getData().getCreateDate());
        tvTime.setText(bean.getData().getAppointmentTime());
        tvComment.setText(bean.getData().getRemarks());
        tvOrderId.setText(bean.getData().getOrderId()+"");
        fiveFee.setText(bean.getData().getFiveBottleCount() +"");
        fifteenFee.setText(bean.getData().getFifteenBottleCount() +"");
        fiftyFee.setText(bean.getData().getFiftyBottleCount() +"");
        deliverFee.setText(bean.getData().getFiveBottleCount()*bean.getData().getFiveDeliveryFee()+bean.getData().getFifteenBottleCount()*bean.getData().getFifteenDeliveryFee()+bean.getData().getFiftyBottleCount()*bean.getData().getFiftyDeliveryFee()+"");
        //totalFee.setText(String.valueOf(Integer.valueOf(fiveFee.getText().toString())+Integer.valueOf(fifteenFee.getText().toString())+Integer.valueOf(fiftyFee.getText().toString())));
        totalFee.setText(bean.getData().getRealAmount()+"");
        dispatchOrdersTime.setText(bean.getData().getCreateDate());
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
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvTelephone.getText().toString()));
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}