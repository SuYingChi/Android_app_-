package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.OrdersListBeanV2;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IOrderView;
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
                    try{
                    OrdersListBeanV2 ordersBean = GsonUtil.getGson().fromJson(s, OrdersListBeanV2.class);
                    iOrderView.onGetOrdersSuccess(ordersBean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }
                }
            }
        });
    }
}
