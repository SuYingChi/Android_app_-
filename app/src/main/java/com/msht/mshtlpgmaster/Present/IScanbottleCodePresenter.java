package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.VerifyBottleBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IBackBottleView;
import com.msht.mshtlpgmaster.viewInterface.IScanCodeDeliverSteelBottleView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IScanbottleCodePresenter {
    private IBackBottleView iBackBottleView;
    private IScanCodeDeliverSteelBottleView iScanCodeDeliverSteelBottleView;

    public IScanbottleCodePresenter(IScanCodeDeliverSteelBottleView iScanCodeDeliverSteelBottleView) {
        this.iScanCodeDeliverSteelBottleView = iScanCodeDeliverSteelBottleView;
    }

    public IScanbottleCodePresenter(IBackBottleView iBackBottleView) {
        this.iBackBottleView = iBackBottleView;
    }

    public void queryBottleByQRCode() {
        OkHttpUtils.get().url(Constants.VERIFY_BOTTLE_BY_QR_CODE).addParams(Constants.URL_PARAMS_BOTTLE_CODE, iScanCodeDeliverSteelBottleView.getBottleCode()).
                tag(iScanCodeDeliverSteelBottleView).addParams(Constants.URL_PARAMS_VERIFYTYPE, iScanCodeDeliverSteelBottleView.getVerifyType()).addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iScanCodeDeliverSteelBottleView.getToken()).build().execute(new DataStringCallback(iScanCodeDeliverSteelBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                ErrorBean ErrorBean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(ErrorBean.getResult()) && TextUtils.equals(ErrorBean.getResult(), "fail")) {
                    iScanCodeDeliverSteelBottleView.onError(ErrorBean.getMsg());

                } else if (!TextUtils.isEmpty(ErrorBean.getResult()) && TextUtils.equals(ErrorBean.getResult(), "success")) {
                    try{
                    VerifyBottleBean bean = GsonUtil.getGson().fromJson(s, VerifyBottleBean.class);
                    iScanCodeDeliverSteelBottleView.onGetBottleInfoSuccess(bean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }
                }
            }

        });
    }

    public void queryBackBottleByQRCode() {
        OkHttpUtils.get().url(Constants.VERIFY_BOTTLE_BY_QR_CODE).addParams(Constants.URL_PARAMS_BOTTLE_CODE, iBackBottleView.getBottleCode()).
                tag(iBackBottleView).addParams(Constants.URL_PARAMS_VERIFYTYPE, iBackBottleView.getVerifyType()).addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iBackBottleView.getToken()).build().execute(new DataStringCallback(iBackBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iBackBottleView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean errorBean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(errorBean.getResult()) && TextUtils.equals(errorBean.getResult(), "fail")) {
                    iBackBottleView.onError(errorBean.getMsg());

                } else if (!TextUtils.isEmpty(errorBean.getResult()) && TextUtils.equals(errorBean.getResult(), "success")) {
                    try{
                    VerifyBottleBean bean = GsonUtil.getGson().fromJson(s, VerifyBottleBean.class);
                    iBackBottleView.onGetBottleInfoSuccess(bean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }
                }
            }

        });
    }
}
