package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyCaptureEmptyFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyCaptureFragment;
import com.msht.mshtLpg.mshtLpgMaster.util.LogUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.ButterKnife;

public class ScanCodeDeliverSteelBottleActivity extends BaseActivity implements MyCaptureFragment.CaptureFrgamnetNextBtnClickListener, MyCaptureEmptyFragment.CaptureEmptyFrgamnetBtnClickListener,PermissionUtils.PermissionRequestFinishListener {


    private MyCaptureFragment captureFragment;
    private MyCaptureEmptyFragment myCaptureEmptybottleFragment;
    private int scanedfiveNum = 0;
    private int scanedfifteenNum = 0;
    private int scanedfiftyNum = 0;
    private int emptyFiveNum = 0;
    private int emptyFifteenNum = 0;
    private int emptyFiftyNum = 0;
    private BaseFragment currentFragment;
    private OrderDetailBean orderDetailBean;
    private int orderfiveNum = 0;
    private int orderfifteenNum =0;
    private int orderfiftyNum=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_code_deliver_steel_bottle_activity);
        ButterKnife.bind(this);
        PermissionUtils.requestPermissions(this, this, Permission.CAMERA);
        orderDetailBean = (OrderDetailBean) getIntent().getSerializableExtra(Constants.ORDER_DETAIL_BEAN);
        orderfiveNum= orderDetailBean.getData().getFiveBottleCount();
        orderfifteenNum =orderDetailBean.getData().getFifteenBottleCount();
        orderfiftyNum=orderDetailBean.getData().getFiftyBottleCount();
        captureFragment = new MyCaptureFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ORDER_FIVE_NUM, orderfiveNum);
        bundle.putInt(Constants.ORDER_FIFTEEN_NUM, orderfifteenNum);
        bundle.putInt(Constants.ORDER_FIFTY_NUM, orderfiftyNum);
        captureFragment.setArguments(bundle);
        showFragment(captureFragment);
        captureFragment.setCameraInitCallBack(new MyCaptureFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    LogUtils.d("TAG", "callback:    " + e);
                }
            }
        });
        captureFragment.setCaptureFrgamnetNextBtnClickListener(this);
    }


    @Override
    public void onCaptureFragmentClickNextBtn() {
        scanedfiveNum = captureFragment.getFiveNum();
        scanedfifteenNum = captureFragment.getFifteenNum();
        scanedfiftyNum = captureFragment.getFiftyNum();
        if(scanedfiveNum!=orderfiveNum||scanedfifteenNum!=orderfifteenNum||scanedfiftyNum!=orderfiftyNum){
            PopUtil.toastInBottom("未扫描订单要求的钢瓶数量和规格，无法跳转下一步");
        }else {
            myCaptureEmptybottleFragment = new MyCaptureEmptyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.SCANED_FIVE_NUM, scanedfiveNum);
            bundle.putInt(Constants.SCANED_FIFTEEN_NUM, scanedfifteenNum);
            bundle.putInt(Constants.SCANED_FIFTY_NUM, scanedfiftyNum);
            myCaptureEmptybottleFragment.setArguments(bundle);
            showFragment(myCaptureEmptybottleFragment);
            myCaptureEmptybottleFragment.setEmptyCameraInitCallBack(new MyCaptureEmptyFragment.EmptyCameraInitCallBack() {
                @Override
                public void callBack(Exception e) {
                    if (e == null) {
                    } else {
                        LogUtils.d("TAG", "callback:    " + e);
                    }
                }
            });
        }

    }

    @Override
    public void onEmptyBottleClickNextBtn() {
        emptyFiveNum = myCaptureEmptybottleFragment.getFiveNum();
        emptyFifteenNum = myCaptureEmptybottleFragment.getFifteenNum();
        emptyFiftyNum = myCaptureEmptybottleFragment.getFiftyNum();
        Intent intent = new Intent(this, OrdersDetailPostActivity.class);
        intent.putExtra(Constants.ORDER_DETAIL_BEAN,orderDetailBean);
        intent.putExtra(Constants.EMPTY_FIVE_NUM, emptyFiveNum);
        intent.putExtra(Constants.EMPTY_FIFTEEN_NUM, emptyFifteenNum);
        intent.putExtra(Constants.EMPTY_FIFTY_NUM, emptyFiftyNum);
        startActivity(intent);
    }

    @Override
    public void onEmptyBottleClickReturnBtn() {
     showFragment(captureFragment);
    }


    private void showFragment(BaseFragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.fl_my_container, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
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

    }
}
