package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.msht.mshtLpg.mshtLpgMaster.Bean.LogoutEvent;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.util.AppUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.SharePreferenceUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IBaseView;

import org.greenrobot.eventbus.EventBus;

public class BaseFragment extends Fragment implements IBaseView{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void showLoading() {
        PopUtil.showCenterLodaingDialog(this.getContext());
    }

    @Override
    public void dismissLoading() {
      PopUtil.hideCenterLoadingDialog(this.getContext());
    }

    @Override
    public void onError(String s) {
        if (AppUtil.isNetworkAvailable(this.getContext())) {
            PopUtil.toastInBottom(getResources().getString(R.string.net_exception));
        } else {
            PopUtil.toastInBottom(getResources().getString(R.string.net_no_available));
        }
       PopUtil.toastInBottom(getResources().getString(R.string.unknow_error));
    }

    @Override
    public String getToken() {
        return SharePreferenceUtil.getInstance().getToken();
    }

    @Override
    public void onLogout() {
        AppUtil.logout();
        EventBus.getDefault().post(new LogoutEvent());
    }
}
