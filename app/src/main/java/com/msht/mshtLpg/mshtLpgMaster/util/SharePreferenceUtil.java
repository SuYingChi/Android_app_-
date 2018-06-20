package com.msht.mshtLpg.mshtLpgMaster.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;

public class SharePreferenceUtil {

    private static SharePreferenceUtil sharePreferenceUtil;
    private   SharedPreferences loginSp;
    private   SharedPreferences.Editor loginEditor;


    public SharePreferenceUtil(String sharePreferencefileName) {
        loginSp = LPGApplication.getLPGApplicationContext().getSharedPreferences(sharePreferencefileName, Context.MODE_PRIVATE);
        loginEditor = loginSp.edit();
    }

    public static SharePreferenceUtil getInstance() {
        if (sharePreferenceUtil == null) {
            synchronized (SharePreferenceUtil.class) {
                if (sharePreferenceUtil == null) {
                    sharePreferenceUtil = new SharePreferenceUtil(Constants.LOGIN_SP_FILE_NAME);
                }
            }
        }
        return sharePreferenceUtil;
    }


    public static SharedPreferences getSharePreferenceFile(String sharePreferencefileName) {
        return LPGApplication.getLPGApplicationContext().getSharedPreferences(sharePreferencefileName, Context.MODE_PRIVATE);
    }

    public  void setToken(String token) {
        loginEditor.putString(Constants.TOKEN, token);
    }

    public  String getToken() {
        return getStringValue(Constants.LOGIN_SP_FILE_NAME, Constants.TOKEN);
    }

    public static String  getStringValue(String fileName,String key) {
        SharedPreferences sharePreferenceFile = getSharePreferenceFile(fileName);
        return sharePreferenceFile.getString(key,"");
    }

    public static void setStringValue(String fileName, String key, String value) {
        SharedPreferences sharePreferenceFile = getSharePreferenceFile(fileName);
        sharePreferenceFile.edit().putString(key,value).apply();
    }


}
