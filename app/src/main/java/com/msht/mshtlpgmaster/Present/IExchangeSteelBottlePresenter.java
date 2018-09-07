package com.msht.mshtlpgmaster.Present;


import android.text.TextUtils;

import com.msht.mshtlpgmaster.Bean.BottleReplacePriceBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.viewInterface.IExchangeSteelBottleView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IExchangeSteelBottlePresenter {

    IExchangeSteelBottleView iExchangeSteelBottleView;

    public IExchangeSteelBottlePresenter(IExchangeSteelBottleView iExchangeSteelBottleView) {
        this.iExchangeSteelBottleView = iExchangeSteelBottleView;
    }

    public void getBottleReplacePrice() {
        OkHttpUtils.get().url(Constants.QUERY_REPLACE_PRICE)
                .addParams("token", iExchangeSteelBottleView.getToken()).
                addParams("bottleWeight", iExchangeSteelBottleView.getBottleWeight()).
                addParams("corrosionType", iExchangeSteelBottleView.getCorrosionType()).
                addParams("years", iExchangeSteelBottleView.getYear()).
                build().execute(new DataStringCallback(iExchangeSteelBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iExchangeSteelBottleView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iExchangeSteelBottleView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    BottleReplacePriceBean bottleReplacePriceBean = GsonUtil.getGson().fromJson(s, BottleReplacePriceBean.class);
                    iExchangeSteelBottleView.onGetReplacePriceSuccess(bottleReplacePriceBean);
                }
            }

        });
    }

    public void getinitBottleReplacePrice() {
        OkHttpUtils.get().url(Constants.QUERY_REPLACE_PRICE)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iExchangeSteelBottleView.getToken()).
                addParams("bottleWeight", "5").
                addParams("bottleProduceDate", "1").
                addParams("corrosionType", "A").
                build().execute(new DataStringCallback(iExchangeSteelBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iExchangeSteelBottleView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean ErrorBean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(ErrorBean.getResult()) && TextUtils.equals(ErrorBean.getResult(), "fail")) {
                    iExchangeSteelBottleView.onError(ErrorBean.getMsg());

                } else if (!TextUtils.isEmpty(ErrorBean.getResult()) && TextUtils.equals(ErrorBean.getResult(), "success")) {
                    BottleReplacePriceBean bean = GsonUtil.getGson().fromJson(s, BottleReplacePriceBean.class);
                    iExchangeSteelBottleView.onGetReplacePriceSuccess(bean);
                }
            }

        });
    }
}
