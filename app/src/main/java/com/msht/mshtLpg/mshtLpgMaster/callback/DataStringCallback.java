package com.msht.mshtLpg.mshtLpgMaster.callback;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IBaseView;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Request;

public class DataStringCallback extends StringCallback {

    private  IBaseView iView;

   public DataStringCallback(IBaseView iView){
     this.iView = iView;
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        iView.showLoading();
    }

    @Override
    public void onError(okhttp3.Call call, Exception e, int i) {
        e.printStackTrace();
        iView.dismissLoading();
        iView.onError(e.toString());
    }

    @Override
    public void onResponse(String s, int i) {
       iView.dismissLoading();
    }
}
