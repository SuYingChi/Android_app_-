package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.msht.mshtLpg.mshtLpgMaster.Bean.GetPayQRCodeBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.GetPayQRErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.QueryOrderBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.GetOrderStatusPresenter;
import com.msht.mshtLpg.mshtLpgMaster.Present.GetQRcodeImageUrlPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IGetPayQRcodeView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QRCodeReceiptActivity extends BaseActivity implements IGetPayQRcodeView {


    @BindView(R.id.qr_code_iv)
    ImageView ivqrQRCode;
    @BindView(R.id.orders_number)
    TextView ordersNumber;
    @BindView(R.id.account)
    TextView account;
    private GetQRcodeImageUrlPresenter getQRcodeImageUrlPresenter;
    private String QRCodeUrl;
    private GetOrderStatusPresenter getOrderStatusPresenter;
    private ScheduledFuture<?> a;
    private ScheduledFuture<?> b;
    private ScheduledThreadPoolExecutor executor;
    private String payType;
    private String body = "付款";
    private String payAmount;
    private String orderId;
    Handler handler = new Handler();
    private Timer time = new Timer();
    private TimerTask tk;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_receipt);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        payAmount = intent.getStringExtra(Constants.PAY_AMOUNT);
        payType = intent.getStringExtra(Constants.PAY_TYPE);
        getQRcodeImageUrlPresenter = new GetQRcodeImageUrlPresenter(this);
        getOrderStatusPresenter = new GetOrderStatusPresenter(this);
        ordersNumber.setText(orderId);
        account.setText(payAmount);
        executor = new ScheduledThreadPoolExecutor(2);
        a = executor.scheduleWithFixedDelay(
                new GetQRCodeImageTask(),
                0,
                3,
                TimeUnit.MINUTES);
        b = executor.scheduleWithFixedDelay(
                new QueryOrderStatusTask(),
                5000,
                5000,
                TimeUnit.MILLISECONDS);
        executor.schedule(new FinishPayTask(), 8, TimeUnit.MINUTES);

    }


    @Override
    public void onGetQRCodeImageURLSuccess(GetPayQRCodeBean bean) {
        QRCodeUrl = bean.getData();
        Glide.with(QRCodeReceiptActivity.this).load(QRCodeUrl)
                .apply(new RequestOptions().centerCrop())
                .into(ivqrQRCode);

    }

    @Override
    public void onGetQRCodeImageURLError(GetPayQRErrorBean bean) {

    }

    @Override
    public void onGetOrderStatusSuccess(QueryOrderBean bean) {
        String s = "";
        int sta = bean.getData().getOrderStatus();
        switch (sta) {
            case 0:
                //待付款
                s = "待支付";
                break;
            case 1:
                s = "待发货";
                break;
            case 2:
                //送气单 对应待验瓶
                s = "已发货";
                break;
            case 3:
                //已完成
                s = "已完成";
                executor.shutdown();
                Intent intent = new Intent(this, SendBottleOrdersDetailFinishActivity.class);
                intent.putExtra(Constants.ORDER_ID, orderId);
                startActivity(intent);
                finish();
                break;
            case 4:
                s = "已退款";
                break;
            case 5:
                s = "失败";
                break;
            case 6:
                s = "待收货";
                break;
            case 7:
                s = "已派工";
                break;
            default:
                break;
        }

    }


    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getPayType() {
        return payType;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getPayAmount() {
        return payAmount;
    }

    @Override
    public Handler getHandler() {

        return handler;
    }

    @Override
    public void onError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
        unbinder.unbind();
    }

    private class GetQRCodeImageTask implements Runnable {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    getQRcodeImageUrlPresenter.getQRcodeUrl();
                }
            });

        }
    }

    private class QueryOrderStatusTask implements Runnable {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    getOrderStatusPresenter.getOrderStatus();
                }
            });
        }
    }

    private class FinishPayTask implements Runnable {
        @Override
        public void run() {
            finish();
        }
    }


}
