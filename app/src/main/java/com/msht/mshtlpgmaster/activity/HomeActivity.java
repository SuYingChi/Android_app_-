package com.msht.mshtlpgmaster.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msht.mshtlpgmaster.Bean.AppInfoBean;
import com.msht.mshtlpgmaster.Bean.RefreshOrdersListBean;
import com.msht.mshtlpgmaster.Bean.SimpleEventbusBean;
import com.msht.mshtlpgmaster.Present.IGetNewestAppInfoPresenter;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.adapter.BaseLazyFragmentPagerAdapter;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.customView.SlidableViewPager;
import com.msht.mshtlpgmaster.fragment.BaseLazyFragment;
import com.msht.mshtlpgmaster.fragment.DispatchOrdersLazyFragement;
import com.msht.mshtlpgmaster.fragment.MySettingFragment;
import com.msht.mshtlpgmaster.fragment.OrdersListLazyFragment;
import com.msht.mshtlpgmaster.services.DownLoadApkService;
import com.msht.mshtlpgmaster.util.AppUtil;
import com.msht.mshtlpgmaster.util.LogUtils;
import com.msht.mshtlpgmaster.util.PermissionUtils;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;
import com.msht.mshtlpgmaster.viewInterface.IUpdateVersionView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeActivity extends BaseActivity implements IUpdateVersionView, PermissionUtils.PermissionRequestFinishListener {


    long exitTime;
    OrdersListLazyFragment f0;
    DispatchOrdersLazyFragement f1;
    MySettingFragment f2;
    List<BaseLazyFragment> list_fragment = new ArrayList<BaseLazyFragment>();
    @BindView(R.id.vp)
    SlidableViewPager vp;
    @BindView(R.id.iv_tab0)
    ImageView ivTab0;
    @BindView(R.id.iv_tab1)
    ImageView ivTab1;
    @BindView(R.id.iv_tab2)
    ImageView ivTab2;
    @BindView(R.id.tv_tab0)
    TextView tvTab0;
    @BindView(R.id.tv_tab1)
    TextView tvTab1;
    @BindView(R.id.tv_tab2)
    TextView tvTab2;
    @BindView(R.id.ll_tab0)
    LinearLayout llTab0;
    @BindView(R.id.ll_tab1)
    LinearLayout llTab1;
    @BindView(R.id.ll_tab2)
    LinearLayout llTab2;
    private Unbinder unbinder;
    private int vision;
    private String url;
    private String tvTitle;
    private String tvUpdateDetail;
    private String tvNewVersion;
    private String tvApkSize;
    private long apksize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        unbinder = ButterKnife.bind(this);
        initView();
        checkVersion();
    }

    private void checkVersion() {
        vision = AppUtil.getVersion(this);
        new IGetNewestAppInfoPresenter(this).getNewestAppInfo();
    }

    private void initView() {
        f0 = new OrdersListLazyFragment();
        f1 = new DispatchOrdersLazyFragement();
        f2 = new MySettingFragment();
        list_fragment.add(f0);
        list_fragment.add(f1);
        list_fragment.add(f2);


        vp.setAdapter(new BaseLazyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment));
        vp.setCurrentItem(SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM));
        initBottom(SharePreferenceUtil.getLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM));
        vp.setOffscreenPageLimit(3);

        vp.setScanScroll(false);
        vp.setPageTransformer(true, null);

    }

    private void initBottom(Integer item) {
        if (item == 0) {
            ivTab0.setImageResource(R.drawable.order);
            ivTab1.setImageResource(R.drawable.dispatch_order_unselected);
            ivTab2.setImageResource(R.drawable.my_setting_unselected);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));

        }
        if (item == 1) {
            ivTab0.setImageResource(R.drawable.order_unselected);
            ivTab1.setImageResource(R.drawable.dispatch_order);
            ivTab2.setImageResource(R.drawable.my_setting_unselected);


            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));

        }
        if (item == 2) {
            ivTab0.setImageResource(R.drawable.order_unselected);
            ivTab1.setImageResource(R.drawable.dispatch_order_unselected);
            ivTab2.setImageResource(R.drawable.my_setting);


            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.text_enable_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));

        }

    }

    @OnClick({R.id.ll_tab0, R.id.ll_tab1, R.id.ll_tab2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab0:
                if (vp.getCurrentItem() != 0) {
                    initBottom(0);
                    vp.setCurrentItem(0, false);
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM, 0);
                }
                break;
            case R.id.ll_tab1:
                if (vp.getCurrentItem() != 1) {
                    initBottom(1);
                    vp.setCurrentItem(1, false);
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM, 1);
                }
                break;
            case R.id.ll_tab2:
                if (vp.getCurrentItem() != 2) {
                    vp.setCurrentItem(2, false);
                    initBottom(2);
                    SharePreferenceUtil.setLoginSpIntValue(Constants.HOME_ACTIVITY_BOTTOM_TAB_ITEM, 2);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                PopUtil.toastInBottom(R.string.exit_App);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list_fragment.clear();
        f0 = null;
        f1 = null;
        f2 = null;
        unbinder.unbind();
    }



    //在别的页面再次开启首页时回调
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("HomeActivity", "onNewIntent: ");
    }

    //打开别的页面后再跳回主页时回调
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("HomeActivity", "onRestart: ");
        //EventBus.getDefault().postSticky(new RefreshOrdersListBean());
        if (vp.getCurrentItem() == 0) {
            f0.refreshOrdersList();
        }
    }

    @Override
    public void onGetNewestAppInfoSuccess(AppInfoBean appInfoBean) {
        tvNewVersion = String.format("液化气移动终端V%s", appInfoBean.getData().getVersion());
        tvApkSize = String.format("安装包大小：%.2fMB", Float.valueOf(appInfoBean.getData().getApkSize()) / 100000);
        apksize = (long) (Float.valueOf(appInfoBean.getData().getApkSize()) * 1000);
        tvTitle = appInfoBean.getData().getTitle();
        tvUpdateDetail = appInfoBean.getData().getRemarks();
        url = appInfoBean.getData().getUrl();
        if (Integer.valueOf(appInfoBean.getData().getVersion()) > vision) {
            if (!AppUtil.isWifiConnect()) {
                if (AppUtil.isNetworkAvailable()) {
                    PopUtil.showComfirmDialog(HomeActivity.this, "下载提示", "当前版本为V" + vision
                                    + ",最新版本为V" + appInfoBean.getData().getVersion()+"\n"+"新版本更新如下"
                                   +"\n"+ String.format("当前不在WiFi环境，安装包大小：%.2fMB,请确认是否使用流量下载", Float.valueOf(appInfoBean.getData().getApkSize()) / 1000)+"\n"+"\n"+AppUtil.isNumeric(appInfoBean.getData().getRemarks())+"\n"+"\n"+"是否更新到最新版本？",
                            "取消", "确定更新", null, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PermissionUtils.requestPermissions(HomeActivity.this, HomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW);
                                }
                            }, true);
                } else {
                    PopUtil.toastInBottom("无可用网络下载安装包");
                }
            } else {
                PopUtil.showComfirmDialog(HomeActivity.this, appInfoBean.getData().getTitle(), "当前版本为V" + vision
                                + ",最新版本为V" + appInfoBean.getData().getVersion()+"\n"+"新版本更新如下"
                               +"\n"+"\n"+AppUtil.isNumeric(appInfoBean.getData().getRemarks())+"\n"+"\n"+"是否更新到最新版本？",
                        "取消", "确定更新", null, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PermissionUtils.requestPermissions(HomeActivity.this, HomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            }
                        }, true);
            }
        }

    }

    @Override
    public void onBackFromSettingPage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(HomeActivity.this)) {
                Intent intent = new Intent(this, DownLoadApkService.class);
                intent.putExtra("url", url);
                intent.putExtra("apksize", apksize);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                LogUtils.d("DownLoadApk", "onPermissionRequestSuccess: ");
                startService(intent);
            }
        }
    }

    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        PopUtil.toastInBottom("请允许LPG写入SD卡");
    }

    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        if (permissions.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Intent intent = new Intent(this, DownLoadApkService.class);
            intent.putExtra("url", url);
            intent.putExtra("apksize", apksize);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            LogUtils.d("DownLoadApk", "onPermissionRequestSuccess: ");
            startService(intent);
        }
    }
}

