package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtLpg.mshtLpgMaster.Bean.LogoutEvent;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.LoginActivity;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.util.AppUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IBaseView;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment implements IBaseView{

    private ImmersionBar mImmersionBar;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initStateBar();

    }

    @Override
    public void showLoading() {
        PopUtil.showCenterLodaingDialog(this.getContext());
    }

    @Override
    public void dismissLoading() {
      PopUtil.hideCenterLoadingDialog(this.getContext());
    }
    protected void initStateBar() {
       /* mImmersionBar = ImmersionBar.with(this);
        //ImmersionBar.with(this).statusBarColor(R.color.msb_color).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).init();
        mImmersionBar.statusBarColor(R.color.msb_color).transparentNavigationBar().init();*/

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
    public void onNetError(){

    };

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
}
