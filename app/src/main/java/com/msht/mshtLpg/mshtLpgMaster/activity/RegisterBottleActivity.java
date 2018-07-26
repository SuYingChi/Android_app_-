package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyDeliverUserBottleFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyRegisterBottleFragment;
import com.msht.mshtLpg.mshtLpgMaster.util.LogUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegisterBottleActivity extends BaseActivity implements  PermissionUtils.PermissionRequestFinishListener{
    @BindView(R.id.fl_my_container)
    FrameLayout framContainer;

    private Unbinder unbinder;
    private FragmentTransaction transaction;
    private MyRegisterBottleFragment registerBottleFragment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_code_deliver_steel_bottle_activity);
        unbinder = ButterKnife.bind(this);
        PermissionUtils.requestPermissions(this, this, Permission.CAMERA);
    }


    private void showFragment(BaseFragment fragment){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_my_container,fragment).commit();
    }



    @Override
    public void onBackFromSettingPage() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            finish();
        }
    }

    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        finish();
    }

    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        registerBottleFragment = new MyRegisterBottleFragment();
        registerBottleFragment.setCameraInitCallBack(new MyDeliverUserBottleFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    LogUtils.d("TAG", "callback:    " + e);
                }
            }
        });
        showFragment(registerBottleFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
