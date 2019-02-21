package com.msht.mshtlpgmaster.util;


import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.msht.mshtlpgmaster.application.LPGApplication;


/**
 * 与尺寸或者计算有关的操作的工具类
 */

public class DimenUtil {

    public static int getScreenWidth() {
        Resources resources = LPGApplication.getLPGApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();

        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight() {
        return LPGApplication.getLPGApplicationContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int dip2px(float dipValue) {
        final float scale = LPGApplication.getLPGApplicationContext().getResources().getDisplayMetrics().density;
        return (int)Math.ceil(dipValue * scale);
    }

    public static int px2dip(float pxValue) {
        final float scale = LPGApplication.getLPGApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
