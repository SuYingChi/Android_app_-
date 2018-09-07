package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.VerifyBottleBean;

public interface IScanCodeDeliverSteelBottleView extends IBaseView {
    void onGetBottleInfoSuccess(VerifyBottleBean verifyBottleBean);
    String getBottleCode();
    int getFragmentType();

    String getVerifyType();

}
