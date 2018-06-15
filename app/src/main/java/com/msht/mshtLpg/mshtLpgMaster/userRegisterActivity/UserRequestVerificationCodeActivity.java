package com.msht.mshtLpg.mshtLpgMaster.userRegisterActivity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.msht.mshtLpg.mshtLpgMaster.activity.BaseActivity;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonInstance;
import com.msht.mshtLpg.mshtLpgMaster.netRequestPresent.UserRequestVerificationCodePresenter;
import com.msht.mshtLpg.mshtLpgMaster.netStringCallback.UserRequestVerificationCodeBean;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IUserRequestVerificationCodeView;

public class UserRequestVerificationCodeActivity extends BaseActivity  implements IUserRequestVerificationCodeView{

    UserRequestVerificationCodePresenter userRequestVerificationCodePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userRequestVerificationCodePresenter= new UserRequestVerificationCodePresenter(this);
    }

    @Override
    public void onNetRequestSuccess(String s) {
        UserRequestVerificationCodeBean userRequestVerificationCodeBean = GsonInstance.getGsonInstance().getGson().fromJson(s,UserRequestVerificationCodeBean.class);
    }
}
