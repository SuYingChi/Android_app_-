package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.ComfirmOrdersBean;

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
