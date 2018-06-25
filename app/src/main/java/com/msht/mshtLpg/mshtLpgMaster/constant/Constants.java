package com.msht.mshtLpg.mshtLpgMaster.constant;

import com.msht.mshtLpg.mshtLpgMaster.BuildConfig;

public class Constants {
    public static final String BASE_URL = BuildConfig.DEBUG ? "http://192.168.2.137:7799/msht/lpgEmpOrder/": "";
    public static final String APP_PACKAGE_NAME = "com.msht.mshtLpg.mshtLpgMaster";
    public static final String LOGIN_SP_FILE_NAME = "lpg";
    public static final String TOKEN = "token";
    public static final String FILE_NAME = "lpgImage";
    public static final String LOGIN = BASE_URL+"login";


}
