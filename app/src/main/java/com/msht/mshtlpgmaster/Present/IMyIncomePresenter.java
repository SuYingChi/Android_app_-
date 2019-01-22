package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.Bean.MyIncomeBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IMyIncomeView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IMyIncomePresenter {
    private IMyIncomeView iMyIncomeView;

    public IMyIncomePresenter(IMyIncomeView iMyIncomeView) {
        this.iMyIncomeView = iMyIncomeView;
    }

    public void getMyIncome() {
        OkHttpUtils.get().url(Constants.MY_INCOME)
               .tag(iMyIncomeView) .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iMyIncomeView.getToken()).
                build().execute(new DataStringCallback(iMyIncomeView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iMyIncomeView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iMyIncomeView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    try{
                    MyIncomeBean myIncomeBean = GsonUtil.getGson().fromJson(s, MyIncomeBean.class);
                    iMyIncomeView.onGetMyIncomeSuccess(myIncomeBean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }
                }
            }

        });

    }

}
