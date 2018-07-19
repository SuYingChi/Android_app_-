package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ExchangeReviewBean;

public interface IExchangeReviewView extends IBaseView{
    void onGetExchangeReviewSuccess(ExchangeReviewBean ExchangeReviewBean);

    String getOrderId();
}
