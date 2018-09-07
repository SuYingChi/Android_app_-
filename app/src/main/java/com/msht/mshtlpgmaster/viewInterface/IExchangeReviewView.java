package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.ExchangeReviewBean;

public interface IExchangeReviewView extends IBaseView{
    void onGetExchangeReviewSuccess(ExchangeReviewBean ExchangeReviewBean);

    String getOrderId();
}
