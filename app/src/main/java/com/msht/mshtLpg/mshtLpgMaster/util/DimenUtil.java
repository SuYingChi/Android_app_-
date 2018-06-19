package com.msht.mshtLpg.mshtLpgMaster.util;


import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;


/**
 * 与尺寸或者计算有关的操作的工具类
 */

public class DimenUtil {

    public static int getScreenWidth() {
        Resources resources = LPGApplication.getLPGApplication().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();

        return dm.widthPixels;
    }
    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight() {
        return LPGApplication.getLPGApplication().getResources().getDisplayMetrics().heightPixels;
    }

    public static int dip2px(float dipValue) {
        final float scale = LPGApplication.getLPGApplication().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = LPGApplication.getLPGApplication().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
