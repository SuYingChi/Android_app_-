package com.msht.mshtLpg.mshtLpgMaster.Present;


import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.BottleReplacePriceBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IExchangeSteelBottleView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IExchangeSteelBottlePresenter {

    IExchangeSteelBottleView iExchangeSteelBottleView;
    public IExchangeSteelBottlePresenter(IExchangeSteelBottleView iExchangeSteelBottleView){
        this.iExchangeSteelBottleView = iExchangeSteelBottleView;
    }

    public void getBottleReplacePrice(){
        OkHttpUtils.get().url(Constants.QUERY_REPLACE_PRICE)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iExchangeSteelBottleView.getToken()).
                addParams("bottleWeight",iExchangeSteelBottleView.getBottleWeight()).
                addParams("bottleProduceDate",iExchangeSteelBottleView.getBottleProduceDate()).
                addParams("corrosionType",iExchangeSteelBottleView.getCorrosionType()).
                build().execute(new DataStringCallback(iExchangeSteelBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                BottleReplacePriceBean bean = GsonUtil.getGson().fromJson(s, BottleReplacePriceBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iExchangeSteelBottleView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iExchangeSteelBottleView.onGetReplacePriceSuccess(bean);
                }
            }

        });
    }
}
