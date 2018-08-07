package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.DeliveryBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ISimpleOrderDetailView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IOrderDetailPresenter {
    private ISimpleOrderDetailView iSimpleOrderDetailView;
    IOrderDetailView iOrderDetailView;

    public IOrderDetailPresenter(IOrderDetailView iOrderDetailView) {
        this.iOrderDetailView = iOrderDetailView;
    }
    public IOrderDetailPresenter(ISimpleOrderDetailView iSimpleOrderDetailView) {
        this.iSimpleOrderDetailView = iSimpleOrderDetailView;
    }
    public void getOrderDetail() {
        OkHttpUtils.get().url(Constants.ORDER_DETAIL).addParams("id", iOrderDetailView.getOrderId())
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iOrderDetailView.getToken()).build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    OrderDetailBean orderDetailBean = GsonUtil.getGson().fromJson(s, OrderDetailBean.class);
                    iOrderDetailView.onGetOrdersDetailSuccess(orderDetailBean);
                }
            }

        });
    }
    public void getSimpleOrderDetail() {
        OkHttpUtils.get().url(Constants.ORDER_DETAIL).addParams("id", iSimpleOrderDetailView.getOrderId())
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iSimpleOrderDetailView.getToken()).build().execute(new DataStringCallback(iSimpleOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iSimpleOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    OrderDetailBean orderDetailBean = GsonUtil.getGson().fromJson(s, OrderDetailBean.class);
                    iSimpleOrderDetailView.onGetOrdersDetailSuccess(orderDetailBean);
                }
            }

        });
    }
    public void getFourDelivery() {
        OkHttpUtils.get().url(Constants.ALL_DELIVER_FARE).addParams("floors", "4")
                .build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                DeliveryBean bean = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iOrderDetailView.onGetFourDeliverySuccess(bean);
                }
            }

        });
    }
    public void getSixDelivery() {
        OkHttpUtils.get().url(Constants.ALL_DELIVER_FARE).addParams("floors", "6")
                .build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                DeliveryBean bean = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iOrderDetailView.onGetSixDeliverySuccess(bean);
                }
            }

        });
    }

    public void getFirstDelivery() {
        OkHttpUtils.get().url(Constants.ALL_DELIVER_FARE).addParams("floors", "1")
                .build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                DeliveryBean bean = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iOrderDetailView.onGetFirstDeliverySuccess(bean);
                }
            }

        });
    }
    public void getSecondDelivery() {
        OkHttpUtils.get().url(Constants.ALL_DELIVER_FARE).addParams("floors", "2")
                .build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                DeliveryBean bean = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iOrderDetailView.onGetSecondDeliverySuccess(bean);
                }
            }

        });
    }
}
