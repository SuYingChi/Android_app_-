package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.UserLoginBean;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IBaseView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ILoginView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ILogoutView;
import com.zhy.http.okhttp.OkHttpUtils;

public class ILogoutPresenter {

    private IBaseView iView;

    public ILogoutPresenter(IBaseView iView) {
        this.iView = iView;
    }

    public void logout() {
        if(iView.getToken().length()==0){
            PopUtil.toastInBottom("未登录");
            return;
        }
        OkHttpUtils.get().url(Constants.LOGOUT).addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iView.getToken()).build().execute(new DataStringCallback(iView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if(isResponseEmpty){
                    iView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                 if ((!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail"))) {
                    iView.onError(bean.getMsg());
                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                      iView.onLogout();
                }
            }
        });
    }
}
