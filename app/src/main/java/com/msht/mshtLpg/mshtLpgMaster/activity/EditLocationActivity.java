package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.msht.mshtLpg.mshtLpgMaster.Bean.DeliveryBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IDeliveryPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.SpinnerAdapter;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IDeliveryView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EditLocationActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBarView topBarView;
    @BindView(R.id.spinner_elevator)
    Spinner spinner;
    @BindView(R.id.et_floor)
    EditText editText;
    @BindView(R.id.save_user_location)
    Button btn;
    private SpinnerAdapter spinnerAdapter;
    List<String> list = new ArrayList<String>();
    private Unbinder unbinder;
    private String floor;
    private String isElevator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_location_layout);
        unbinder = ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        floor = bundle.getString(Constants.FLOOR);
        editText.setText(floor);
        isElevator = bundle.getString(Constants.IS_ELEVATOR);
        spinnerAdapter = new SpinnerAdapter(this);
        spinner.setAdapter(spinnerAdapter);
        list.add("无电梯");
        list.add("有电梯");
        spinnerAdapter.setData(list);
        spinnerAdapter.notifyDataSetChanged();
        spinner.setSelection(Integer.valueOf(isElevator));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                isElevator  = position+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.putExtra(Constants.IS_ELEVATOR,isElevator );
                intent.putExtra(Constants.FLOOR,Integer.valueOf(editText.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Constants.IS_ELEVATOR,isElevator );
                if(TextUtils.isEmpty(editText.getText().toString())){
                    PopUtil.toastInBottom("楼层不能为空");
                }else {
                    intent.putExtra(Constants.FLOOR,editText.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

    }
}
