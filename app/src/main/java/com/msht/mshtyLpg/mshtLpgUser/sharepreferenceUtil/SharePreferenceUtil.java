package com.msht.mshtyLpg.mshtLpgUser.sharepreferenceUtil;

import android.content.Context;
import android.content.SharedPreferences;

import com.msht.mshtyLpg.mshtLpgUser.application.LPGApplication;

public class SharePreferenceUtil {

    private static SharePreferenceUtil sharePreferenceUtil ;
    public static SharePreferenceUtil getSharePreferenceUtilInstance( ) {
        if(sharePreferenceUtil == null) {
            synchronized (SharePreferenceUtil.class) {
                if (sharePreferenceUtil == null) {
                    sharePreferenceUtil = new SharePreferenceUtil( "lpg");
                }
            }
        }
        return sharePreferenceUtil;
    }


    private final SharedPreferences sp;
    private final SharedPreferences.Editor editor;
    private final Context mContext;

    public SharePreferenceUtil(String file) {
        sp = LPGApplication.getLPGApplication().getApplicationContext().getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
        mContext = LPGApplication.getLPGApplication();
    }
}
