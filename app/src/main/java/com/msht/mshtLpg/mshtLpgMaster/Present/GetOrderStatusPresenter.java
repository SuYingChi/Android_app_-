package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.QueryOrderBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IGetPayQRcodeView;
import com.zhy.http.okhttp.OkHttpUtils;

public class GetOrderStatusPresenter {
    IGetPayQRcodeView iGetPayQRcodeView;

    public GetOrderStatusPresenter(IGetPayQRcodeView iGetPayQRcodeView) {
        this.iGetPayQRcodeView = iGetPayQRcodeView;
    }

    public void getOrderStatus() {
        OkHttpUtils.get().url(Constants.QUERY_ORDER_DETAIL_BY_ORDER_ID).addParams("id", iGetPayQRcodeView.getOrderId())
                .addParams("orderType",iGetPayQRcodeView.getOrderType()).build().execute(new DataStringCallback(iGetPayQRcodeView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                    QueryOrderBean bean = GsonUtil.getGson().fromJson(s, QueryOrderBean.class);
                    if(TextUtils.equals(bean.getResult(),"success")){

                       iGetPayQRcodeView.getHandler().post(new Runnable() {
                           @Override
                           public void run() {
                               iGetPayQRcodeView.onGetOrderStatusSuccess(bean);
                          }
                       }) ;
                    }
            }

        });
    }
}
