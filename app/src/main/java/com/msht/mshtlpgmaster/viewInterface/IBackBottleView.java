package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.VerifyBottleBean;

public interface IBackBottleView extends IBaseView{
    void onGetBottleInfoSuccess(VerifyBottleBean verifyBottleBean);
    String getBottleCode();

    String getVerifyType();
}
