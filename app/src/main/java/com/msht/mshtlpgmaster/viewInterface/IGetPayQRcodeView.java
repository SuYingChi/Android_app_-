package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.GetPayQRCodeBean;
import com.msht.mshtlpgmaster.Bean.GetPayQRErrorBean;
import com.msht.mshtlpgmaster.Bean.QueryOrderBean;
import com.msht.mshtlpgmaster.Bean.WxcodePayErroBean;

public interface IGetPayQRcodeView extends IBaseView {
    void onGetQRCodeImageURLSuccess(GetPayQRCodeBean bean);
    void onGetQRCodeImageURLError(WxcodePayErroBean bean);

    void onGetOrderStatusSuccess(QueryOrderBean bean);

    String getOrderId();

    String getPayType();

    String getBody();

    String getPayAmount();

    android.os.Handler getHandler();
}
