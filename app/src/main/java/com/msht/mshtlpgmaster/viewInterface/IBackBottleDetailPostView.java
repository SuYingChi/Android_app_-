package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.ComfirmOrdersBean;

public interface IBackBottleDetailPostView extends IBaseView {
    String getIsDelivery();

    String getReFiveBottleCount();

    String getReFifteenBottleCount();

    String getReFiftyBottleCount();

    String getRecycleBottleIds();

    String getFloor();

    String getIsElevator();

    void onPostOrdersSuccess(ComfirmOrdersBean comfirmOrdersBean);

    String getOrderId();
}
