package com.msht.mshtyLpg.mshtLpgUser.netRequestPresent;

import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;

import com.msht.mshtyLpg.mshtLpgUser.gsonInstance.GsonInstance;
import com.msht.mshtyLpg.mshtLpgUser.netBean.ErrorBean;
import com.msht.mshtyLpg.mshtLpgUser.netStringCallback.DataStringCallback;
import com.msht.mshtyLpg.mshtLpgUser.viewInterface.IBaseView;

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
