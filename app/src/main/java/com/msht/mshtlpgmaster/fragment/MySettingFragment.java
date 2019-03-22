package com.msht.mshtlpgmaster.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtlpgmaster.Bean.IMyMenuBean;
import com.msht.mshtlpgmaster.Bean.LoginEventBean;
import com.msht.mshtlpgmaster.Bean.LogoutEvent;
import com.msht.mshtlpgmaster.Present.ILogoutPresenter;
import com.msht.mshtlpgmaster.Present.MyMenu;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.activity.CurrentVersionInfoActivity;
import com.msht.mshtlpgmaster.activity.LoginActivity;
import com.msht.mshtlpgmaster.activity.MyBottleActivity;
import com.msht.mshtlpgmaster.activity.InnerActivity;
import com.msht.mshtlpgmaster.activity.MyIncomeActivity;
import com.msht.mshtlpgmaster.activity.RegisterEmployerActivity;
import com.msht.mshtlpgmaster.activity.RegisterBottleActivity;
import com.msht.mshtlpgmaster.activity.TransferStorageListActivity;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.DimenUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;
import com.msht.mshtlpgmaster.viewInterface.IMyMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MySettingFragment extends BaseLazyFragment implements IMyMenu {
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
    @BindView(R.id.my_setting_in_storage)
    RelativeLayout in_transfer;
    @BindView(R.id.my_setting_mobile_open_account)
    RelativeLayout register;
    @BindView(R.id.ll_my_setting_my_income)
    LinearLayout llMyIncome;
    @BindView(R.id.ll_my_setting_about_me)
    LinearLayout llAboutme;
    @BindView(R.id.rlt_get_steel_bottle_out)
    RelativeLayout rltGetout;
    @BindView(R.id.rlt_my_steel_bottle)
    RelativeLayout rltMyBottlt;
    @BindView(R.id.rlt_transfer)
    RelativeLayout rlt_transfer;
    @BindView(R.id.rlt_register_steel_bottle)
    RelativeLayout rltRegister;
    @BindView(R.id.rlt_setting_get_steel_bottle_in)
    RelativeLayout rltIn;
    @BindView(R.id.my_setting_fragment_layout_topbar)
    LinearLayout topbar;
    @BindView(R.id.my_setting_online)
    TextView tvOnline;
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
        topbar.setPadding(0, ImmersionBar.getStatusBarHeight(getActivity())+ DimenUtil.dip2px(5),0,DimenUtil.dip2px(5));
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
        rlt_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransferStorageListActivity.class);
                //  intent.putExtra("TransferType", "0");
                intent.putExtra("TransferType", "2");
                startActivity(intent);
            }
        });
        in_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransferStorageListActivity.class);
                //   intent.putExtra("TransferType", "1");
                intent.putExtra("TransferType", "2");
                startActivity(intent);
            }
        });
        rltMyBottlt.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onResume() {
        super.onResume();
        MyMenu.getMyIncome(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEventBean event) {
        name = event.getUserLoginBean().getData().getEmployeeName();
        siteName = event.getUserLoginBean().getData().getSiteName();
        tvName.setText(name);
        tvSite.setText(siteName);
        tvOnline.setText("在线");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LogoutEvent messageEvent) {
        Intent goLogin = new Intent(getActivity(), LoginActivity.class);
        startActivity(goLogin);
        name = "未登录";
        siteName = "未登录";
        tvName.setText(name);
        tvSite.setText(siteName);
        tvOnline.setText("离线");
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onGetMyMenuSuccess(String s) {
        IMyMenuBean bean = GsonUtil.getGson().fromJson(s, IMyMenuBean.class);
        if (bean.getData().contains("领瓶出库")) {
            rltGetout.setVisibility(View.VISIBLE);
        } else {
            rltGetout.setVisibility(View.GONE);
        }
        if (bean.getData().contains("返瓶入库")) {
            rltIn.setVisibility(View.VISIBLE);
        } else {
            rltIn.setVisibility(View.GONE);
        }
        if (bean.getData().contains("钢瓶注册")) {
            rltRegister.setVisibility(View.VISIBLE);
        } else {
            rltRegister.setVisibility(View.INVISIBLE);
        }
        if (bean.getData().contains("调拨出库")) {
            rlt_transfer.setVisibility(View.VISIBLE);
        } else {
            rlt_transfer.setVisibility(View.INVISIBLE);
        }
        if (bean.getData().contains("我的钢瓶")) {
            rltMyBottlt.setVisibility(View.VISIBLE);
        } else {
            rltMyBottlt.setVisibility(View.GONE);
        }

    }


}
