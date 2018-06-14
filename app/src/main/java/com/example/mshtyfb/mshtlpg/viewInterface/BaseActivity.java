package com.example.mshtyfb.mshtlpg.viewInterface;

import android.app.Activity;

public class BaseActivity extends Activity implements IBaseView{
    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }


    @Override
    public void onError(String s) {

    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public String getToken() {
        return null;
    }
}
