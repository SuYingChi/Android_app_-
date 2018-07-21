package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.GetPayQRCodeBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.GetPayQRErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.QueryOrderBean;

public interface IGetPayQRcodeView extends IBaseView {
    void onGetQRCodeImageURLSuccess(GetPayQRCodeBean bean);
    void onGetQRCodeImageURLError(GetPayQRErrorBean bean);

    void onGetOrderStatusSuccess(QueryOrderBean bean);

    String getOrderId();

    String getPayType();

    String getBody();

    String getPayAmount();

    android.os.Handler getHandler();
}
