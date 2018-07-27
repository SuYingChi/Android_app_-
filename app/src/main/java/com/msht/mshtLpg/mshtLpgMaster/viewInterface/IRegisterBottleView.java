package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.GetBottleInfo;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ScanInnerFetchBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.UpdateBottleInfo;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;

public interface IRegisterBottleView extends IBaseView{


   void onGetBottleInfoSuccess(GetBottleInfo getBottleInfoBean);

   void onUpdateBottleInfoSuccess(ErrorBean errorBean);

   String getBottleCode();

   String getBottleNum();

   String getBottleWeight();

   String getProducer();

   String getPropertyUnit();

   String getCreateTime();

   String getNextCheckTime();
}
