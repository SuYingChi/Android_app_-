package com.msht.mshtlpgmaster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.customView.TopBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DeliveryAddressActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.id_re_new_address)
    View layoutSelectAddress;
    @BindView(R.id.scan_delive_topbar)
    TopBarView topBarView;
    private static final int CREATE_SUCCESS_CODE = 1;
    private Context mContext;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);
        mContext = this;
        unbinder = ButterKnife.bind(this);
        layoutSelectAddress.setOnClickListener(this);
        topBarView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_re_new_address:
                onStartEditLocation();
                break;
            case R.id.scan_delive_topbar:
                finish();
                break;
            default:
                break;
        }
    }

    private void onStartEditLocation() {
        Intent intent = new Intent(mContext, EditCustomerAddressActivity.class);
        startActivityForResult(intent, CREATE_SUCCESS_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

    }
}
