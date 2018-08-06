package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;

public interface ISimpleOrderDetailView extends IBaseView {
    void onGetOrdersDetailSuccess(OrderDetailBean bean);
    String getOrderId();
}
