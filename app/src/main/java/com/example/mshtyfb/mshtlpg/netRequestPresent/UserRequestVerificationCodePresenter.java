package com.example.mshtyfb.mshtlpg.netRequestPresent;

import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;

import com.example.mshtyfb.mshtlpg.gsonInstance.GsonInstance;
import com.example.mshtyfb.mshtlpg.netBean.ErrorBean;
import com.example.mshtyfb.mshtlpg.netStringCallback.DataStringCallback;
import com.example.mshtyfb.mshtlpg.viewInterface.IBaseView;

public class UserRequestVerificationCodePresenter implements BaseNetRequestPresenter {

    IBaseView iView;

    UserRequestVerificationCodePresenter(IBaseView iView){
     this.iView = iView;
    }

    @Override
    public void requestNetWork() {
        OkHttpUtils.post().url("").addParams("","").build().execute(new DataStringCallback(iView){
            @Override
            public void onResponse(String s, int i) {
                super.onResponse(s, i);
                ErrorBean errorBean = GsonInstance.getGsonInstance().getGson().fromJson(s, ErrorBean.class);
                if(!TextUtils.isEmpty(errorBean.getResult())&&TextUtils.equals(errorBean.getResult(),"success")){
                    iView.onSuccess(s);
                }
            }
        });
    }
}
