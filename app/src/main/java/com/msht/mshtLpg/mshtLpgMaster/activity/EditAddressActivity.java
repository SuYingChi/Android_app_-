package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.MySheetDialog;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.ShaUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EditAddressActivity extends BaseActivity {
    @BindView(R.id.top_bar)
    TopBarView topBarView;
    @BindView(R.id.id_et_floor)
    EditText editText;
    @BindView(R.id.save_user_location)
    Button btn;
    @BindView(R.id.id_sex_layout)
    View layoutSex  ;
    @BindView(R.id.id_elevator_layout)
    View layoutElevator;
    @BindView(R.id.id_address_layout)
    View layoutAddress;
    @BindView(R.id.tv_elevator)
    TextView tvElevator;
    @BindView(R.id.et_sex)
    TextView tvSex;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    List<String> list = new ArrayList<String>();
    private Unbinder unbinder;
    private String floor;
    private String isElevator="1";
    private String isSex="1";
    private String locationName;
    private String addressDescribe;
    private String latitude;
    private String longitude;
    private String mCity;
    private String mArea;
    private Context mContext;
    private static final int SELECT_SUCCESS_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        mContext=this;
        unbinder = ButterKnife.bind(this);
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
                    latitude=data.getStringExtra("latitude");
                    longitude=data.getStringExtra("longitude");
                    tvAddress.setText(addressDescribe);
                }
                break;
                default:
                    break;
        }

    }

    @OnClick( {R.id.id_sex_layout,R.id.id_elevator_layout,R.id.id_address_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_sex_layout:
                onSelectSex();
                break;
            case R.id.id_elevator_layout:
                onSelectElevator();
                break;
            case R.id.id_address_layout:
                onSelectAddress();
                break;
            default:
                break;
        }
    }
    private void onSelectAddress() {
        Intent intent=new Intent(mContext,SelectAddressActivity.class);
        startActivityForResult(intent,SELECT_SUCCESS_CODE);
    }
    private void onSelectElevator() {
        String[] mList=new String[]{"无电梯","有电梯"};
        String mTitle="是否有电梯";
        new MySheetDialog(this,mTitle,mList).builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .setOnSheetItemClickListener(new MySheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(String item, String string) {
                        tvElevator.setText(string);
                        isElevator=item;
                    }
                }).show();
    }
    private void onSelectSex() {
        String[] mList=new String[]{"女","男"};
        String mTitle="请选择性别";
        new MySheetDialog(this,mTitle,mList).builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .setOnSheetItemClickListener(new MySheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(String item, String string) {
                        tvSex.setText(string);
                        isSex=item;
                    }
                }).show();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
