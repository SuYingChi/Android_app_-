package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.msht.mshtlpgmaster.Bean.DeliveryBean;
import com.msht.mshtlpgmaster.Bean.OrderDetailBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.viewInterface.ISimpleOrderDetailView;
import com.msht.mshtlpgmaster.viewInterface.IOrderDetailView;
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
                if (isResponseEmpty) {
                    iOrderDetailView.onError("接口返回空字符串:");
                    return;
                }
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
                if (isResponseEmpty) {
                    iOrderDetailView.onError("接口返回空字符串:");
                    return;
                }
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
        OkHttpUtils.get().url(Constants.GET_ALL_Delivery_FEE).addParams("floors", "4")
                .addParams("isElevator", 0 + "")
                .build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iOrderDetailView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    DeliveryBean bean2 = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                    iOrderDetailView.onGetFourDeliverySuccess(bean2);
                }
            }

        });
    }

    public void getSixDelivery() {
        OkHttpUtils.get().url(Constants.GET_ALL_Delivery_FEE).addParams("floors", "6")
                .addParams("isElevator", 0 + "")
                .build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iOrderDetailView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    DeliveryBean bean2 = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                    iOrderDetailView.onGetSixDeliverySuccess(bean2);
                }
            }

        });
    }

    public void getFirstDelivery() {
        OkHttpUtils.get().url(Constants.GET_ALL_Delivery_FEE).addParams("floors", "1")
                .addParams("isElevator", 0 + "")
                .build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iOrderDetailView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    DeliveryBean bean2 = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                    iOrderDetailView.onGetFirstDeliverySuccess(bean2);
                }
            }

        });
    }

    public void getSecondDelivery() {
        OkHttpUtils.get().url(Constants.GET_ALL_Delivery_FEE).addParams("floors", "2")
                .addParams("isElevator", 0 + "")
                .build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iOrderDetailView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    DeliveryBean bean2 = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                    iOrderDetailView.onGetSecondDeliverySuccess(bean2);
                }
            }

        });
    }
}
