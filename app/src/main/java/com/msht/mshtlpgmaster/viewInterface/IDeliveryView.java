package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.DeliveryBean;

public interface IDeliveryView extends IBaseView{
    void  onGetDeliverySuccess(DeliveryBean bean);

    String getFloors();

}
