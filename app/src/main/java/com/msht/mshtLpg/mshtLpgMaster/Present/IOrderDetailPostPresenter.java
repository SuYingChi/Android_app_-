package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;
import android.util.Log;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ComfirmOrdersBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailPostView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IOrderDetailPostPresenter {
    private  IOrderDetailPostView iOrderDetailPostView;

    public IOrderDetailPostPresenter(IOrderDetailPostView iOrderDetailPostView) {
        this.iOrderDetailPostView = iOrderDetailPostView;
    }
    public void postOrders(){
   /*    String i = iOrderDetailPostView.getReFiveBottleCount();
        iOrderDetailPostView.getIsDelivery();*/
        OkHttpUtils.get().url(Constants.POST_ORDER).addParams(Constants.ORDER_ID, iOrderDetailPostView.getOrderId())
                .addParams(Constants.URL_PARAMS_ORDER_TYPE,iOrderDetailPostView.getOrderType())
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iOrderDetailPostView.getToken())
                .addParams(Constants.IS_DELIVERY,iOrderDetailPostView.getIsDelivery())
                .addParams(Constants.reFiveBottleCount,iOrderDetailPostView.getReFiveBottleCount())
                .addParams("reFifteenBottleCount",iOrderDetailPostView.getReFifteenBottleCount())
                .addParams("reFiftyBottleCount",iOrderDetailPostView.getReFiftyBottleCount())
                .addParams("fiveBottleCount",iOrderDetailPostView.getFiveBottleCount())
                .addParams("fifteenBottleCount",iOrderDetailPostView.getFifteenBottleCount())
                .addParams("fiftyBottleCount",iOrderDetailPostView.getFiftyBottleCount())
                .addParams("deliveryBottleIds",iOrderDetailPostView.getDeliveryBottleIds())
                .addParams("recycleBottleIds",iOrderDetailPostView.getRecycleBottleIds())
                .addParams("floor",iOrderDetailPostView.getFloor())
                .addParams("isElevator",iOrderDetailPostView.getIsElevator())
                .addParams("replaceBottleStr",iOrderDetailPostView.getReplaceBottleStr())
                .build().execute(new DataStringCallback(iOrderDetailPostView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                Log.d("suyingchi", "onResponse: before from json---------"+s);
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);

                Log.d("suyingchi", "onResponse: after from json");
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iOrderDetailPostView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    ComfirmOrdersBean comfirmOrdersBean = GsonUtil.getGson().fromJson(s,ComfirmOrdersBean.class);
                    iOrderDetailPostView.onPostOrdersSuccess(comfirmOrdersBean);
                }
            }

        });
    }


}
