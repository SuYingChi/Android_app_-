package com.msht.mshtLpg.mshtLpgMaster.viewInterface;

public  interface IBaseView {
    void showLoading();

    void dismissLoading();

    void onError(String s);

    String getToken();

    /*String getEmployerId();*/

    void onLogout();

    void onNetError();
}
