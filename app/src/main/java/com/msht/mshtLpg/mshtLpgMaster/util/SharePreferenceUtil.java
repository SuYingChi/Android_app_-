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
        loginEditor.commit();
    }

    public  String getToken() {
        return getSPStringValue(Constants.LOGIN_SP_FILE_NAME, Constants.TOKEN);
    }

    public static String getSPStringValue(String fileName, String key) {
        SharedPreferences sharePreferenceFile = getSharePreferenceFile(fileName);
        return sharePreferenceFile.getString(key,"");
    }
    public static String  getLoginSpStringValue(String key) {
        SharedPreferences sharePreferenceFile = getSharePreferenceFile(Constants.LOGIN_SP_FILE_NAME);
        return sharePreferenceFile.getString(key,"");
    }
    public static int  getLoginSpIntValue(String key) {
        SharedPreferences sharePreferenceFile = getSharePreferenceFile(Constants.LOGIN_SP_FILE_NAME);
        return sharePreferenceFile.getInt(key,0);
    }
    public static void setSPStringValue(String fileName, String key, String value) {
        SharedPreferences sharePreferenceFile = getSharePreferenceFile(fileName);
        sharePreferenceFile.edit().putString(key,value).apply();
    }
    public static void setLoginSpStringValue(String key, String value) {
        SharedPreferences sharePreferenceFile = getSharePreferenceFile(Constants.LOGIN_SP_FILE_NAME);
        sharePreferenceFile.edit().putString(key,value).apply();
    }
    public static void setLoginSpIntValue(String key, int value) {
        SharedPreferences sharePreferenceFile = getSharePreferenceFile(Constants.LOGIN_SP_FILE_NAME);
        sharePreferenceFile.edit().putInt(key,value).apply();
    }

}
