package com.msht.mshtyLpg.mshtLpgUser.viewInterface;

public interface IBaseView {
    void showLoading();

    void dismissLoading();

    void onError(String s);

    void onSuccess(String s);

    String getToken();

}
