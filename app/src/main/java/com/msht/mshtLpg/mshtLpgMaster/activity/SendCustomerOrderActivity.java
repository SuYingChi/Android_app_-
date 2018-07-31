package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReplacePlaceOrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.scan_delive_topbar)
    TopBarView topBarView;
    @BindView(R.id.id_select_address_layout)
    View layoutSelectAddress;
    private static final int SELECT_SUCCESS_CODE=1;
    private Context mContext;
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_place_order);
        unbinder = ButterKnife.bind(this);
        mContext=this;
        layoutSelectAddress.setOnClickListener(this);
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_select_address_layout:
                onStartDeliveryAddress();
                break;
                default:
                    break;
        }
    }
    private void onStartDeliveryAddress() {
        Intent intent=new Intent(mContext,EditLocationActivity.class);
        startActivityForResult(intent,SELECT_SUCCESS_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

    }
}
