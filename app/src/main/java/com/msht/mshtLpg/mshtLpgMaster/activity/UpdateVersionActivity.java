package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.AppInfoBean;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.AppUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IUpdateVersionView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateVersionActivity extends BaseActivity implements IUpdateVersionView {
   @BindView(R.id.topbar)
    TopBarView topBarView;
   @BindView(R.id.update)
    TextView tvUpdate;
   @BindView(R.id.new_version)
   TextView tvNewVersion;
   @BindView(R.id.apk_size)
   TextView tvApkSize;
   @BindView(R.id.update_detail)
   TextView tvUpdateDetail;
   @BindView(R.id.title)
   TextView tvTitle;

    private int vision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_new_version_layout);
        ButterKnife.bind(this);
        // 获取本版本号，是否更新
         vision = AppUtil.getVersion(this);

    }

    @Override
    public void onGetNewestAppInfoSuccess(AppInfoBean appInfoBean) {

    }
}
