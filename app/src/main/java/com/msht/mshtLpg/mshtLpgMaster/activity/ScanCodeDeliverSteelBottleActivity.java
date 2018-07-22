package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrderDetailBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPostPresenter;
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyCaptureFragment;
import com.msht.mshtLpg.mshtLpgMaster.util.BottleCaculteUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.LogUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailPostView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IOrderDetailView;
import com.yanzhenjie.permission.Permission;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ScanCodeDeliverSteelBottleActivity extends BaseActivity implements MyCaptureFragment.CaptureActivityListener, PermissionUtils.PermissionRequestFinishListener, IOrderDetailView{


    private MyCaptureFragment captureFragment;
    private MyCaptureFragment captureEmptybottleFragment;
    private int scanedfiveNum = 0;
    private int scanedfifteenNum = 0;
    private int scanedfiftyNum = 0;
    private int orderfiveNum = 0;
    private int orderfifteenNum = 0;
    private int orderfiftyNum = 0;
    private String orderId;
    private FragmentTransaction transaction;
    private List<VerifyBottleBean> heavyBottleList;
    private List<VerifyBottleBean> emptyBottleList;
    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_code_deliver_steel_bottle_activity);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        PermissionUtils.requestPermissions(this, this, Permission.CAMERA);

    }


    @Override
    public void onClickNextBtnAndSendVerifyBottleList(int fragmentType, List<VerifyBottleBean> list) {
        if(fragmentType == 1) {
            this.heavyBottleList = list;
            scanedfiveNum = BottleCaculteUtil.getBottleNum(list,5);
            scanedfifteenNum = BottleCaculteUtil.getBottleNum(list,15);;
            scanedfiftyNum = BottleCaculteUtil.getBottleNum(list,50);;
            if(scanedfiveNum!=orderfiveNum||scanedfifteenNum!=orderfifteenNum||scanedfiftyNum!=orderfiftyNum){
                PopUtil.toastInBottom("未达到订单要求钢瓶数和规格"+"请按订单要求扫描验瓶"+"5kg瓶"+orderfiveNum+"15kg瓶"+orderfifteenNum+"50kg瓶"+orderfiftyNum);
            }else {
                Bundle bundle = new Bundle();
                captureEmptybottleFragment = new MyCaptureFragment();
                bundle.putInt(Constants.ORDER_FIVE_NUM, orderfiveNum);
                bundle.putInt(Constants.ORDER_FIFTEEN_NUM, orderfifteenNum );
                bundle.putInt(Constants.ORDER_FIFTY_NUM, orderfiftyNum);
                bundle.putInt(Constants.SCANFRAGMENT_TYPE,2);
                captureEmptybottleFragment.setArguments(bundle);
                showFragment(captureEmptybottleFragment);
                captureEmptybottleFragment.setCameraInitCallBack(new MyCaptureFragment.CameraInitCallBack() {
                    @Override
                    public void callBack(Exception e) {
                        if (e == null) {

                        } else {
                            LogUtils.d("TAG", "callback:    " + e);
                        }
                    }
                });
            }


        }else {
            emptyBottleList = list;
            Intent intent = new Intent(this,OrdersDetailPostActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString(Constants.ORDER_ID,orderId);
            bundle.putSerializable(Constants.HEAVY_BOTTLE_LIST,(Serializable)heavyBottleList);
            bundle.putSerializable(Constants.EMPTY_BOTTLE_LIST,(Serializable)emptyBottleList);
            bundle.putInt("starttype",1);
            intent.putExtras(bundle);
            startActivity(intent);

        }

    }


    @Override
    public void onCaptureFragmenBackBtnClick(int fragmentType) {
        if(fragmentType == 1){
            finish();
        }else if(fragmentType == 2){
            showFragment(captureFragment);
        }
    }


/*    private void showFragment(BaseFragment fragment) {
        if (currentFragment != fragment) {
            transaction = getSupportFragmentManager().beginTransaction();
            if (currentFragment != null && currentFragment.isVisible()) {
                transaction.hide(currentFragment);
            }
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.fl_my_container, fragment).show(fragment);
            } else {
                transaction.show(fragment);
            }
            transaction.commit();
        }
    }*/
    private void showFragment(BaseFragment fragment){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_my_container,fragment).commit();
    }
  /*  private void removeEmptyFragment() {
        if (captureEmptybottleFragment !=null&& captureEmptybottleFragment.isAdded()) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(captureEmptybottleFragment).commit();
            captureEmptybottleFragment = null;
        }
    }*/
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
        new IOrderDetailPresenter(this).getOrderDetail();
    }


    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean bean) {
        orderfiveNum = bean.getData().getFiveBottleCount();
        orderfifteenNum = bean.getData().getFifteenBottleCount();
        orderfiftyNum = bean.getData().getFiftyBottleCount();

        captureFragment = new MyCaptureFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ORDER_FIVE_NUM, orderfiveNum);
        bundle.putInt(Constants.ORDER_FIFTEEN_NUM, orderfifteenNum);
        bundle.putInt(Constants.ORDER_FIFTY_NUM, orderfiftyNum);
        bundle.putInt(Constants.SCANFRAGMENT_TYPE,1);
        captureFragment.setArguments(bundle);
        captureFragment.setCameraInitCallBack(new MyCaptureFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    LogUtils.d("TAG", "callback:    " + e);
                }
            }
        });

        showFragment(captureFragment);
    }

    @Override
    public String getOrderId() {
        return orderId;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
