package com.msht.mshtlpgmaster.activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtlpgmaster.Bean.OrderDetailBean;
import com.msht.mshtlpgmaster.Present.IOrderDetailPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.util.DimenUtil;
import com.msht.mshtlpgmaster.util.PermissionUtils;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.ISimpleOrderDetailView;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SendBottleOrdersDetailActivity extends BaseActivity implements ISimpleOrderDetailView, PermissionUtils.PermissionRequestFinishListener {
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
    @BindView(R.id.dispatch_bottle_time)
    TextView tvDispatchBottleTime;
    @BindView(R.id.tv_comman_topbar_title)
    TextView title;
    private String orderId;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_detail_layout);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        returnBtn.setPadding(DimenUtil.dip2px(10), ImmersionBar.getStatusBarHeight(this),ImmersionBar.getStatusBarHeight(this),ImmersionBar.getStatusBarHeight(this));
        RelativeLayout.LayoutParams lay = (RelativeLayout.LayoutParams)title.getLayoutParams();
        lay.topMargin=ImmersionBar.getStatusBarHeight(this);
        title.setLayoutParams(lay);
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        IOrderDetailPresenter iOrderDetailPresenter = new IOrderDetailPresenter(this);
        iOrderDetailPresenter.getSimpleOrderDetail();
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
                Intent intent = new Intent(this, ScanSteelBottleActivity.class);
                intent.putExtra(Constants.ORDER_ID, orderId);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean bean) {
        tvLocation.setText(new StringBuilder().append(bean.getData().getAddress()).append(bean.getData().getFloor()).append("层").append(bean.getData().getRoomNum()).append("房").append(bean.getData().getIsDelivery() == 1 ? "(自提单)" : "(配送单)").toString());
        tvElevator.setText(bean.getData().getIsElevator() == 1 ? "(有电梯)" : "(无电梯)");
        tvUser.setText(new StringBuilder().append(bean.getData().getBuyer()).append(bean.getData().getSex() == 0 ? "(先生)" : "(女士)").toString());
        tvTelephone.setText(bean.getData().getMobile());
        tvDay.setText(new StringBuilder().append(bean.getData().getAppointmentTime()).toString());
        tvDispatchBottleTime.setText(new StringBuilder().append("发货时间：").append(bean.getData().getSendTime()));
        tvComment.setText(new StringBuilder().append("内部备注：").append(bean.getData().getRemarks()).toString());
        tvOrderId.setText(bean.getData().getOrderId() + "");
        int fiveCount = bean.getData().getFiveBottleCount();
        int fifteenCount = bean.getData().getFifteenBottleCount();
        int fiftyCount = bean.getData().getFiftyBottleCount();
        fiveFee.setText(String.valueOf(fiveCount));
        fifteenFee.setText(String.valueOf(fifteenCount));
        fiftyFee.setText(String.valueOf(fiftyCount));
        totalFee.setText(String.valueOf(fiveCount + fifteenCount + fiftyCount));
        dispatchOrdersTime.setText(new StringBuilder().append("下单时间：").append(bean.getData().getCreateDate()).toString());

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
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvTelephone.getText().toString()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
