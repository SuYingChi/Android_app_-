package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ILoginView;
import com.zhy.http.okhttp.OkHttpUtils;

import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;

public class UserRequestVerificationCodePresenter implements BaseNetRequestPresenter {

    ILoginView iView;

    public UserRequestVerificationCodePresenter(ILoginView iView){
     this.iView = iView;
    }

    @Override
    public void requestNetWork() {
        OkHttpUtils.post().url("").addParams("","").build().execute(new DataStringCallback(iView){
            @Override
            public void onResponse(String s, int i) {
                //一定要先继承再重写
                super.onResponse(s, i);
                ErrorBean errorBean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if(!TextUtils.isEmpty(errorBean.getResult())&&TextUtils.equals(errorBean.getResult(),"success")){
                    iView.onNetRequestSuccess(s);
                }
            }
        });
    }
}
