package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersListBeanV2;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderView;
import com.zhy.http.okhttp.OkHttpUtils;


public class IOrdersListPresenter {

    private IOrderView iOrderView;

    public IOrdersListPresenter(IOrderView iOrderView) {
        this.iOrderView = iOrderView;
    }

    public void getOrders() {
        OkHttpUtils.get().url(Constants.QUERY_ORDERS)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iOrderView.getToken()).
                addParams(Constants.URL_PARAMS_ORDER_STATUS, iOrderView.getOrdersStatus() + "")
                .addParams(Constants.URL_PARAMS_ORDER_TYPE, iOrderView.getOrderType())
                .addParams(Constants.URL_PARAMS_PAGE_NUM, iOrderView.getPage() + "")
                .addParams("pageSize", "2")
                .build().execute(new DataStringCallback(iOrderView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承,diss对话框，再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iOrderView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean errorBean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (errorBean != null && !TextUtils.isEmpty(errorBean.getResult()) && TextUtils.equals(errorBean.getResult(), "fail")) {
                    iOrderView.onError(errorBean.getMsg());
                } else if (errorBean != null && !TextUtils.isEmpty(errorBean.getResult()) && TextUtils.equals(errorBean.getResult(), "success")) {
                    OrdersListBeanV2 ordersBean = GsonUtil.getGson().fromJson(s, OrdersListBeanV2.class);
                    iOrderView.onGetOrdersSuccess(ordersBean);
                }
            }
        });
    }
}
