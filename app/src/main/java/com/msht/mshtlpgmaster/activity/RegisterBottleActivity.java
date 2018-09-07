package com.msht.mshtlpgmaster.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.fragment.MyScanDeliverUserBottleFragment;
import com.msht.mshtlpgmaster.fragment.MyScanRegisterBottleFragment;
import com.msht.mshtlpgmaster.util.AppUtil;
import com.msht.mshtlpgmaster.util.LogUtils;
import com.msht.mshtlpgmaster.util.PermissionUtils;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegisterBottleActivity extends BaseActivity implements PermissionUtils.PermissionRequestFinishListener {
    private static final String TAG = RegisterBottleActivity.class.getSimpleName();
    @BindView(R.id.fl_my_container)
    FrameLayout framContainer;

    private Unbinder unbinder;
    private FragmentTransaction transaction;
    private MyScanRegisterBottleFragment registerBottleFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_code_deliver_steel_bottle_activity);
        transaction = getSupportFragmentManager().beginTransaction();
        unbinder = ButterKnife.bind(this);
        PermissionUtils.requestPermissions(this, this, Permission.CAMERA);
    }


    @Override
    public void onBackFromSettingPage() {
        if (!PermissionUtils.cameraIsCanUse()) {
            finish();
        }
    }

    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        finish();
    }

    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        registerBottleFragment = new MyScanRegisterBottleFragment();
        registerBottleFragment.setCameraInitCallBack(new MyScanDeliverUserBottleFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    LogUtils.d(TAG, "callback:    " + e);
                }
            }
        });
        AppUtil.showFragment(registerBottleFragment, getSupportFragmentManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
