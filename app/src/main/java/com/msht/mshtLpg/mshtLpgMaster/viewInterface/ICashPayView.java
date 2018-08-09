package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.CashPayBean;

public interface ICashPayView extends IBaseView {
    String getOrderId();
    String getPayChannel();
    String getMsbUserId();
    void onCashPaySuceess(CashPayBean cashPayBean);
}
