package com.msht.mshtlpgmaster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtlpgmaster.Bean.LoginEventBean;
import com.msht.mshtlpgmaster.Bean.UserLoginBean;
import com.msht.mshtlpgmaster.Present.ILoginPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.services.KillSelfService;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;
import com.msht.mshtlpgmaster.viewInterface.ILoginView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity implements ILoginView {


    @BindView(R.id.et_login_mobile_number)
    EditText mobileNumber;
    @BindView(R.id.login_login_password)
    EditText loginPassword;
    @BindView(R.id.btn_login)
    Button btnOk;

    private ILoginPresenter iLoginPresenter;
    private Unbinder unbinder;
    private long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                PopUtil.toastInBottom(R.string.exit_App);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        iLoginPresenter = new ILoginPresenter(this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
                if (mobileNumber.getText().length() != 11) {
                    PopUtil.toastInBottom(getString(R.string.please_input_right_mobile_number));
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
        SharePreferenceUtil.getInstance().setToken(s.getData().getLoginToken());
        SharePreferenceUtil.setLoginSpStringValue(Constants.EMPLOYERID, s.getData().getEmployeeId() + "");
        SharePreferenceUtil.setLoginSpStringValue("employeeName", s.getData().getEmployeeName());
        SharePreferenceUtil.setLoginSpStringValue("siteName", s.getData().getSiteName());
        SharePreferenceUtil.setLoginSpStringValue("siteId", s.getData().getSiteId() + "");
        startActivity(new Intent(this, HomeActivity.class));
        EventBus.getDefault().postSticky(new LoginEventBean(s));
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ImmersionBar.with(this).destroy();
        EventBus.getDefault().unregister(this);
    }
   public void reinputUserinfo(){
       mobileNumber.setText("");
       loginPassword.setText("");
       mobileNumber.setHint("请输入手机号");
       loginPassword.setHint("请输入密码");
    }
}
