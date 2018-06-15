package com.msht.mshtLpg.mshtLpgMaster.appInfoUtil;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.msht.mshtLpg.mshtLpgMaster.activity.BaseActivity;
import com.msht.mshtLpg.mshtLpgMaster.constant.AppInfoConstant;
import com.msht.mshtLpg.mshtLpgMaster.sharepreferenceUtil.SharePreferenceUtil;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class AppUtil {
    private static final String TAG = "AppUtil";
    private static PackageInfo packageInfo;
    public static boolean isAppInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            packageInfo= pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
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
}
