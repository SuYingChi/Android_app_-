package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;

public interface IOrderDetailView extends IBaseView{
    void onGetOrdersDetailSuccess(OrderDetailBean bean);
    int getOrderId();
}
