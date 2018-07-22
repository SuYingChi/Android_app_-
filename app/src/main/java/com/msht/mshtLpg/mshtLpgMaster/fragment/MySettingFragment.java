package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.LoginEventBean;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.InnerFetchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MySettingFragment extends BaseLazyFragment{
    @BindView(R.id.my_setting_username)
        TextView tvName;
    @BindView(R.id.my_setting_user_location)
    TextView tvSite;
    @BindView(R.id.iv_my_setting_get_steel_bottle_out_warehouse)
    ImageView tvInnerDistribute;
    private String name="";
    private String siteName="";

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
        tvInnerDistribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),InnerFetchActivity.class));
            }
        });
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEventBean event) {
         name = event.getUserLoginBean().getData().getEmployeeName();
         siteName = event.getUserLoginBean().getData().getSiteName();
        tvName.setText(name);
        tvSite.setText(siteName);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
