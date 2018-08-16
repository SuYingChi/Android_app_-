package com.msht.mshtLpg.mshtLpgMaster.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.DispatchCustomerOrderActivity;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class DispatchReturnOrdersFragment extends BaseLazyFragment {
    @BindView(R.id.id_btn_next)
    Button btnNext;
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
    Button tvFiveWeightCount;
    @BindView(R.id.id_tv_num2)
    Button tvFifteenWeightCount;
    @BindView(R.id.id_tv_num3)
    Button tvFiftyWeightCount;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.id_total_amount)
    TextView tvTotal;
    private int fiveWeightCount = 0;
    private int fifteenWeightCount = 0;
    private int fiftyWeightCount = 0;
    private int totalCount = 0;
    private String isDelivery = "配送";

    @Override
    protected int setLayoutId() {
        return R.layout.frgment_dispatchorder_temp;
    }
    @Override
    protected void initView() {
        super.initView();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb1){
                    isDelivery = "配送";
                }else if(checkedId == R.id.rb2){
                    isDelivery = "自提";
                }
            }
        });

    }
    @OnClick({R.id.id_btn_next, R.id.id_add_btn1, R.id.id_add_btn2,R.id.id_add_btn3,R.id.id_subtract_btn1,R.id.id_subtract_btn2,R.id.id_subtract_btn3,R.id.id_tv_num1,R.id.id_tv_num2,R.id.id_tv_num3})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.id_btn_next:
                if(totalCount==0){
                    PopUtil.toastInBottom("请选择要退的钢瓶");
                }else {
                    Intent intent = new Intent(getActivity(), DispatchCustomerOrderActivity.class);
                    intent.putExtra("dispatchOrdersType","2");
                    intent.putExtra("fiveCount", fiveWeightCount + "");
                    intent.putExtra("fifteenWeightCount", fifteenWeightCount + "");
                    intent.putExtra("fiftyWeightCount", fiftyWeightCount + "");
                    intent.putExtra("isDelivery", isDelivery + "");
                    intent.putExtra("total", totalCount + "");
                    startActivity(intent);
                }
                break;
            case R.id.id_add_btn1:
                fiveWeightCount++;
                totalCount++;
                tvTotal.setText(totalCount+"");
                tvFiveWeightCount.setText(fiveWeightCount+"");
                break;
            case R.id.id_add_btn2:
                fifteenWeightCount++;
                totalCount++;
                tvTotal.setText(totalCount+"");
                tvFifteenWeightCount.setText(fifteenWeightCount+"");
                break;
            case R.id.id_add_btn3:
                fiftyWeightCount++;
                totalCount++;
                tvTotal.setText(totalCount+"");
                tvFiftyWeightCount.setText(fiftyWeightCount+"");
                break;
            case R.id.id_subtract_btn1:
                fiveWeightCount--;
                totalCount--;
                tvTotal.setText(totalCount+"");
                tvFiveWeightCount.setText(fiveWeightCount+"");
                break;
            case R.id.id_subtract_btn2:
                fifteenWeightCount--;
                totalCount--;
                tvTotal.setText(totalCount+"");
                tvFifteenWeightCount.setText(fifteenWeightCount+"");
                break;
            case R.id.id_subtract_btn3:
                fiftyWeightCount--;
                totalCount--;
                tvTotal.setText(totalCount+"");
                tvFiftyWeightCount.setText(fiftyWeightCount+"");
                break;
            default:
                break;
        }
    }
}

