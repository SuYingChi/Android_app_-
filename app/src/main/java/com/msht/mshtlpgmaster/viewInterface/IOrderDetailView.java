package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.DeliveryBean;
import com.msht.mshtlpgmaster.Bean.OrderDetailBean;

public interface IOrderDetailView extends IBaseView{
    void onGetOrdersDetailSuccess(OrderDetailBean bean);
    String getOrderId();

    void onGetFourDeliverySuccess(DeliveryBean bean);

    void onGetSixDeliverySuccess(DeliveryBean bean);

    void onGetFirstDeliverySuccess(DeliveryBean bean);

    void onGetSecondDeliverySuccess(DeliveryBean bean);
}
