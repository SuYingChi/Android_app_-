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
import com.msht.mshtLpg.mshtLpgMaster.Present.IOrderDetailPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyScanBackBottleFragment;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyScanDeliverUserBottleFragment;
import com.msht.mshtLpg.mshtLpgMaster.util.BottleCaculteUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.LogUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ISimpleOrderDetailView;
import com.yanzhenjie.permission.Permission;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ScanCodeDeliverSteelBottleActivity extends BaseActivity implements MyScanDeliverUserBottleFragment.CaptureActivityListener, PermissionUtils.PermissionRequestFinishListener, ISimpleOrderDetailView {


    private static final String TAG = ScanCodeDeliverSteelBottleActivity.class.getSimpleName();
    private MyScanDeliverUserBottleFragment deliverUserBottleFragment;
    private MyScanDeliverUserBottleFragment captureEmptybottleFragment;
    private int orderfiveNum = 0;
    private int orderfifteenNum = 0;
    private int orderfiftyNum = 0;
    private String orderId;
    private List<VerifyBottleBean> heavyBottleList;
    private Unbinder unbinder;
    private int orderType;
    private MyScanBackBottleFragment backBottleFragment;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_code_deliver_steel_bottle_activity);
        transaction = getSupportFragmentManager().beginTransaction();
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra(Constants.ORDER_ID);
        PermissionUtils.requestPermissions(this, this, Permission.CAMERA);

    }


    @Override
    public void onClickNextBtnAndSendVerifyBottleList(int fragmentType, List<VerifyBottleBean> list) {
        if(fragmentType == 1) {
            this.heavyBottleList = list;
            int scanedfiveNum = BottleCaculteUtil.getBottleNum(list, 5);
            int scanedfifteenNum = BottleCaculteUtil.getBottleNum(list, 15);
            ;
            int scanedfiftyNum = BottleCaculteUtil.getBottleNum(list, 50);
            ;
            if(scanedfiveNum !=orderfiveNum|| scanedfifteenNum !=orderfifteenNum|| scanedfiftyNum !=orderfiftyNum){
                PopUtil.toastInBottom("未达到订单要求钢瓶数和规格"+"请按订单要求扫描验瓶"+"5kg瓶"+orderfiveNum+"15kg瓶"+orderfifteenNum+"50kg瓶"+orderfiftyNum);
            }else {
                if(captureEmptybottleFragment == null) {
                    Bundle bundle = new Bundle();
                    captureEmptybottleFragment = new MyScanDeliverUserBottleFragment();
                    bundle.putInt(Constants.ORDER_FIVE_NUM, orderfiveNum);
                    bundle.putInt(Constants.ORDER_FIFTEEN_NUM, orderfifteenNum);
                    bundle.putInt(Constants.ORDER_FIFTY_NUM, orderfiftyNum);
                    bundle.putInt(Constants.SCANFRAGMENT_TYPE, 2);
                    captureEmptybottleFragment.setArguments(bundle);
                    captureEmptybottleFragment.setCameraInitCallBack(new MyScanDeliverUserBottleFragment.CameraInitCallBack() {
                        @Override
                        public void callBack(Exception e) {
                            if (e == null) {

                            } else {
                                LogUtils.d(TAG, "callback:    " + e);
                            }
                        }
                    });
                }
                showFragment(captureEmptybottleFragment);
            }


        }else {
            Intent intent = new Intent(this,SendBottleOrdersDetailPostActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString(Constants.ORDER_ID,orderId);
            bundle.putSerializable(Constants.HEAVY_BOTTLE_LIST,(Serializable)heavyBottleList);
            bundle.putSerializable(Constants.EMPTY_BOTTLE_LIST,(Serializable) list);
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
            showFragment(deliverUserBottleFragment);
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
        transaction.setCustomAnimations(R.anim.forward_enter,R.anim.forward_exit_fragment);
        //加入动画会导致相机无法启动扫描
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
        new IOrderDetailPresenter(this).getSimpleOrderDetail();
    }


    @Override
    public void onGetOrdersDetailSuccess(OrderDetailBean bean) {
        orderfiveNum = bean.getData().getFiveBottleCount();
        orderfifteenNum = bean.getData().getFifteenBottleCount();
        orderfiftyNum = bean.getData().getFiftyBottleCount();
        orderType = bean.getData().getOrderType();
         //送气单的扫码界面
        if(orderType == 1) {
            deliverUserBottleFragment = new MyScanDeliverUserBottleFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.ORDER_FIVE_NUM, orderfiveNum);
            bundle.putInt(Constants.ORDER_FIFTEEN_NUM, orderfifteenNum);
            bundle.putInt(Constants.ORDER_FIFTY_NUM, orderfiftyNum);
            bundle.putInt(Constants.SCANFRAGMENT_TYPE, 1);
            deliverUserBottleFragment.setArguments(bundle);
            deliverUserBottleFragment.setCameraInitCallBack(new MyScanDeliverUserBottleFragment.CameraInitCallBack() {
                @Override
                public void callBack(Exception e) {
                    if (e == null) {

                    } else {
                        LogUtils.d(TAG, "callback:    " + e);
                    }
                }
            });
            showFragment(deliverUserBottleFragment);
        }//退瓶单的扫码界面
        else if(orderType == 0){
            backBottleFragment = new MyScanBackBottleFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.ORDER_FIVE_NUM, bean.getData().getReFiveBottleCount());
            bundle.putInt(Constants.ORDER_FIFTEEN_NUM, bean.getData().getReFifteenBottleCount());
            bundle.putInt(Constants.ORDER_FIFTY_NUM, bean.getData().getReFiftyBottleCount());
            bundle.putString(Constants.ORDER_ID,orderId);
            backBottleFragment.setArguments(bundle);
            backBottleFragment.setCameraInitCallBack(new MyScanBackBottleFragment.CameraInitCallBack() {
                @Override
                public void callBack(Exception e) {
                    if (e == null) {

                    } else {
                        LogUtils.d(TAG, "callback:    " + e);
                    }
                }
            });
            showFragment(backBottleFragment);
        }
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
