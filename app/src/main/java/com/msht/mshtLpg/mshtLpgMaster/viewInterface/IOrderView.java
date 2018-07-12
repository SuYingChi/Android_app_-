package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersBean;

public interface IOrderView extends IBaseView{

    int getPage();

    int getOrdersType();

    int getOrdersStatus();

    void onGetOrdersSuccess(OrdersBean s);

}
