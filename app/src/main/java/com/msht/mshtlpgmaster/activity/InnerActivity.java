package com.msht.mshtlpgmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;

import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.fragment.MyScanDeliverUserBottleFragment;
import com.msht.mshtlpgmaster.fragment.MyScanInnerFragment;
import com.msht.mshtlpgmaster.util.AppUtil;
import com.msht.mshtlpgmaster.util.LogUtils;
import com.msht.mshtlpgmaster.util.PermissionUtils;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InnerActivity extends BaseActivity implements MyScanInnerFragment.InnnerFetchActivityListener, PermissionUtils.PermissionRequestFinishListener {
    private static final String TAG = InnerActivity.class.getSimpleName();
    @BindView(R.id.fl_my_container)
    FrameLayout framContainer;

    private Unbinder unbinder;
    private String employerId;
    private FragmentTransaction transaction;
    private MyScanInnerFragment scanEmpolyerFragment;
    private MyScanInnerFragment myScanBottleFragment;
    private String innerUrl = "";
    private int innerType = 0;
    private String verifyType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_code_deliver_steel_bottle_activity);
        Intent intent = getIntent();
        innerType = intent.getIntExtra("innerType", 0);
        if (innerType == 1) {
            innerUrl = Constants.INNER_FETCH;
            verifyType = "7";
        } else if (innerType == 2) {
            innerUrl = Constants.INNER_RETURN;
            verifyType = "6";
        }
        unbinder = ButterKnife.bind(this);
        PermissionUtils.requestPermissions(this, this, Permission.CAMERA);
    }

    @Override
    public void onClickNextBtnAndSendEmployerId(String employerId) {
        this.employerId = employerId;
        Bundle bundle = new Bundle();
        myScanBottleFragment = new MyScanInnerFragment();
        bundle.putInt(Constants.SCANFRAGMENT_TYPE, 2);
        bundle.putInt("innerType", innerType);
        myScanBottleFragment.setArguments(bundle);
        myScanBottleFragment.setCameraInitCallBack(new MyScanDeliverUserBottleFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    Log.d(TAG, "callback:    " + e);
                }
            }
        });
        AppUtil.replaceFragment(myScanBottleFragment, scanEmpolyerFragment, getSupportFragmentManager());

    }

    @Override
    public void onCaptureFragmenBackBtnClick(int fragmentType) {
        if (innerType == 1 && fragmentType == 1) {
            finish();
        } else if (innerType == 1 && fragmentType == 2) {
            scanEmpolyerFragment = new MyScanInnerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.SCANFRAGMENT_TYPE, 1);
            bundle.putInt("innerType", innerType);
            scanEmpolyerFragment.setArguments(bundle);
            scanEmpolyerFragment.setCameraInitCallBack(new MyScanDeliverUserBottleFragment.CameraInitCallBack() {
                @Override
                public void callBack(Exception e) {
                    if (e == null) {

                    } else {
                        LogUtils.d(TAG, "callback:    " + e);
                    }
                }
            });
            AppUtil.replaceFragment(scanEmpolyerFragment, myScanBottleFragment, getSupportFragmentManager());
        } else if (innerType == 2) {
            finish();
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
        if (innerType == 1) {
            scanEmpolyerFragment = new MyScanInnerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.SCANFRAGMENT_TYPE, 1);
            bundle.putInt("innerType", innerType);
            scanEmpolyerFragment.setArguments(bundle);
            scanEmpolyerFragment.setCameraInitCallBack(new MyScanDeliverUserBottleFragment.CameraInitCallBack() {
                @Override
                public void callBack(Exception e) {
                    if (e == null) {

                    } else {
                        LogUtils.d(TAG, "callback:    " + e);
                    }
                }
            });
            AppUtil.showFragment(scanEmpolyerFragment, getSupportFragmentManager());
        } else if (innerType == 2) {
            Bundle bundle = new Bundle();
            myScanBottleFragment = new MyScanInnerFragment();
            bundle.putInt(Constants.SCANFRAGMENT_TYPE, 2);
            bundle.putInt("innerType", innerType);
            myScanBottleFragment.setArguments(bundle);
            myScanBottleFragment.setCameraInitCallBack(new MyScanDeliverUserBottleFragment.CameraInitCallBack() {
                @Override
                public void callBack(Exception e) {
                    if (e == null) {

                    } else {
                        LogUtils.d(TAG, "callback:    " + e);
                    }
                }
            });
            AppUtil.showFragment(myScanBottleFragment, getSupportFragmentManager());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
