package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.content.Intent;
import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.LoginActivity;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderView;
import com.zhy.http.okhttp.OkHttpUtils;


public class IOrdersPresenter {

    private IOrderView iOrderView;
    private int tempType = iOrderView.getOrdersType()==0?1:0;

    public IOrdersPresenter(IOrderView iOrderView) {
        this.iOrderView = iOrderView;
    }

    public void getOrders() {
        OkHttpUtils.get().url(Constants.QUERY_ORDERS)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iOrderView.getToken()).
                addParams(Constants.URL_PARAMS_ORDER_STATUS, iOrderView.getOrdersStatus() + "")
                .addParams(Constants.URL_PARAMS_ORDER_TYPE,  + tempType+"")
                .addParams(Constants.URL_PARAMS_PAGE_NUM, iOrderView.getPage() + "")
                .build().execute(new DataStringCallback(iOrderView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承,diss对话框，再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                OrdersBean ordersBean = GsonUtil.getGson().fromJson(s,OrdersBean.class);
                if (ordersBean!=null&&!TextUtils.isEmpty(ordersBean.getResult()) && TextUtils.equals(ordersBean.getResult(), "failed")) {
                    iOrderView.onError(ordersBean.getMsg());
                } else if (ordersBean!=null&&!TextUtils.isEmpty(ordersBean.getResult()) && TextUtils.equals(ordersBean.getResult(), "success")) {
                   iOrderView.onGetOrdersSuccess(ordersBean);
                }
            }
        });
    }
}
