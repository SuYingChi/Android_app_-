package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.msht.mshtlpgmaster.Bean.DeliveryBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.viewInterface.IDeliveryView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IDeliveryPresenter {
    private IDeliveryView iDeliveryView;

    public IDeliveryPresenter(IDeliveryView iDeliveryView) {
        this.iDeliveryView = iDeliveryView;
    }

    public void getDelivery() {
        OkHttpUtils.get().url(Constants.GET_ALL_Delivery_FEE).addParams("floors", iDeliveryView.getFloors())
                .addParams("isElevator", 0 + "")
                .build().execute(new DataStringCallback(iDeliveryView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iDeliveryView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iDeliveryView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    DeliveryBean deliveryBean = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                    iDeliveryView.onGetDeliverySuccess(deliveryBean);
                }
            }

        });
    }

    public void getElevatorDelivery() {
        OkHttpUtils.get().url(Constants.GET_ALL_Delivery_FEE).addParams("floors", "1")
                .addParams("isElevator", 0 + "")
                .build().execute(new DataStringCallback(iDeliveryView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iDeliveryView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iDeliveryView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    DeliveryBean bean2 = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                    iDeliveryView.onGetDeliverySuccess(bean2);
                }
            }

        });
    }
}
