package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.AppInfoBean;
import com.msht.mshtLpg.mshtLpgMaster.Present.IGetNewestAppInfoPresenter;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.customView.TopBarView;
import com.msht.mshtLpg.mshtLpgMaster.services.DownLoadApkService;
import com.msht.mshtLpg.mshtLpgMaster.util.AppUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IUpdateVersionView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateVersionActivity extends BaseActivity implements IUpdateVersionView ,PermissionUtils.PermissionRequestFinishListener{
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
    private String url;

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
        url = appInfoBean.getData().getUrl();
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
                                        //AppUtil.goMarket(UpdateVersionActivity.this);
                                    PermissionUtils.requestPermissions(UpdateVersionActivity.this,UpdateVersionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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

    @Override
    public void onBackFromSettingPage() {

    }

    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        PopUtil.toastInBottom("请允许LPG写入SD卡");
    }

    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        Intent intent = new Intent(UpdateVersionActivity.this,DownLoadApkService.class);
        intent.putExtra("url", url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(intent);
    }
}
