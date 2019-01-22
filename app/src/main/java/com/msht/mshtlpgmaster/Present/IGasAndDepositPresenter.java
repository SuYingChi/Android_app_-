package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.GasAndDepositBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IOrdesDespositView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;

public class IGasAndDepositPresenter {
    private IOrdesDespositView iOrdesDespositView;

    public IGasAndDepositPresenter(IOrdesDespositView iOrdesDespositView) {
        this.iOrdesDespositView = iOrdesDespositView;
    }

    public void getGasAndDeposit() {
        PostFormBuilder s = OkHttpUtils.post().url(Constants.GET_GAS_AND_DEPOSIT).addParams("siteId", iOrdesDespositView.getSiteId());
        OkHttpUtils.get().url(Constants.GET_GAS_AND_DEPOSIT).addParams("siteId", iOrdesDespositView.getSiteId())
                .tag(iOrdesDespositView).build().execute(new DataStringCallback(iOrdesDespositView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iOrdesDespositView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean ErrorBean = GsonUtil.getGson().fromJson(s, ErrorBean.class);

                if (!TextUtils.isEmpty(ErrorBean.getResult()) && TextUtils.equals(ErrorBean.getResult(), "fail")) {
                    iOrdesDespositView.onError(ErrorBean.getMsg());

                } else if (!TextUtils.isEmpty(ErrorBean.getResult()) && TextUtils.equals(ErrorBean.getResult(), "success")) {
                    try{
                    GasAndDepositBean bean = GsonUtil.getGson().fromJson(s, GasAndDepositBean.class);
                    iOrdesDespositView.onGasAndDepositGetSuccess(bean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }

            }
            }

        });
    }
}
