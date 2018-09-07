package com.msht.mshtlpgmaster.viewInterface;

import com.msht.mshtlpgmaster.Bean.RegisterBean;

public interface IRegisterEmployerView extends IBaseView{

   void onRegisterSuccess(RegisterBean registerBean);

   String getName();

   String getSex();

   String getMobile();

   String getLocation();


   String getLatitude();

   String getLongitude();

   String getIsElevator();

   String getRoom();

   String getFloor();



}
