package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;

import java.text.BreakIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SendCustomerOrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.scan_delive_topbar)
    TopBarView topBarView;
    @BindView(R.id.id_select_address_layout)
    View layoutSelectAddress;
    @BindView(R.id.id_tv_address)
    TextView textView;
    @BindView(R.id.id_tv_username)
    TextView tvUserName;
    @BindView(R.id.id_tv_mobile)
    TextView tvMobile;
    @BindView(R.id.id_tv_elevator)
    TextView tvElevator;
    @BindView(R.id.id_tv_amount1)
    TextView tvFive;
    @BindView(R.id.id_tv_amount2)
    TextView tvFifteen;
    @BindView(R.id.id_tv_amount3)
    TextView tvFifty;
    @BindView(R.id.id_total_amount)
    TextView tvTotal;
    @BindView(R.id.id_tv_isdeliver)
    TextView tvIsDeliver;
    private static final int SELECT_SUCCESS_CODE=1;
    private Context mContext;
    private Unbinder unbinder;
    private String mArea;
    private String mCity;
    private String locationName;
    private String addressDescribe;
    private String latitude;
    private String longitude;
    private BreakIterator tvAddress;
    private String fiveWeightCount;
    private String fifteenWeightCount;
    private String fiftyWeightCount;
    private String username;
    private String phone;
    private String isDelivery;
    private String name;
    private String sex;
    private String isElevator;
    private String ridgepole;
    private String floor;
    private String room;
    private String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_customer_order);
        unbinder = ButterKnife.bind(this);
        mContext=this;
        layoutSelectAddress.setOnClickListener(this);
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
        Intent intent=getIntent();
        fiveWeightCount =  intent.getStringExtra("fiveCount");
        fifteenWeightCount =  intent.getStringExtra("fifteenWeightCount");
        fiftyWeightCount =  intent.getStringExtra("fiftyWeightCount");
        tvFive.setText(fiveWeightCount);
        tvFifteen.setText(fifteenWeightCount);
        tvFifty.setText(fiftyWeightCount);
        isDelivery = intent.getStringExtra("isDelivery");
        tvIsDeliver.setText(isDelivery);
        total = intent.getStringExtra("total");
        tvTotal.setText(total);
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
        Intent intent=new Intent(mContext,EditAddressActivity.class);
        startActivityForResult(intent,SELECT_SUCCESS_CODE);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case SELECT_SUCCESS_CODE:
                if (data!=null){
                    mArea=data.getStringExtra("mArea");
                    mCity=data.getStringExtra("mCity");
                    locationName=data.getStringExtra("addressName");
                    addressDescribe=data.getStringExtra("addressDescribe");
                    latitude=data.getStringExtra("lat");
                    longitude=data.getStringExtra("lon");
                    name = data.getStringExtra("name");
                    tvUserName.setText(name);
                    sex = data.getStringExtra("sex");
                    phone = data.getStringExtra("phone");
                    tvMobile.setText(phone);
                    isElevator =data.getStringExtra("isElevator");
                    tvElevator.setText(isElevator+ "电梯");
                    ridgepole = data.getStringExtra("Ridgepole");
                    floor = data.getStringExtra("Floor");
                    room = data.getStringExtra("Room");
                    textView.setText(addressDescribe+ridgepole+"栋"+floor+"楼"+room+"房");
                }
                break;
            default:
                break;
        }

    }
}
