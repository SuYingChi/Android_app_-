package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.Bean.MonthCountBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IMonthCountView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IMonthCountPresenter {
    private IMonthCountView iMonthCountView;

    public IMonthCountPresenter(IMonthCountView iMonthCountView) {
        this.iMonthCountView = iMonthCountView;
    }

    public void getMonthCount() {
        OkHttpUtils.get().url(Constants.MONTH_COUNT)
                .tag(iMonthCountView).addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iMonthCountView.getToken()).
                build().execute(new DataStringCallback(iMonthCountView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iMonthCountView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iMonthCountView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    try {
                        MonthCountBean monthCountBean = GsonUtil.getGson().fromJson(s, MonthCountBean.class);
                        iMonthCountView.onGetMonthCountSuccess(monthCountBean);
                    }catch (JsonSyntaxException e){
                            PopUtil.toastInBottom("GSON转换异常");
                        }
                }
            }

        });
    }
}
