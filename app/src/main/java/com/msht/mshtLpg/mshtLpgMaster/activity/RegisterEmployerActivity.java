package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.RegisterBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IRegisterEmployerPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IRegisterEmployerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RegisterEmployerActivity extends BaseActivity implements IRegisterEmployerView {


    @BindView(R.id.topbar)
    TopBarView topBarView;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.spinner_sex)
    Spinner spinnerSex;
    @BindView(R.id.et_mobile_number)
    EditText etMobile;
    @BindView(R.id.tv_user_location)
    TextView tvLocation;
    @BindView(R.id.spinner_elevator)
    Spinner SpinnerElevator;
    @BindView(R.id.et_floor)
    EditText etFloor;
    @BindView(R.id.et_room)
    EditText etRoom;
    @BindView(R.id.save_user_location)
    Button postRegisterBtn;
    private Unbinder unbinder;
    private String name;
    private String sex;
    private String mobileNum;
    private String location;
    private String latitude;
    private String longitude;
    private String isElevator;
    private String room;
    private String floor;
    private IRegisterEmployerPresenter iRegisterEmployerPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_employer_layout);
        iRegisterEmployerPresenter = new IRegisterEmployerPresenter(this);
        unbinder = ButterKnife.bind(this);
        postRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRegisterEmployerPresenter.postRegisterEmployer();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onRegisterSuccess(RegisterBean registerBean) {

    }

    @Override
    public String getName() {
        return name = etName.getText().toString();
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public String getMobile() {
        return mobileNum = etMobile.getText().toString();
    }

    @Override
    public String getLocation() {
        return location = tvLocation.toString();
    }

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    @Override
    public String getIsElevator() {
        return isElevator;
    }

    @Override
    public String getRoom() {
        return room = etRoom.getText().toString();
    }

    @Override
    public String getFloor() {
        return floor = etFloor.getText().toString();
    }
}
