package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.BottleReplacePriceBean;

public interface IExchangeSteelBottleView extends IBaseView {
    void onGetReplacePriceSuccess(BottleReplacePriceBean bean);

    String getBottleWeight();

    String getBottleProduceDate();

    String getCorrosionType();

    String getYear();
}
