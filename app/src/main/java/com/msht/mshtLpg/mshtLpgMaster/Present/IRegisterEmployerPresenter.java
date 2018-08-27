package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.RegisterBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IRegisterEmployerView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IRegisterEmployerPresenter {
    private  IRegisterEmployerView iRegisterEmployerView;

    public IRegisterEmployerPresenter(IRegisterEmployerView iRegisterEmployerView) {
        this.iRegisterEmployerView = iRegisterEmployerView;
    }

    public void postRegisterEmployer() {
        OkHttpUtils.get().url(Constants.REGISTER_EMPLOYER)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iRegisterEmployerView.getToken()).
                addParams("",iRegisterEmployerView.getName())
                .addParams("",iRegisterEmployerView.getSex())
                .addParams("",iRegisterEmployerView.getMobile())
                .addParams("",iRegisterEmployerView.getFloor())
                .addParams("",iRegisterEmployerView.getIsElevator())
                .addParams("",iRegisterEmployerView.getRoom())
                .addParams("",iRegisterEmployerView.getLocation())
                .addParams("",iRegisterEmployerView.getLatitude())
                .addParams("",iRegisterEmployerView.getLongitude())
                .build().execute(new DataStringCallback(iRegisterEmployerView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if(isResponseEmpty){
                    iRegisterEmployerView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iRegisterEmployerView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    RegisterBean registerBean = GsonUtil.getGson().fromJson(s, RegisterBean.class);
                    iRegisterEmployerView.onRegisterSuccess(registerBean);
                }
            }

        });
    }
}
