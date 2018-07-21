package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersListBeanV2;

public interface IOrderView extends IBaseView{

    int getPage();

    int getOrdersStatus();

    void onGetOrdersSuccess(OrdersListBeanV2 bean);

}
