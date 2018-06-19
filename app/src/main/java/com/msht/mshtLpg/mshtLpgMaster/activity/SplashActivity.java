package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    private Timer time;
    private TimerTask tk;
    private long timelong = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = new Timer();

        AndPermission.with(this)
                .permission(Permission.Group.LOCATION,Permission.Group.STORAGE,Permission.Group.CAMERA,Permission.Group.PHONE)
                .onGranted(permissions -> {
                    // TODO what to do.
                    waitGo();
                }).onDenied(permissions -> {
            PopUtil.toastInBottom("为了更好使用LPG，请赋予权限");
            waitGo();
        })
                .start();

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
}
