package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.AppUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentVersionInfoActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBarView topBarView;
    @BindView(R.id.current_version)
    TextView tvCurrentAppInfo;
    @BindView(R.id.check_is_update)
    TextView tvCheckUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_version_info_layout);
        ButterKnife.bind(this);
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int vision = AppUtil.getVersion(this);
        tvCurrentAppInfo.setText("员工端液化气移动终端V"+vision);
        tvCheckUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(CurrentVersionInfoActivity.this,UpdateVersionActivity.class));
            }
        });

    }
}
