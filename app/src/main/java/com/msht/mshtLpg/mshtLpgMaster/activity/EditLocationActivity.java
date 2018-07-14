package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.SpinnerAdapter;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditLocationActivity extends BaseActivity {
    @BindView(R.id.top_bar)
    TopBarView topBarView;
    @BindView(R.id.spinner_elevator)
    Spinner spinner;
    @BindView(R.id.et_floor)
    EditText editText;
    @BindView(R.id.save_user_location)
    Button btn;
    private SpinnerAdapter spinnerAdapter;
    List<String> list = new ArrayList<String>();
    private int selectElevator  = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_location_layout);
        ButterKnife.bind(this);
        spinnerAdapter = new SpinnerAdapter(this);
        spinner.setAdapter(spinnerAdapter);
        list.add("是");
        list.add("否");
        spinnerAdapter.setData(list);
        spinnerAdapter.notifyDataSetChanged();
        spinner.setSelection(selectElevator);
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.putExtra("elevator",selectElevator );
                intent.putExtra("floor",Integer.valueOf(editText.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("elevator",selectElevator );
                intent.putExtra("floor",Integer.valueOf(editText.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    selectElevator  = 0;
                }else if(position==1){
                    selectElevator = 1;
                }
            }
        });
    }


}
