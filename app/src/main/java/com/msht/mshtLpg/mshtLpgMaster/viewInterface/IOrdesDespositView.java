package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.GasAndDepositBean;

public interface IOrdesDespositView extends IBaseView{
    void onGasAndDepositGetSuccess(GasAndDepositBean bean);

    String getSiteId();
}
