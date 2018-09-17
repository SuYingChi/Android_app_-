package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.ExchangeReviewBean;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IExchangeReviewView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IExchangeReviewPresenter {
    private IExchangeReviewView iExchangeReviewView;

    public IExchangeReviewPresenter(IExchangeReviewView iExchangeReviewView) {
        this.iExchangeReviewView = iExchangeReviewView;
    }

    public void getExchangeReview() {
        OkHttpUtils.get().url(Constants.REPLACE_BOTTLE_LIST)
                .addParams(Constants.ORDER_ID, iExchangeReviewView.getOrderId()).
                build().execute(new DataStringCallback(iExchangeReviewView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iExchangeReviewView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iExchangeReviewView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    try{
                    ExchangeReviewBean exchangeReviewBean = GsonUtil.getGson().fromJson(s, ExchangeReviewBean.class);
                    iExchangeReviewView.onGetExchangeReviewSuccess(exchangeReviewBean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }

            }
            }

        });
    }
}
