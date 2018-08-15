package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ComfirmOrdersBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPostPresenter;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.util.BottleCaculteUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IBackBottleDetailPostView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ISimpleOrderDetailView;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class BackBottleDetailPostActivity extends BaseActivity implements IBackBottleDetailPostView ,ISimpleOrderDetailView,PermissionUtils.PermissionRequestFinishListener{
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
    @BindView(R.id.tv_orderid)
    TextView tvOrderId;
    @BindView(R.id.ll_orders_detail_command_topbar_client_info)
    LinearLayout clientInfo;
    @BindView(R.id.hand_over_steel_bottle)
    Button btnSava;
    private List<VerifyBottleBean> backBottleList;
    private String orderId;
    private Unbinder unbinder;
    private IOrderDetailPresenter iOrderDetailPresenter;
    private OrderDetailBean detailBean;
    private String orderFive;
    private String orderFifteen;
    private String orderFifty;
    private String floor;
    private String isElevator;
    private String isDelivery;
    private IOrderDetailPostPresenter iOrderDetailPostPresenter;
    private String fiveDeposite;
    private String fifteenDeposite;
    private String fiftyDeposite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_bottle_orders_post_layout);
        unbinder =  ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            orderId = bundle.getString(Constants.ORDER_ID);
            backBottleList = (List<VerifyBottleBean>)bundle.getSerializable(Constants.EMPTY_BOTTLE_LIST);
            //此时订单状态还只是待验瓶
            iOrderDetailPresenter = new IOrderDetailPresenter(this);
            iOrderDetailPostPresenter= new IOrderDetailPostPresenter(this);
            iOrderDetailPresenter.getSimpleOrderDetail();
        }

    }
    @OnClick({R.id.return_btn, R.id.comman_topbar_call_phone_btn, R.id.hand_over_steel_bottle,R.id.ll_orders_detail_command_topbar_client_info})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.return_btn:
                finish();
                break;
            case R.id.comman_topbar_call_phone_btn:
                PopUtil.showTipsDialog(this, "拨打电话", "请确认是否要拨打电话" + tvTelephone.getText().toString(), "取消", "确认", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PermissionUtils.requestPermissions(BackBottleDetailPostActivity.this,BackBottleDetailPostActivity.this, Permission.CALL_PHONE);
                    }
                });
                break;
            case R.id.hand_over_steel_bottle:
                PopUtil.showTipsDialog(BackBottleDetailPostActivity.this, "提交退瓶订单", "确认已将押金付给客户并提交退瓶订单？", "取消", "确认", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iOrderDetailPostPresenter.postBackBottleOrders();
                    }
                });
                break;
            case R.id.ll_orders_detail_command_topbar_client_info:
                intent = new Intent(BackBottleDetailPostActivity.this, EditLocationActivity.class);
                intent.putExtra(Constants.FLOOR, floor);
                intent.putExtra(Constants.IS_ELEVATOR, isElevator);
                startActivityForResult(intent, Constants.EDIT_FLOOR_REQUEST_CODE);
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.EDIT_FLOOR_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                floor = data.getStringExtra(Constants.FLOOR);
                tvLocation.setText(detailBean.getData().getAddress() + floor + detailBean.getData().getRoomNum());
                isElevator = data.getStringExtra(Constants.IS_ELEVATOR);
                tvElevator.setText(isElevator.equals(1 + "") ? "(有电梯)" : "(无电梯)");
            }
        }

    }
    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean bean) {
        this.detailBean = bean;
        isDelivery = bean.getData().getIsDelivery()+"";
        floor = bean.getData().getFloor()+"";
        tvLocation.setText(new StringBuilder().append(bean.getData().getAddress()).append(floor).append("层").append(bean.getData().getRoomNum()).append("房").append(bean.getData().getIsDelivery()== 1?"(自提单)":"(配送单)").toString());
        isElevator = bean.getData().getIsElevator()+"";
        tvElevator.setText(isElevator.equals(1 + "") ? "(有电梯)" : "(无电梯)");
        tvUser.setText(new StringBuilder().append(bean.getData().getBuyer()).append(bean.getData().getSex() == 1 ? "(先生)" : "(女士)").toString());
        tvTelephone.setText(bean.getData().getMobile());
        tvDay.setText(bean.getData().getCreateDate());
        tvTime.setText(bean.getData().getAppointmentTime());
        tvComment.setText(bean.getData().getRemarks());
        tvOrderId.setText(orderId);
        orderFive = bean.getData().getReFiveBottleCount() +"";
        orderFifteen  = bean.getData().getReFifteenBottleCount() +"";
        orderFifty = bean.getData().getReFiftyBottleCount() +"";

        fiveDeposite  = BottleCaculteUtil.getDeposite(bean,5);
        fifteenDeposite  = BottleCaculteUtil.getDeposite(bean,15);
        fiftyDeposite  = BottleCaculteUtil.getDeposite(bean,50);
        fiveFee.setText(fiveDeposite);
        fifteenFee.setText(fifteenDeposite);
        fiftyFee.setText(fiftyDeposite);
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

    @SuppressLint("MissingPermission")
    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvTelephone.getText().toString()));
        startActivity(intent);
    }

    @Override
    public String getIsDelivery() {
        return isDelivery;
    }

    @Override
    public String getReFiveBottleCount() {
        return orderFive;
    }

    @Override
    public String getReFifteenBottleCount() {
        return orderFifteen;
    }

    @Override
    public String getReFiftyBottleCount() {
        return orderFifty;
    }

    @Override
    public String getRecycleBottleIds() {
        StringBuilder sf = new StringBuilder();
        for(int i = 0; i< backBottleList.size(); i++){
            if(i==0){
                sf.append(backBottleList.get(i).getData().getId());
            }else {
                sf.append(",").append(backBottleList.get(i).getData().getId());
            }
        }
        return sf.toString();
    }

    @Override
    public String getFloor() {
        return floor;
    }

    @Override
    public String getIsElevator() {
        return isElevator;
    }

    @Override
    public void onPostOrdersSuccess(ComfirmOrdersBean comfirmOrdersBean) {
        Intent intent = new Intent(this, BackBottleOrdersFinishActivity.class);
        intent.putExtra(Constants.ORDER_ID, orderId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
