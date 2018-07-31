package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.InnerFetchVerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ScanInnerFetchBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;

public interface IinnerFetchView extends IBaseView{

   void onInnerFetchComfirmSuccess(ScanInnerFetchBottleBean bean);


   String getEmployeeCode();

   String getBottleIds();

   void onGetInnerFetchBottleInfoSuccess(VerifyBottleBean verifyBottleBean);

   void onGetEmployerInfoSuccess();

   String getBottleCode();

    String getVerifyType();

    String getUrl();

    int getInnerType();

    String getSiteId();
}
