package com.example.mshtyfb.mshtlpg.viewInterface;

public interface IBaseView {
    void showLoading();

    void dismissLoading();

    void onError(String s);

    void onSuccess(String s);

    String getToken();

}
