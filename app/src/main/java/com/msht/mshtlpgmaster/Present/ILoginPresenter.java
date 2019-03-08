package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.UserErrorBean;
import com.msht.mshtlpgmaster.Bean.UserLoginBean;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.application.LPGApplication;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.ILoginView;
import com.zhy.http.okhttp.OkHttpUtils;

import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.callback.DataStringCallback;

public class ILoginPresenter {

    private ILoginView iView;

    public ILoginPresenter(ILoginView iView) {
        this.iView = iView;
    }

    public void login(String mobileNumber, String password) {
        OkHttpUtils.get().url(Constants.LOGIN).addParams("mobile", mobileNumber).tag(iView).addParams("password", password).build().execute(new DataStringCallback(iView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iView.onError("接口返回空字符串:");
                    return;
                }
                UserErrorBean bean = GsonUtil.getGson().fromJson(s, UserErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "failed")) {
                    switch (bean.getData().getErrorCode()) {
                        case "100":
                            iView.onError(bean.getMsg());
                            break;
                        case "101":
                            iView.onError(bean.getMsg());
                            break;
                        case "102":
                            iView.onError(bean.getMsg());
                            break;
                        default:
                            iView.onError(LPGApplication.getLPGApplicationContext().getResources().getString(R.string.login_unknown_error));
                            iView.onLogout();
                            break;
                    }
                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    if (TextUtils.equals(bean.getData().getErrorCode(), "200")) {
                        try{
                        UserLoginBean userLoginBean = GsonUtil.getGson().fromJson(s, UserLoginBean.class);
                        iView.onLoginSuccess(userLoginBean);
                    }catch (JsonSyntaxException e){
                        PopUtil.toastInBottom("GSON转换异常");
                    }

                }
                }
            }
        });
    }
}
