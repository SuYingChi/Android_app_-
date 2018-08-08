package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.yanzhenjie.permission.Permission;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity implements PermissionUtils.PermissionRequestFinishListener {

    private Timer time;
    private TimerTask tk;
    private long timelong = 2000;
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        time = new Timer();
         PermissionUtils.requestPermissions(SplashActivity.this,SplashActivity.this,Permission.ACCESS_COARSE_LOCATION,Permission.ACCESS_FINE_LOCATION,Permission.CAMERA,Permission.CALL_PHONE,Permission.WRITE_EXTERNAL_STORAGE);

    }

    @Override
    protected void initStateBar() {
        mImmersionBar = ImmersionBar.with(this);
        ImmersionBar.with(this).transparentStatusBar().fullScreen(true).init();

    }

    private void waitGo(){
        tk = new TimerTask() {
            @Override
            public void run() {
                gotoActivity();
                finish();
            }
        };
        time.schedule(tk, timelong);
    }

    private void gotoActivity() {
        Intent go;
        go = new Intent(this, LoginActivity.class);
        if (getIntent().getExtras() != null) {
            go.putExtras(getIntent().getExtras());
        }
        if (SharePreferenceUtil.getInstance().getToken().equals("")) {
            Intent goLogin = new Intent(this, LoginActivity.class);
            startActivity(goLogin);
        }else {
            Intent goMain = new Intent(this, HomeActivity.class);
            startActivity(goMain);
        }
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void onBackFromSettingPage() {
        waitGo();
    }

    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        waitGo();
    }

    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        waitGo();
    }

}
