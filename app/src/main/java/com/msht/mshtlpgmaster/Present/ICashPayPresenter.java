package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.EmpPayBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.ICashPayView;
import com.zhy.http.okhttp.OkHttpUtils;

public class ICashPayPresenter {
    private ICashPayView iCashPayView;

    public ICashPayPresenter(ICashPayView iCashPayView) {
        this.iCashPayView = iCashPayView;
    }

    public void cashPay() {
        OkHttpUtils.get().url(Constants.CASH_PAY).addParams("id", iCashPayView.getOrderId())
                .addParams("payChannel", iCashPayView.getPayChannel())
                .addParams("msbUserId", iCashPayView.getMsbUserId())
                .build().execute(new DataStringCallback(iCashPayView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iCashPayView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if ((!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail"))) {
                    iCashPayView.onError(bean.getMsg());
                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    try{
                    EmpPayBean empPayBean = GsonUtil.getGson().fromJson(s, EmpPayBean.class);
                    iCashPayView.onCashPaySuceess(empPayBean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }

            }
            }
        });
    }
}
