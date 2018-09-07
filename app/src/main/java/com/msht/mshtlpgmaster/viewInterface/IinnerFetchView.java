package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.ScanInnerFetchBottleBean;
import com.msht.mshtlpgmaster.Bean.VerifyBottleBean;

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
