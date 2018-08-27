package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.EmpPayBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ICashPayView;
import com.zhy.http.okhttp.OkHttpUtils;

public class ICashPayPresenter {
    private  ICashPayView iCashPayView;

    public ICashPayPresenter(ICashPayView iCashPayView){
       this.iCashPayView = iCashPayView;
    }

    public void cashPay(){
        OkHttpUtils.get().url(Constants.CASH_PAY).addParams("id", iCashPayView.getOrderId())
                .addParams("payChannel",iCashPayView.getPayChannel())
                .addParams("msbUserId",iCashPayView.getMsbUserId())
                .build().execute(new DataStringCallback(iCashPayView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
               if(isResponseEmpty){
                   iCashPayView.onError("接口返回空字符串:");
                   return;
               }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
               if ((!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail"))) {
                    iCashPayView.onError(bean.getMsg());
                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                   EmpPayBean empPayBean = GsonUtil.getGson().fromJson(s, EmpPayBean.class);
                    iCashPayView.onCashPaySuceess(empPayBean);
                }
            }
        });
    }
}
