package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IBaseView;
import com.zhy.http.okhttp.OkHttpUtils;

public class ILogoutPresenter {

    private IBaseView iView;

    public ILogoutPresenter(IBaseView iView) {
        this.iView = iView;
    }

    public void logout() {
        if (iView.getToken().length() == 0) {
            PopUtil.toastInBottom("未登录");
            return;
        }
        OkHttpUtils.get().url(Constants.LOGOUT).tag(iView).addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iView.getToken()).build().execute(new DataStringCallback(iView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if ((!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail"))) {
                    iView.onError(bean.getMsg());
                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    try{
                    iView.onLogout();
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }

            }
            }
        });
    }
}
