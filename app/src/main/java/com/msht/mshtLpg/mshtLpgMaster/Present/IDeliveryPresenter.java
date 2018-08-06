package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.DeliveryBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IDeliveryView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IDeliveryPresenter {
    private  IDeliveryView iDeliveryView;

    public IDeliveryPresenter(IDeliveryView iDeliveryView) {
     this.iDeliveryView = iDeliveryView;
    }

    public void getDelivery() {
        OkHttpUtils.get().url(Constants.GET_Delivery_FEE).addParams("floors", iDeliveryView.getFloors())
                .build().execute(new DataStringCallback(iDeliveryView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
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
        OkHttpUtils.get().url(Constants.GET_Delivery_FEE).addParams("floors", "1")
                .build().execute(new DataStringCallback(iDeliveryView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                DeliveryBean bean = GsonUtil.getGson().fromJson(s, DeliveryBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iDeliveryView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iDeliveryView.onGetDeliverySuccess(bean);
                }
            }

        });
    }
}
