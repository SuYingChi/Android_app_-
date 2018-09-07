package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;
import android.util.Log;

import com.msht.mshtlpgmaster.Bean.ComfirmOrdersBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.viewInterface.IBackBottleDetailPostView;
import com.msht.mshtlpgmaster.viewInterface.IOrderDetailPostView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IOrderDetailPostPresenter {
    private IBackBottleDetailPostView iBackBottleDetailPostView;
    private IOrderDetailPostView iOrderDetailPostView;

    public IOrderDetailPostPresenter(IOrderDetailPostView iOrderDetailPostView) {
        this.iOrderDetailPostView = iOrderDetailPostView;
    }

    public IOrderDetailPostPresenter(IBackBottleDetailPostView iBackBottleDetailPostView) {
        this.iBackBottleDetailPostView = iBackBottleDetailPostView;
    }

    public void postOrders() {
        OkHttpUtils.get().url(Constants.POST_ORDER).addParams(Constants.ORDER_ID, iOrderDetailPostView.getOrderId())
                .addParams(Constants.URL_PARAMS_ORDER_TYPE, iOrderDetailPostView.getOrderType())
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iOrderDetailPostView.getToken())
                .addParams(Constants.IS_DELIVERY, iOrderDetailPostView.getIsDelivery())
                .addParams(Constants.reFiveBottleCount, iOrderDetailPostView.getReFiveBottleCount())
                .addParams("reFifteenBottleCount", iOrderDetailPostView.getReFifteenBottleCount())
                .addParams("reFiftyBottleCount", iOrderDetailPostView.getReFiftyBottleCount())
                .addParams("fiveBottleCount", iOrderDetailPostView.getFiveBottleCount())
                .addParams("fifteenBottleCount", iOrderDetailPostView.getFifteenBottleCount())
                .addParams("fiftyBottleCount", iOrderDetailPostView.getFiftyBottleCount())
                .addParams("deliveryBottleIds", iOrderDetailPostView.getDeliveryBottleIds())
                .addParams("recycleBottleIds", iOrderDetailPostView.getRecycleBottleIds())
                .addParams("floor", iOrderDetailPostView.getFloor())
                .addParams("isElevator", iOrderDetailPostView.getIsElevator())
                .addParams("replaceBottleStr", iOrderDetailPostView.getReplaceBottleStr())
                .build().execute(new DataStringCallback(iOrderDetailPostView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                Log.d("suyingchi", "onResponse: before from json---------" + s);
                if (isResponseEmpty) {
                    iBackBottleDetailPostView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);

                Log.d("suyingchi", "onResponse: after from json");
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailPostView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    ComfirmOrdersBean comfirmOrdersBean = GsonUtil.getGson().fromJson(s, ComfirmOrdersBean.class);
                    iOrderDetailPostView.onPostOrdersSuccess(comfirmOrdersBean);
                }
            }

        });
    }

    public void postBackBottleOrders() {
        OkHttpUtils.get().url(Constants.POST_ORDER).addParams(Constants.ORDER_ID, iBackBottleDetailPostView.getOrderId())
                .addParams(Constants.URL_PARAMS_ORDER_TYPE, iBackBottleDetailPostView.getOrderType())
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iBackBottleDetailPostView.getToken())
                .addParams(Constants.IS_DELIVERY, iBackBottleDetailPostView.getIsDelivery())
                .addParams(Constants.reFiveBottleCount, iBackBottleDetailPostView.getReFiveBottleCount())
                .addParams("reFifteenBottleCount", iBackBottleDetailPostView.getReFifteenBottleCount())
                .addParams("reFiftyBottleCount", iBackBottleDetailPostView.getReFiftyBottleCount())
                .addParams("recycleBottleIds", iBackBottleDetailPostView.getRecycleBottleIds())
                .addParams("floor", iBackBottleDetailPostView.getFloor())
                .addParams("isElevator", iBackBottleDetailPostView.getIsElevator())
                .build().execute(new DataStringCallback(iBackBottleDetailPostView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iBackBottleDetailPostView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iBackBottleDetailPostView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    ComfirmOrdersBean comfirmOrdersBean = GsonUtil.getGson().fromJson(s, ComfirmOrdersBean.class);
                    iBackBottleDetailPostView.onPostOrdersSuccess(comfirmOrdersBean);
                }
            }

        });
    }

}
