package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.GasAndDepositBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IBackBottleDetailPostView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrdesDespositView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IGasAndDepositPresenter {
    private  IOrdesDespositView iOrdesDespositView;

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
               if(isResponseEmpty){
                   iOrdesDespositView.onError("接口返回空字符串:");
                   return;
               }
               ErrorBean ErrorBean = GsonUtil.getGson().fromJson(s, ErrorBean.class);

               if (!TextUtils.isEmpty(ErrorBean.getResult()) && TextUtils.equals(ErrorBean.getResult(), "fail")) {
                   iOrdesDespositView.onError(ErrorBean.getMsg());

               } else if (!TextUtils.isEmpty(ErrorBean.getResult()) && TextUtils.equals(ErrorBean.getResult(), "success")) {
                   GasAndDepositBean bean = GsonUtil.getGson().fromJson(s, GasAndDepositBean.class);
                   iOrdesDespositView.onGasAndDepositGetSuccess(bean);
               }
           }

       });
    }
}
