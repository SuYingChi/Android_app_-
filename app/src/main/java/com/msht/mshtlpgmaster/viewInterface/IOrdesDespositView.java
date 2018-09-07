package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.GasAndDepositBean;

public interface IOrdesDespositView extends IBaseView{
    void onGasAndDepositGetSuccess(GasAndDepositBean bean);

    String getSiteId();
}
