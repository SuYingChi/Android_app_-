package com.msht.mshtyLpg.mshtLpgUser.userRegisterActivity;


import com.msht.mshtyLpg.mshtLpgUser.activity.BaseActivity;
import com.msht.mshtyLpg.mshtLpgUser.gsonInstance.GsonInstance;
import com.msht.mshtyLpg.mshtLpgUser.netStringCallback.UserRequestVerificationCodeBean;

public class UserRequestVerificationCodeActivity extends BaseActivity {



    @Override
    public void onSuccess(String s) {
        super.onSuccess(s);
        UserRequestVerificationCodeBean userRequestVerificationCodeBean = GsonInstance.getGsonInstance().getGson().fromJson(s,UserRequestVerificationCodeBean.class);
    }
}
