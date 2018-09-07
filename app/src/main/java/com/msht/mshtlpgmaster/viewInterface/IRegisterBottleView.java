package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.Bean.GetBottleInfo;

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
