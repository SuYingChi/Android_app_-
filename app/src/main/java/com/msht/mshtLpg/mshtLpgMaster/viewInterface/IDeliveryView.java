package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.DeliveryBean;

public interface IDeliveryView extends IBaseView{
    void  onGetDeliverySuccess(DeliveryBean bean);

    String getFloors();

}
