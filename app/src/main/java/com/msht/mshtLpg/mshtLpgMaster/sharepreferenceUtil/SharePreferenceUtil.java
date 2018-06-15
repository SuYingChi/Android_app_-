package com.msht.mshtLpg.mshtLpgMaster.sharepreferenceUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import com.msht.mshtLpg.mshtLpgMaster.appInfoUtil.AppUtil;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.constant.AppInfoConstant;

public class SharePreferenceUtil {

    private static SharePreferenceUtil sharePreferenceUtil ;
    private String token;

    public static SharePreferenceUtil getInstance( ) {
        if(sharePreferenceUtil == null) {
            synchronized (SharePreferenceUtil.class) {
                if (sharePreferenceUtil == null) {
                    sharePreferenceUtil = new SharePreferenceUtil( AppInfoConstant.SHAREPREFERENCE_FILE_NAME);
                }
            }
        }
        return sharePreferenceUtil;
    }


    private final SharedPreferences sp;
    private final SharedPreferences.Editor editor;

    public SharePreferenceUtil(String file) {
        sp = LPGApplication.getLPGApplication().getApplicationContext().getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void setToken(String token){
        setStringValue(AppInfoConstant.TOKEN,token);
    }
    public String getToken(Context context) {
        if (AppUtil.isAppInstalled(context, AppInfoConstant.APP_PACKAGE_NAME)) {
            try {
                Context sharedAppContext = context.createPackageContext(AppInfoConstant.APP_PACKAGE_NAME, 0);
                SharedPreferences sharedPreferences = sharedAppContext.getSharedPreferences(AppInfoConstant.SHAREPREFERENCE_FILE_NAME, Context.MODE_WORLD_READABLE);
                return sharedPreferences.getString(AppInfoConstant.TOKEN, "读取失败");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return getStringValue(AppInfoConstant.TOKEN);
    }
    public String getStringValue(String key) {
        return sp.getString(key, "");
    }
    public void setStringValue(String key,String value) {
         editor.putString(key, value);
    }


}
