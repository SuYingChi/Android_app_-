package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.GetPayQRCodeBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.GetQRcodeErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.QueryOrderBean;

public interface IGetPayQRcodeView extends IBaseView {
    void onGetQRCodeImageURLSuccess(GetPayQRCodeBean bean);
    void onGetQRCodeImageError(GetQRcodeErrorBean bean);

    void onGetOrderStatusSuccess(QueryOrderBean bean);

    String getId();

    String getOrderType();

    String getOrderId();

    String getPayType();

    String getBody();

    String getPayAmount();
}
