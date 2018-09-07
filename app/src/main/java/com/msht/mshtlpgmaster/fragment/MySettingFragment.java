package com.msht.mshtlpgmaster.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.msht.mshtlpgmaster.Bean.LoginEventBean;
import com.msht.mshtlpgmaster.Bean.LogoutEvent;
import com.msht.mshtlpgmaster.Present.ILogoutPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.activity.CurrentVersionInfoActivity;
import com.msht.mshtlpgmaster.activity.LoginActivity;
import com.msht.mshtlpgmaster.activity.MyBottleActivity;
import com.msht.mshtlpgmaster.activity.InnerActivity;
import com.msht.mshtlpgmaster.activity.MyIncomeActivity;
import com.msht.mshtlpgmaster.activity.RegisterEmployerActivity;
import com.msht.mshtlpgmaster.activity.RegisterBottleActivity;
import com.msht.mshtlpgmaster.activity.TransferStorageListActivity;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MySettingFragment extends BaseLazyFragment {
    @BindView(R.id.my_setting_username)
    TextView tvName;
    @BindView(R.id.my_setting_user_location)
    TextView tvSite;
    @BindView(R.id.iv_my_setting_get_steel_bottle_out_warehouse)
    ImageView tvInnerDistribute;
    @BindView(R.id.iv_my_setting_get_steel_bottle_in_warehouse)
    ImageView ivInnerReturn;
    @BindView(R.id.iv_my_setting_register_steel_bottle)
    ImageView ivRegisterBottle;
    @BindView(R.id.ll_my_setting_unregister_login)
    LinearLayout llUnregister;
    @BindView(R.id.my_setting_out_warehouse)
    RelativeLayout out_transfer;
    @BindView(R.id.my_setting_in_storage)
    RelativeLayout in_transfer;
    @BindView(R.id.my_setting_my_steel_bottle)
    RelativeLayout myBottle;
    @BindView(R.id.my_setting_mobile_open_account)
    RelativeLayout register;
    @BindView(R.id.ll_my_setting_my_income)
    LinearLayout llMyIncome;
    @BindView(R.id.ll_my_setting_about_me)
    LinearLayout llAboutme;
    private String name = "";
    private String siteName = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.my_setting_fragment_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        name = SharePreferenceUtil.getLoginSpStringValue("employeeName");
        siteName = SharePreferenceUtil.getLoginSpStringValue("siteName");
        tvName.setText(name);
        tvSite.setText(siteName);
        tvInnerDistribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InnerActivity.class);
                intent.putExtra("innerType", 1);
                startActivity(intent);
            }
        });
        ivInnerReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InnerActivity.class);
                intent.putExtra("innerType", 2);
                startActivity(intent);
            }
        });
        ivRegisterBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterBottleActivity.class);
                startActivity(intent);
            }
        });
        llUnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ILogoutPresenter(MySettingFragment.this).logout();
            }
        });
        out_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransferStorageListActivity.class);
                intent.putExtra("TransferType", "0");
                startActivity(intent);
            }
        });
        in_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransferStorageListActivity.class);
                intent.putExtra("TransferType", "1");
                startActivity(intent);
            }
        });
        myBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyBottleActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterEmployerActivity.class);
                startActivity(intent);
            }
        });
        llMyIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyIncomeActivity.class);
                startActivity(intent);
            }
        });
        llAboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CurrentVersionInfoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEventBean event) {
        name = event.getUserLoginBean().getData().getEmployeeName();
        siteName = event.getUserLoginBean().getData().getSiteName();
        tvName.setText(name);
        tvSite.setText(siteName);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LogoutEvent messageEvent) {
        Intent goLogin = new Intent(getActivity(), LoginActivity.class);
        startActivity(goLogin);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
