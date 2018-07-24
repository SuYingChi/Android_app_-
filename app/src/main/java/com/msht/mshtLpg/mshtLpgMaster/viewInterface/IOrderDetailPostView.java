package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ComfirmOrdersBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;

public interface IOrderDetailPostView extends IBaseView{
   void onPostOrdersSuccess(ComfirmOrdersBean bean);

   String getOrderId();

   String getIsDelivery();

   String getReFiveBottleCount();

   String getReFifteenBottleCount();

   String getReFiftyBottleCount();

   String getFiveBottleCount();

   String getFifteenBottleCount();

   String getFiftyBottleCount();

   String getDeliveryBottleIds();

   String getFloor();

   String getRecycleBottleIds();

   String getReplaceBottleStr();

    String getIsElevator();
}
