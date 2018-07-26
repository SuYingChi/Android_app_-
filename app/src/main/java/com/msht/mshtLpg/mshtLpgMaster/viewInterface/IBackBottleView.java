package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;

public interface IBackBottleView extends IBaseView{
    void onGetBottleInfoSuccess(VerifyBottleBean verifyBottleBean);
    String getBottleCode();

    String getVerifyType();
}
