package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.OrdersListBeanV2;

public interface IOrderView extends IBaseView{

    int getPage();

    int getOrdersStatus();

    void onGetOrdersSuccess(OrdersListBeanV2 bean);

}
