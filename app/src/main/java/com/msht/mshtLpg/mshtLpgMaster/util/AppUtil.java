package com.msht.mshtLpg.mshtLpgMaster.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.msht.mshtLpg.mshtLpgMaster.activity.LoginActivity;
import com.msht.mshtLpg.mshtLpgMaster.activity.WebActivity;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class AppUtil {
    private static final String TAG = "AppUtil";
    private static PackageInfo packageInfo;
    public static boolean isLPGInstalled( String packageName) {
        PackageManager pm = LPGApplication.getLPGApplicationContext().getPackageManager();
        boolean installed;
        try {
            packageInfo= pm.getPackageInfo(packageName, 0);
            if(packageInfo!=null){
                installed = true;
            }else {
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
                if (mIBinder != null&&(mActivity.getSystemService(INPUT_METHOD_SERVICE)) != null) {
                    ((InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(mIBinder, InputMethodManager.HIDE_NOT_ALWAYS);
                }

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
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
    public  static void logout() {
        SharePreferenceUtil.getInstance().setToken("");
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


}
