package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPostPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailPostView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersDetailPostActivity extends BaseActivity implements IOrderDetailPostView {


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
    @BindView(R.id.five_num)
    TextView tvFive;
    @BindView(R.id.fifteen_num)
    TextView tvFifteen;
    @BindView(R.id.fifty_num)
    TextView tvFifty;
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

    private IOrderDetailPostPresenter iOrderDetailPostPresenter;
    private OrderDetailBean bean;
    private int emptyFiveNum = 0;
    private int emptyFifteenNum = 0;
    private int emptyFiftyNum = 0;
    private int exchangeFee;
    private int floor;
    private int isElevator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_orders_v2);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        bean = (OrderDetailBean) intent.getSerializableExtra(Constants.ORDER_DETAIL_BEAN);
        emptyFiveNum = intent.getIntExtra(Constants.EMPTY_FIVE_NUM, 0);
        emptyFifteenNum = intent.getIntExtra(Constants.EMPTY_FIFTEEN_NUM, 0);
        emptyFiftyNum = intent.getIntExtra(Constants.EMPTY_FIFTY_NUM, 0);
        iOrderDetailPostPresenter = new IOrderDetailPostPresenter(this);
        initView();
    }

    private void initView() {
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvLocation.setText(new StringBuilder().append(bean.getData().getAddress()).append(bean.getData().getFloor()).append(bean.getData().getRoomNum()).toString());
        tvElevator.setText(bean.getData().getIsElevator() == 1 ? "(有电梯)" : "(无电梯)");
        tvUser.setText(new StringBuilder().append(bean.getData().getBuyer()).append(bean.getData().getSex() == 1 ? "(先生)" : "(女士)").toString());
        tvTel.setText(bean.getData().getMobile());
        tvDay.setText(bean.getData().getCreateDate());
        tvTime.setText(bean.getData().getAppointmentTime());
        tvComment.setText(bean.getData().getRemarks());
        tvOrderId.setText(bean.getData().getOrderId());
        tvFive.setText(bean.getData().getFiveBottleCount() * bean.getData().getFiveGasFee());
        tvFifteen.setText(bean.getData().getFifteenBottleCount() * bean.getData().getFifteenGasFee());
        tvFifty.setText(bean.getData().getFiftyBottleCount() * bean.getData().getFiftyGasFee());

        //一瓶的押金是多少？
        if (emptyFiveNum != 0) {
            tvFiveDeposite.setText((bean.getData().getFiveBottleCount() - emptyFiveNum) * 1);
            llFiveDeposite.setVisibility(View.VISIBLE);
        } else if (emptyFifteenNum != 0) {
            llFiveDeposite.setVisibility(View.GONE);
            tvFifteenDeposite.setText((bean.getData().getFifteenBottleCount() - emptyFifteenNum) * 1);
            llFifteenDeposite.setVisibility(View.VISIBLE);
        } else if (emptyFiftyNum != 0) {
            llFifteenDeposite.setVisibility(View.GONE);
            tvFiftyDeposite.setText((bean.getData().getFiftyBottleCount() - emptyFiftyNum) * 1);
            llFiftyDeposite.setVisibility(View.VISIBLE);
        } else {
            llFiftyDeposite.setVisibility(View.GONE);
        }

        tvDepositeFee.setText(String.valueOf(Integer.valueOf(tvFiveDeposite.getText().toString()) + Integer.valueOf(tvFifteenDeposite.getText().toString()) + Integer.valueOf(tvFiftyDeposite.getText().toString())));
        tvDeliverFee.setText(bean.getData().getFiveBottleCount() * bean.getData().getFiveDeliveryFee() + bean.getData().getFifteenBottleCount() * bean.getData().getFifteenDeliveryFee() + bean.getData().getFiftyBottleCount() * bean.getData().getFiftyDeliveryFee());
        tvCost.setText(String.valueOf(Integer.valueOf(tvFive.getText().toString()) + Integer.valueOf(tvFifteen.getText().toString()) + Integer.valueOf(tvFifty.getText().toString())));
        tvDispatchOrderTime.setText(bean.getData().getCreateDate());
        tvDispatchOrderTime.setText(bean.getData().getCreateDate());

        tvCost.setText(Integer.valueOf(tvDeliverFee.getText().toString()) + Integer.valueOf(tvDepositeFee.getText().toString()) + Integer.valueOf(tvGasFee.getText().toString()));

        //startActivityforresult 自有产权置换点击事件
        llDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int remainFiveBottle = bean.getData().getFiveBottleCount() - emptyFiveNum;
                int remainFifteenBottle = bean.getData().getFifteenBottleCount() - emptyFifteenNum;
                int remainFiftyBottle = bean.getData().getFiftyBottleCount() - emptyFiftyNum;

                Intent intent = new Intent(OrdersDetailPostActivity.this, ExchangeSteelBottleActivity.class);
                intent.putExtra(Constants.REMAIN_FIVE_NUM, remainFiveBottle);
                intent.putExtra(Constants.REMAIN_FIFTEEN_NUM, remainFifteenBottle);
                intent.putExtra(Constants.REMAIN_FIFTY_NUM, remainFiftyBottle);
                startActivityForResult(intent, Constants.EXCHANGE_EMPTY_BOTTLE_REQUEST_CODE);

            }
        });
        tvPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOrderDetailPostPresenter.postOrders();
            }
        });
        client_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrdersDetailPostActivity.this, EditLocationActivity.class);
                startActivityForResult(intent,Constants.EDIT_FLOOR_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onPostOrdersSuccess() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.EXCHANGE_EMPTY_BOTTLE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null ) {
                exchangeFee = data.getIntExtra(Constants.EXCHANGE_FEE,0);
                tvDiscount.setText(exchangeFee+"");
            }
        } if (requestCode == Constants.EDIT_FLOOR_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null ) {
                floor = data.getIntExtra(Constants.FLOOR,0);
                isElevator = data.getIntExtra(Constants.IS_ELEVATOR,0);
            }
        }

    }

}
