package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.yanzhenjie.permission.Permission;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity implements PermissionUtils.PermissionRequestFinishListener {

    private Timer time;
    private TimerTask tk;
    private long timelong = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = new Timer();

         PermissionUtils.requestPermissions(SplashActivity.this,SplashActivity.this,Permission.ACCESS_COARSE_LOCATION,Permission.ACCESS_FINE_LOCATION,Permission.CAMERA,Permission.CALL_PHONE,Permission.WRITE_EXTERNAL_STORAGE);

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
    public void onBackFromSettingPage() {
        waitGo();
    }

    @Override
    public void onPermissionRequestDenied() {
       waitGo();
    }

    @Override
    public void onPermissionRequestSuccess() {
        waitGo();
    }
}
