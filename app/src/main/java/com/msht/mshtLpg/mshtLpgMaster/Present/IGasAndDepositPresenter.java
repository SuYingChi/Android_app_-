package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.GasAndDepositBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.activity.OrdersDetailPostActivity;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailPostView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrdesDespositView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IGasAndDepositPresenter {
    private final IOrdesDespositView iOrdesDespositView;

    public IGasAndDepositPresenter(IOrdesDespositView iOrdesDespositView) {
        this.iOrdesDespositView =iOrdesDespositView;
    }
   public  void getGasAndDeposit(){
       OkHttpUtils.get().url(Constants.GET_GAS_AND_DEPOSIT).addParams("siteId", iOrdesDespositView.getSiteId())
               .build().execute(new DataStringCallback(iOrdesDespositView) {
           @Override
           public void onResponse(String s, int i) {
               //先继承再重写或重写覆盖请求错误的场景
               super.onResponse(s, i);
               GasAndDepositBean bean = GsonUtil.getGson().fromJson(s, GasAndDepositBean.class);
               if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                   iOrdesDespositView.onError(bean.getMsg());

               } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                   iOrdesDespositView.onGasAndDepositGetSuccess(bean);
               }
           }

       });
    }
}
