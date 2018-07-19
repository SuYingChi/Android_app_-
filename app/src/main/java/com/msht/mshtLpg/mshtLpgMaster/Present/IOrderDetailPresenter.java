package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IOrderDetailPresenter {
    IOrderDetailView iOrderDetailView;

    public IOrderDetailPresenter(IOrderDetailView iOrderDetailView) {
        this.iOrderDetailView = iOrderDetailView;
    }

    public void getOrderDetail() {
        OkHttpUtils.get().url(Constants.ORDER_DETAIL).addParams("id", iOrderDetailView.getOrderId() + "")
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iOrderDetailView.getToken()).build().execute(new DataStringCallback(iOrderDetailView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                OrderDetailBean bean = GsonUtil.getGson().fromJson(s, OrderDetailBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iOrderDetailView.onGetOrdersDetailSuccess(bean);
                }
            }

        });
    }
}
