package com.example.mshtyfb.mshtlpg.viewInterface;

import com.example.mshtyfb.mshtlpg.gsonInstance.GsonInstance;
import com.example.mshtyfb.mshtlpg.netStringCallback.UserRequestVerificationCodeBean;

public class UserRequestVerificationCodeView extends BaseActivity  {



    @Override
    public void onSuccess(String s) {
        super.onSuccess(s);
        UserRequestVerificationCodeBean userRequestVerificationCodeBean = GsonInstance.getGsonInstance().getGson().fromJson(s,UserRequestVerificationCodeBean.class);
    }
}
