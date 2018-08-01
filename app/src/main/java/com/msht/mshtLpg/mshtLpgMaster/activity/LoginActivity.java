package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.msht.mshtLpg.mshtLpgMaster.Bean.LoginEventBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.UserLoginBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.ILoginPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ILoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity implements ILoginView{



    @BindView(R.id.et_login_mobile_number)
    EditText mobileNumber;
    @BindView(R.id.login_login_password)
    EditText loginPassword;
   @BindView(R.id.btn_login)
    Button btnOk;

    private ILoginPresenter iLoginPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        iLoginPresenter= new ILoginPresenter(this);
    }

    @OnClick( R.id.btn_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
                if (mobileNumber.getText().length() != 11) {
                    PopUtil.toastInBottom( getString(R.string.please_input_right_mobile_number));
                    return;
                }
                if (loginPassword.getText().length() != 6) {
                    PopUtil.toastInBottom(getString(R.string.please_input_password));
                    return;
                }
                iLoginPresenter.login(mobileNumber.getText().toString(), loginPassword.getText().toString());
                break;
              default:
                break;

        }
    }

    @Override
    public void onLoginSuccess(UserLoginBean s) {
        SharePreferenceUtil.getInstance().setToken( s.getData().getLoginToken());
        SharePreferenceUtil.setLoginSpStringValue(Constants.EMPLOYERID,s.getData().getEmployeeId()+"");
        SharePreferenceUtil.setLoginSpStringValue("employeeName",s.getData().getEmployeeName());
        SharePreferenceUtil.setLoginSpStringValue("siteName",s.getData().getSiteName());
        SharePreferenceUtil.setLoginSpStringValue("siteId",s.getData().getSiteId()+"");
        startActivity( new Intent(this, HomeActivity.class));
        EventBus.getDefault().postSticky(new LoginEventBean(s));

    }

   /* @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEventBean event) {
        finish();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
