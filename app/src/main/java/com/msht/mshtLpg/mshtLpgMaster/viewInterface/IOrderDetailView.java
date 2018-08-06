package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.DeliveryBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;

public interface IOrderDetailView extends IBaseView{
    void onGetOrdersDetailSuccess(OrderDetailBean bean);
    String getOrderId();

    void onGetFourDeliverySuccess(DeliveryBean bean);

    void onGetSixDeliverySuccess(DeliveryBean bean);

    void onGetFirstDeliverySuccess(DeliveryBean bean);
}
