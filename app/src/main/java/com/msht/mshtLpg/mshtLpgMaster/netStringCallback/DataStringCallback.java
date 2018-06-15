package com.msht.mshtLpg.mshtLpgMaster.netStringCallback;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonInstance;
import com.msht.mshtLpg.mshtLpgMaster.netBean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IBaseView;
import com.zhy.http.okhttp.callback.StringCallback;

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

        if(!TextUtils.isEmpty(errorBean.getResult())&&TextUtils.equals(errorBean.getError(),"403")){
            iView.onLogout();
        }
        if(!TextUtils.isEmpty(errorBean.getResult())&&TextUtils.equals(errorBean.getResult(),"fail")){
            iView.onError(errorBean.getResult());
        }
    }
}
