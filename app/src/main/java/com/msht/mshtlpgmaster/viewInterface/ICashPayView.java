package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.EmpPayBean;

public interface ICashPayView extends IBaseView {
    String getOrderId();
    String getPayChannel();
    String getMsbUserId();
    void onCashPaySuceess(EmpPayBean empPayBean);
}
