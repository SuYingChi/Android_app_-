package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ExchangeReviewBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyEmployerBean;
import com.msht.mshtLpg.mshtLpgMaster.activity.ExchangeReviewActivity;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IExchangeReviewView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IExchangeReviewPresenter {
    private  IExchangeReviewView iExchangeReviewView;

    public IExchangeReviewPresenter(IExchangeReviewView iExchangeReviewView) {
        this.iExchangeReviewView = iExchangeReviewView;
    }

    public void getExchangeReview(){
        OkHttpUtils.get().url(Constants.REPLACE_BOTTLE_LIST)
                .addParams(Constants.ORDER_ID,iExchangeReviewView.getOrderId() ).
                build().execute(new DataStringCallback(iExchangeReviewView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                VerifyEmployerBean bean = GsonUtil.getGson().fromJson(s, VerifyEmployerBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iExchangeReviewView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    ExchangeReviewBean exchangeReviewBean = GsonUtil.getGson().fromJson(s, ExchangeReviewBean.class);
                    iExchangeReviewView.onGetExchangeReviewSuccess(exchangeReviewBean);
                }
            }

        });
    }
}
