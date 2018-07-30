package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyDeliverUserBottleFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyScanInnerFetchFragment;
import com.msht.mshtLpg.mshtLpgMaster.util.LogUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InnerActivity extends BaseActivity implements MyScanInnerFetchFragment.InnnerFetchActivityListener, PermissionUtils.PermissionRequestFinishListener{
    @BindView(R.id.fl_my_container)
    FrameLayout framContainer;

    private Unbinder unbinder;
    private String employerId;
    private FragmentTransaction transaction;
    private MyScanInnerFetchFragment scanEmpolyerFragment;
    private MyScanInnerFetchFragment myScanBottleFragment;


    private String innerUrl = "";
    private int innerType=0;
    private String verifyType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_code_deliver_steel_bottle_activity);
        Intent intent = getIntent();
         innerType =intent.getIntExtra("innerType",0);
        if(innerType == 1){
            innerUrl = Constants.INNER_FETCH;
            verifyType = "7";
        }else if(innerType  == 2){
            innerUrl = Constants.INNER_RETURN;
            verifyType = "6";
        }
        unbinder = ButterKnife.bind(this);
        PermissionUtils.requestPermissions(this, this, Permission.CAMERA);
    }


    private void showFragment(BaseFragment fragment){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_my_container,fragment).commit();
    }
    @Override
    public void onClickNextBtnAndSendEmployerId( String employerId) {
        this.employerId = employerId;
        Bundle bundle = new Bundle();
        myScanBottleFragment = new MyScanInnerFetchFragment();
        bundle.putString(Constants.EMPLOYERID, employerId);
        bundle.putInt(Constants.SCANFRAGMENT_TYPE,2);
        myScanBottleFragment.setArguments(bundle);
        myScanBottleFragment.setCameraInitCallBack(new MyDeliverUserBottleFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    LogUtils.d("TAG", "callback:    " + e);
                }
            }
        });
        showFragment(myScanBottleFragment);

    }

    @Override
    public void onCaptureFragmenBackBtnClick(int fragmentType) {
        if(fragmentType == 1){
            finish();
        }else if(fragmentType == 2){
            showFragment(scanEmpolyerFragment);
        }
    }

    @Override
    public String getEmpolyerId() {
        return employerId;
    }

    @Override
    public String getUrl() {
        return innerUrl;
    }

    @Override
    public int getInnerType() {
        return innerType;
    }

    @Override
    public String getVerifyType() {
        return verifyType;
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
        scanEmpolyerFragment = new MyScanInnerFetchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.SCANFRAGMENT_TYPE,1);
        scanEmpolyerFragment.setArguments(bundle);
        scanEmpolyerFragment.setCameraInitCallBack(new MyDeliverUserBottleFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    LogUtils.d("TAG", "callback:    " + e);
                }
            }
        });
        showFragment(scanEmpolyerFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
