package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.OrderDetailBean;

public interface ISimpleOrderDetailView extends IBaseView {
    void onGetOrdersDetailSuccess(OrderDetailBean bean);
    String getOrderId();
}
