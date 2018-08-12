package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.EmpPayBean;

public interface ICashPayView extends IBaseView {
    String getOrderId();
    String getPayChannel();
    String getMsbUserId();
    void onCashPaySuceess(EmpPayBean empPayBean);
}
