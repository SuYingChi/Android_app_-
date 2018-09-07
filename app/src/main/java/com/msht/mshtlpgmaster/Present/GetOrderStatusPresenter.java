package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.msht.mshtlpgmaster.Bean.QueryOrderBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.viewInterface.IGetPayQRcodeView;
import com.zhy.http.okhttp.OkHttpUtils;

public class GetOrderStatusPresenter {
    IGetPayQRcodeView iGetPayQRcodeView;

    public GetOrderStatusPresenter(IGetPayQRcodeView iGetPayQRcodeView) {
        this.iGetPayQRcodeView = iGetPayQRcodeView;
    }

    public void getOrderStatus() {
        OkHttpUtils.get().url(Constants.QUERY_ORDER_DETAIL_BY_ORDER_ID).addParams("id", iGetPayQRcodeView.getOrderId())
                .addParams("orderType", iGetPayQRcodeView.getOrderType()).build().execute(new DataStringCallback(iGetPayQRcodeView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iGetPayQRcodeView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (TextUtils.equals(bean.getResult(), "success")) {
                    QueryOrderBean queryOrderBean = GsonUtil.getGson().fromJson(s, QueryOrderBean.class);
                    iGetPayQRcodeView.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            iGetPayQRcodeView.onGetOrderStatusSuccess(queryOrderBean);
                        }
                    });
                }
            }

        });
    }
}
