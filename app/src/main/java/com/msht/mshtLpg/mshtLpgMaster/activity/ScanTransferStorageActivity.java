package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyScanDeliverUserBottleFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyScanTransferBottleFragment;
import com.msht.mshtLpg.mshtLpgMaster.util.LogUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author mshtyfb
 */
public class ScanTransferStorageActivity extends BaseActivity  implements  PermissionUtils.PermissionRequestFinishListener{
    private static final String TAG = ScanTransferStorageActivity.class.getSimpleName();
    @BindView(R.id.fl_my_container)
    FrameLayout framContainer;

    private Unbinder unbinder;
    private FragmentTransaction transaction;
    private MyScanTransferBottleFragment scanTransferBottleFragment;
    private String orderId;
    private String fiveCount;
    private String fifteenCount;
    private String ffityCount;
    private String transferType;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_code_deliver_steel_bottle_activity);
        unbinder = ButterKnife.bind(this);
        orderId = getIntent().getStringExtra(Constants.ORDER_ID);
        fiveCount = getIntent().getStringExtra(Constants.ORDER_FIVE_NUM);
        fifteenCount = getIntent().getStringExtra(Constants.ORDER_FIFTEEN_NUM);
        ffityCount = getIntent().getStringExtra(Constants.ORDER_FIFTY_NUM);
        transferType = getIntent().getStringExtra("TransferType");
        PermissionUtils.requestPermissions(this, this, Permission.CAMERA);

    }


    private void showFragment(BaseFragment fragment){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_my_container,fragment).commit();
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
        scanTransferBottleFragment = new MyScanTransferBottleFragment();
        scanTransferBottleFragment.setCameraInitCallBack(new MyScanDeliverUserBottleFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    LogUtils.d(TAG, "callback:    " + e);
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ORDER_ID,orderId);
        bundle.putString(Constants.ORDER_FIVE_NUM,fiveCount);
        bundle.putString(Constants.ORDER_FIFTEEN_NUM, fifteenCount);
        bundle.putString(Constants.ORDER_FIFTY_NUM, ffityCount);
        bundle.putString("TransferType",transferType);
        scanTransferBottleFragment.setArguments(bundle);
        showFragment(scanTransferBottleFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
