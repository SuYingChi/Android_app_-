package com.example.mshtyfb.mshtlpg.netStringCallback;

import android.text.TextUtils;

import com.zhy.http.okhttp.callback.StringCallback;

import com.example.mshtyfb.mshtlpg.gsonInstance.GsonInstance;
import com.example.mshtyfb.mshtlpg.netBean.ErrorBean;
import com.example.mshtyfb.mshtlpg.viewInterface.IBaseView;

public class DataStringCallback extends StringCallback {


    private  IBaseView iView;

   public DataStringCallback(IBaseView iView){
     this.iView = iView;
    }

    @Override
    public void onError(okhttp3.Call call, Exception e, int i) {
        e.printStackTrace();
        iView.onError(e.toString());
    }

    @Override
    public void onResponse(String s, int i) {
        ErrorBean errorBean = GsonInstance.getGsonInstance().getGson().fromJson(s, ErrorBean.class);
        if(!TextUtils.isEmpty(errorBean.getResult())&&TextUtils.equals(errorBean.getResult(),"fail")){
            iView.onError(errorBean.getResult());
        }
    }
}
