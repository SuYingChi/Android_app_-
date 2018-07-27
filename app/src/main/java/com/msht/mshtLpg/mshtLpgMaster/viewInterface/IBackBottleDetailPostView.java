package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ComfirmOrdersBean;

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
