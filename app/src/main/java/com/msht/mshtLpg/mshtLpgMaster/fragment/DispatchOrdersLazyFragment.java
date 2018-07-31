package com.msht.mshtLpg.mshtLpgMaster.fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.SendCustomerOrderActivity;

import butterknife.BindView;

public class DispatchOrdersLazyFragment extends BaseLazyFragment implements View.OnClickListener {
    @BindView(R.id.id_btn_next)
    Button btnNext;
    @BindView(R.id.id_total_amount)
    TextView tvCount;
    @BindView(R.id.id_add_btn1)
    Button btnAdd1;
    @BindView(R.id.id_add_btn2)
    Button btnAdd2;
    @BindView(R.id.id_add_btn3)
    Button btnAdd3;
    @BindView(R.id.id_subtract_btn1)
    Button btnSubtract1;
    @BindView(R.id.id_subtract_btn2)
    Button btnSubtract2;
    @BindView(R.id.id_subtract_btn3)
    Button btnSubtract3;
    @BindView(R.id.id_tv_num1)
    TextView tvFiveWeightCount;
    @BindView(R.id.id_tv_num2)
    TextView tvFifteenWeightCount;
    @BindView(R.id.id_tv_num3)
    TextView tvFiftyWeightCount;
    private int fiveWeightCount;
    private int fifteenWeightCount;
    private int fiftyWeightCount;
    private Context mContext;
    @Override
    protected int setLayoutId() {
        return R.layout.frgment_dispatchorder_temp;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }
    @Override
    protected void initView() {
        super.initView();
        btnNext.setOnClickListener(this);
    }
    private void onStartSendOrder() {
        Intent intent=new Intent(getActivity(), SendCustomerOrderActivity.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_btn_next:
                onStartSendOrder();
                break;
            default:
                break;
        }
    }
}
