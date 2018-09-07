package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.BottleReplacePriceBean;

public interface IExchangeSteelBottleView extends IBaseView {
    void onGetReplacePriceSuccess(BottleReplacePriceBean bean);

    String getBottleWeight();

    String getBottleProduceDate();

    String getCorrosionType();

    String getYear();
}
