package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.zxing.Result;
import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtLpg.mshtLpgMaster.Bean.LogoutEvent;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.LoginActivity;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.LoadingDialog;
import com.msht.mshtLpg.mshtLpgMaster.util.AppUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IBaseView;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment implements IBaseView {

    private ImmersionBar mImmersionBar;
    protected LoadingDialog centerLoadingDialog;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initStateBar();

    }

    @Override
    public void showLoading() {
        showCenterLodaingDialog();

    }

    @Override
    public void dismissLoading() {
        hideCenterLoadingDialog();
    }

    protected void initStateBar() {
       /* mImmersionBar = ImmersionBar.with(this);
        //ImmersionBar.with(this).statusBarColor(R.color.msb_color).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).init();
        mImmersionBar.statusBarColor(R.color.msb_color).transparentNavigationBar().init();*/

    }

    protected void showCenterLodaingDialog() {
        if (!this.getActivity().isFinishing() && centerLoadingDialog == null) {
            centerLoadingDialog = new LoadingDialog(this.getContext());
        } else if (!this.getActivity().isFinishing() && !centerLoadingDialog.isShowing()) {
            centerLoadingDialog.show();
        }
    }


    protected void hideCenterLoadingDialog() {
        if (centerLoadingDialog != null && centerLoadingDialog.isShowing() && this.getContext() != null) {
            centerLoadingDialog.dismiss();
        }
    }

    @Override
    public void onError(String s) {
        if (!AppUtil.isNetworkAvailable()) {
            PopUtil.toastInBottom(R.string.net_no_available);
            onNetError();
        } else {
            PopUtil.toastInBottom(s);
            switch (s) {
                case "未登录":
                case "登出返回结果为空":
                    AppUtil.logout();
                    Intent goLogin = new Intent(this.getActivity(), LoginActivity.class);
                    startActivity(goLogin);
                    break;
                default:

                    break;
            }
        }
    }

    @Override
    public String getToken() {
        return SharePreferenceUtil.getInstance().getToken();
    }

    @Override
    public String getEmployerId() {
        return SharePreferenceUtil.getLoginSpStringValue(Constants.EMPLOYERID);
    }

    @Override
    public void onLogout() {
        AppUtil.logout();
        EventBus.getDefault().post(new LogoutEvent());
    }

    @Override
    public void onNetError() {

    }

    ;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    public String getOrderType() {
        return SharePreferenceUtil.getInstance().getOrderType();
    }

    //扫描二维码的frgament 需要重写这三个方法
    public void handleDecode(Result obj, Bitmap barcode) {
    }



    public void drawViewfinder() {
    }



    public Handler getHandler() {
        return null;
    }


}
