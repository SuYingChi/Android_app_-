package com.msht.mshtlpgmaster.activity;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OSUtils;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.customView.LoadingDialog;
import com.msht.mshtlpgmaster.util.AppUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.Bean.LogoutEvent;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;
import com.msht.mshtlpgmaster.viewInterface.IBaseView;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;


/**
 * @author mshtyfb
 */
public class BaseActivity extends AppCompatActivity implements IBaseView, SwipeBackActivityBase {

    protected LoadingDialog centerLoadingDialog;
    private SwipeBackActivityHelper mHelper;
    private ImmersionBar mImmersionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //这个框架滑动返回与immersionbar 不兼容
        /* initSwipeBackFinish();*/
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSoftInPutMode();
        initStateBar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onDestroy() {
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
        MobclickAgent.onPause(this);
        OkHttpUtils.getInstance().cancelTag(this);
        super.onDestroy();
    }

    protected void initStateBar() {
        if (!OSUtils.isEMUI3_0()) {
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.statusBarDarkFont(true, 0.2f).navigationBarEnable(false).init();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                // activity.getWindow().setNavigationBarColor(Color.parseColor("#ff000000"));
                getWindow().setNavigationBarColor(Color.BLACK);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (ImmersionBar.hasNavigationBar(this)) {
                    getWindow().getDecorView().setFitsSystemWindows(true);
                }
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
    }

    private void setSoftInPutMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    @Override
    public void showLoading() {
        showCenterLodaingDialog();
    }

    @Override
    public void dismissLoading() {
        hideCenterLoadingDialog();
    }

    protected void showCenterLodaingDialog() {
        if (!isFinishing() && centerLoadingDialog == null) {
            centerLoadingDialog = new LoadingDialog(this);
            centerLoadingDialog.show();
        } else if (!isFinishing() && !centerLoadingDialog.isShowing()) {
            centerLoadingDialog.show();
        }
    }


    protected void hideCenterLoadingDialog() {
        if (centerLoadingDialog != null && centerLoadingDialog.isShowing() && !isFinishing()) {
            centerLoadingDialog.dismiss();
        }
    }

    @Override
    public void onError(String s) {
        if (!AppUtil.isNetworkAvailable()) {
            PopUtil.toastInBottom(R.string.net_no_available);
            onNetError();
        } else if (TextUtils.isEmpty(SharePreferenceUtil.getInstance().getToken())) {
            AppUtil.logout();
            PopUtil.toastInBottom(s);
            if (this instanceof LoginActivity) {
                ((LoginActivity) this).reinputUserinfo();
            } else {
                Intent goLogin = new Intent(this, LoginActivity.class);
                startActivity(goLogin);
                finish();
            }
        } else {
            PopUtil.toastInBottom(s);
            switch (s) {
                case "未登录":
                    AppUtil.logout();
                    Intent goLogin = new Intent(this, LoginActivity.class);
                    startActivity(goLogin);
                    finish();
                    break;
                default:

                    break;
            }
        }
    }

    @Override
    public String getToken() {
        return SharePreferenceUtil.getInstance().getToken();
    }

    @Override
    public String getEmployerId() {
        return SharePreferenceUtil.getLoginSpStringValue(Constants.EMPLOYERID);
    }

    @Override
    public void onLogout() {
        AppUtil.logout();
    }

    @Override
    public void onNetError() {

    }

    @Override
    public String getOrderType() {
        return SharePreferenceUtil.getInstance().getOrderType();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

}
