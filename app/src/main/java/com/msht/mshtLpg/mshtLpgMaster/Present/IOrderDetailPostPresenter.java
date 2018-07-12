package com.msht.mshtLpg.mshtLpgMaster.Present;

import com.msht.mshtLpg.mshtLpgMaster.activity.OrdersDetailPostActivity;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailPostView;

public class IOrderDetailPostPresenter {
    private  IOrderDetailPostView iOrderDetailPostView;

    public IOrderDetailPostPresenter(IOrderDetailPostView iOrderDetailPostView) {
        this.iOrderDetailPostView = iOrderDetailPostView;
    }
    public void postOrders(){}
}
