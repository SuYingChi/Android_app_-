package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;

public interface IScanCodeDeliverSteelBottleView extends IBaseView {
    void onGetBottleInfoSuccess(VerifyBottleBean verifyBottleBean);
    String getBottleCode();
    int getFragmentType();

    String getVerifyType();
}
