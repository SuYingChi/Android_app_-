package com.msht.mshtLpg.mshtLpgMaster.util;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.BaseActivity;
import com.msht.mshtLpg.mshtLpgMaster.activity.LoginActivity;
import com.msht.mshtLpg.mshtLpgMaster.activity.UpdateVersionActivity;
import com.msht.mshtLpg.mshtLpgMaster.activity.WebActivity;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseFragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class AppUtil {
    private static final String TAG = "AppUtil";
    private static PackageInfo packageInfo;

    public static boolean isLPGInstalled(String packageName) {
        PackageManager pm = LPGApplication.getLPGApplicationContext().getPackageManager();
        boolean installed;
        try {
            packageInfo = pm.getPackageInfo(packageName, 0);
            if (packageInfo != null) {
                installed = true;
            } else {
                installed = false;
            }
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    public static void hideSoftInput(Activity mActivity) {
        try {
            IBinder mIBinder = mActivity.getCurrentFocus().getWindowToken();
            if (mIBinder != null && (mActivity.getSystemService(INPUT_METHOD_SERVICE)) != null) {
                ((InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(mIBinder, InputMethodManager.HIDE_NOT_ALWAYS);
            }

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) LPGApplication.getLPGApplicationContext()
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }
        return true;
    }

    public static void logout() {
        SharePreferenceUtil.getInstance().setToken("");
        SharePreferenceUtil.setLoginSpStringValue(Constants.EMPLOYERID, "");
        SharePreferenceUtil.setLoginSpStringValue("employeeName", "");
        SharePreferenceUtil.setLoginSpStringValue("siteName", "");
    }

    public static void goWeb(Context mContext, String url) {
        if (TextUtils.isEmpty(url))
            return;
        Intent goweb = new Intent(mContext, WebActivity.class);
        goweb.putExtra("url", url);
        mContext.startActivity(goweb);
    }

    public static void goLogin(Context mContext) {
        Intent go = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(go);
    }

    public static void hideInput(Context context, EditText et) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);

    }

    /**
     * 2 * 获取版本号 3 * @return 当前应用的版本号 4
     */
    public static int getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {

        }

        return verName;
    }

    public static void goMarket(Context context) {
        try {

            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());// id为包名
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(it);

        } catch (Exception e) {
            PopUtil.toastInBottom("请打开应用市场，搜索 " + context.getString(R.string.app_name));
            e.printStackTrace();
        }
    }

    public static void replaceFragment(BaseFragment showfragment, BaseFragment currentFragment, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(currentFragment).commit();
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.forward_enter, R.anim.forward_exit_fragment);
        transaction.replace(R.id.fl_my_container, showfragment).commit();
    }

    public static void showFragment(BaseFragment showfragment, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_my_container, showfragment).commit();
    }
}
