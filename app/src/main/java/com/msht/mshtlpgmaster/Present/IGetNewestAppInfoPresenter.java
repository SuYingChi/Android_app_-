package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.AppInfoBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IUpdateVersionView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IGetNewestAppInfoPresenter {

    private IUpdateVersionView iUpdateVersionView;

    public IGetNewestAppInfoPresenter(IUpdateVersionView iUpdateVersionView) {
        this.iUpdateVersionView = iUpdateVersionView;

    }

    public void getNewestAppInfo() {
        OkHttpUtils.get().url(Constants.GET_NEWESTAPPINFO).tag(iUpdateVersionView).build().execute(new DataStringCallback(iUpdateVersionView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iUpdateVersionView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if ((!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail"))) {
                    iUpdateVersionView.onError(bean.getMsg());
                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    try{
                    AppInfoBean appInfoBean = GsonUtil.getGson().fromJson(s, AppInfoBean.class);
                    iUpdateVersionView.onGetNewestAppInfoSuccess(appInfoBean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }

            }
            }
        });
    }
}
