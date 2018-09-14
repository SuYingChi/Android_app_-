package com.msht.mshtlpgmaster.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.msht.mshtlpgmaster.Bean.AppInfoBean;
import com.msht.mshtlpgmaster.Present.IGetNewestAppInfoPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.customView.TopBarView;
import com.msht.mshtlpgmaster.services.DownLoadApkService;
import com.msht.mshtlpgmaster.util.AppUtil;
import com.msht.mshtlpgmaster.util.LogUtils;
import com.msht.mshtlpgmaster.util.PermissionUtils;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IUpdateVersionView;

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
    private long apksize;

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
        tvNewVersion.setText(String.format("液化气移动终端V%s", appInfoBean.getData().getVersion()));
        tvApkSize.setText(String.format("安装包大小：%.2fMB", Float.valueOf(appInfoBean.getData().getApkSize())/1000));
        apksize =  (long)(Float.valueOf(appInfoBean.getData().getApkSize())*1000);
        tvTitle.setText(appInfoBean.getData().getTitle());
        tvUpdateDetail.setText(appInfoBean.getData().getRemarks());
        url = appInfoBean.getData().getUrl();
        if (Integer.valueOf(appInfoBean.getData().getVersion()) > vision) {
            tvUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!AppUtil.isWifiConnect()){
                        if(AppUtil.isNetworkAvailable()) {
                            PopUtil.showComfirmDialog(UpdateVersionActivity.this, "下载提示",   "当前版本为V" + AppUtil.getVerName(UpdateVersionActivity.this)
                                            + ",最新版本为V" + appInfoBean.getData().getVersion()
                                            + ",是否更新到最新版本？"+String.format("当前不在WiFi环境，安装包大小：%.2fMB,请确认是否使用流量下载", Float.valueOf(appInfoBean.getData().getApkSize()) / 1000),
                                    "取消", "确定更新", null, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            PermissionUtils.requestPermissions(UpdateVersionActivity.this, UpdateVersionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                                        }
                                    }, true);
                        }else {
                            PopUtil.toastInBottom("无可用网络下载安装包");
                        }
                    }else {
                        PopUtil.showComfirmDialog(UpdateVersionActivity.this, appInfoBean.getData().getTitle(), "当前版本为V" + AppUtil.getVerName(UpdateVersionActivity.this)
                                        + ",最新版本为V" + appInfoBean.getData().getVersion()
                                        + ",是否更新到最新版本？",
                                "取消", "确定更新", null, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        PermissionUtils.requestPermissions(UpdateVersionActivity.this, UpdateVersionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                                    }
                                }, true);
                    }
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
        intent.putExtra("apksize",apksize);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        LogUtils.d("DownLoadApk", "onPermissionRequestSuccess: ");
        startService(intent);
    }
}
