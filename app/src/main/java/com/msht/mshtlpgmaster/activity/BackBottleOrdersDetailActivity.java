package com.msht.mshtlpgmaster.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtlpgmaster.Bean.OrderDetailBean;
import com.msht.mshtlpgmaster.Present.IOrderDetailPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.util.BottleCaculteUtil;
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

public class BackBottleOrdersDetailActivity extends BaseActivity implements ISimpleOrderDetailView, PermissionUtils.PermissionRequestFinishListener {
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
        setContentView(R.layout.back_bottle_orders_detail_layout);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        returnBtn.setPadding(DimenUtil.dip2px(10),ImmersionBar.getStatusBarHeight(this),ImmersionBar.getStatusBarHeight(this),ImmersionBar.getStatusBarHeight(this));
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
                PopUtil.showComfirmDialog(this, "拨打电话", "请确认是否要拨打电话" + tvTelephone.getText().toString(), "取消", "确认", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PermissionUtils.requestPermissions(BackBottleOrdersDetailActivity.this, BackBottleOrdersDetailActivity.this, Permission.CALL_PHONE);
                    }
                }, true);
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
        tvDay.setText(new StringBuilder().append(bean.getData().getAppointmentTime()));
        tvDispatchBottleTime.setText(new StringBuilder().append("发货时间：").append(bean.getData().getSendTime()));
        tvComment.setText(new StringBuilder().append("内部备注：").append(bean.getData().getRemarks()).toString());
        String retriveId = bean.getData().getOrderId() + "";
        tvOrderId.setText(retriveId);
        String fiveDeposite = BottleCaculteUtil.getDeposite(bean, 5);
        String fifteenDeposite = BottleCaculteUtil.getDeposite(bean, 15);
        String fiftyDeposite = BottleCaculteUtil.getDeposite(bean, 50);
        fiveFee.setText(fiveDeposite);
        fifteenFee.setText(fifteenDeposite);
        fiftyFee.setText(fiftyDeposite);
        totalFee.setText(bean.getData().getRealAmount() + "");
        dispatchOrdersTime.setText(new StringBuilder().append("下单时间：").append(bean.getData().getCreateDate()).toString());

    }


    @Override
    public String getOrderId() {
        return orderId;
    }


    @Override
    public void onBackFromSettingPage() {
        PopUtil.toastInBottom("请允许LPG拨打电话");
    }

    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        PopUtil.toastInBottom("请允许LPG拨打电话");
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvTelephone.getText().toString()));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
