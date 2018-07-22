package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ScanInnerFetchBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyEmployerBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IinnerFetchView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IInnerFetchPresenter {

    private IinnerFetchView iinnerFetchView;


    public IInnerFetchPresenter(IinnerFetchView iinnerFetchView){
        this.iinnerFetchView = iinnerFetchView;
    }

    public void  innerFetchComfirm() {
        OkHttpUtils.get().url(Constants.INNER_FETCH).addParams("employeeCode", iinnerFetchView.getEmployeeCode()).addParams("bottleIds", iinnerFetchView.getBottleIds()).addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iinnerFetchView.getToken()).build().execute(new DataStringCallback(iinnerFetchView) {
            @Override
            public void onResponse(String s, int i) {
                super.onResponse(s, i);
                ScanInnerFetchBottleBean bean = GsonUtil.getGson().fromJson(s, ScanInnerFetchBottleBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iinnerFetchView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iinnerFetchView.onInnerFetchComfirmSuccess(bean);
                }
            }
        });
    }
    public void innerFetchQueryBottle(){
        OkHttpUtils.get().url(Constants.VERIFY_BOTTLE_BY_QR_CODE).addParams(Constants.URL_PARAMS_BOTTLE_CODE,iinnerFetchView.getBottleCode()).
                addParams(Constants.URL_PARAMS_VERIFYTYPE,iinnerFetchView.getVerifyType()).addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iinnerFetchView.getToken()).build().execute(new DataStringCallback(iinnerFetchView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                VerifyBottleBean bean = GsonUtil.getGson().fromJson(s, VerifyBottleBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iinnerFetchView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iinnerFetchView.onGetInnerFetchBottleInfoSuccess(bean);
                }
            }

        });

    }
    public void innerFetchQueryEmpolyer(){
        OkHttpUtils.get().url("").addParams("employeeCode", iinnerFetchView.getEmployeeCode()).addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iinnerFetchView.getToken()).build().execute(new DataStringCallback(iinnerFetchView) {
            @Override
            public void onResponse(String s, int i) {
                super.onResponse(s, i);
                VerifyEmployerBean bean = GsonUtil.getGson().fromJson(s, VerifyEmployerBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iinnerFetchView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iinnerFetchView.onGetEmployerInfoSuccess("");
                }
            }
        });
    }
}