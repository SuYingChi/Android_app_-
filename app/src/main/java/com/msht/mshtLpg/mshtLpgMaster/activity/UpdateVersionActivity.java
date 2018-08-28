package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.AppInfoBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IGetNewestAppInfoPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.util.AppUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
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
        vision = AppUtil.getVersion(this);
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new IGetNewestAppInfoPresenter(this).getNewestAppInfo();

    }

    @Override
    public void onGetNewestAppInfoSuccess(AppInfoBean appInfoBean) {
        tvNewVersion.setText(appInfoBean.getData().getVersion());
        tvApkSize.setText(appInfoBean.getData().getApkSize());
        tvTitle.setText(appInfoBean.getData().getTitle());
        tvUpdateDetail.setText(appInfoBean.getData().getDesc());
        if (Integer.valueOf(appInfoBean.getData().getVersion()) > vision) {
            tvUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopUtil.showComfirmDialog(UpdateVersionActivity.this, appInfoBean.getData().getTitle(), "当前版本为V" + AppUtil.getVerName(UpdateVersionActivity.this)
                                    + ",最新版本为V" + appInfoBean.getData().getVersion()
                                    + ",是否更新到最新版本？",
                            "取消", "确定更新", null, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AppUtil.goMarket(UpdateVersionActivity.this);
                                }
                            }, true);
                }
            });
        } else {
            tvUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopUtil.toastInBottom("当前已经是最新版本");
                }
            });
        }
    }
}
