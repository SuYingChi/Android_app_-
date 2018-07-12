package com.msht.mshtLpg.mshtLpgMaster.constant;

import com.msht.mshtLpg.mshtLpgMaster.BuildConfig;

public class Constants {

    public static final String BASE_URL = BuildConfig.DEBUG ? "http://192.168.0.104:7799/msht/lpgEmpOrder/": "";
    public static final String LOGIN = BASE_URL+"login";
    public static final String QUERY_ORDERS = BASE_URL+"getOrderByPage";
    public static final String ORDER_DETAIL =  BASE_URL+"getOrderDetail";
    public static final String SCAN_BOTTLE_QR_CODE = BASE_URL+"verifyBottle";

    public static final String URL_PARAMS_LOGIN_TOKEN = "loginToken";
    public static final String URL_PARAMS_ORDER_TYPE = "orderType";
    public static final String URL_PARAMS_ORDER_STATUS="orderStatus";
    public static final String URL_PARAMS_PAGE_NUM = "pageNum";
    public static final String URL_PARAMS_BOTTLE_CODE = "bottleCode";
    public static final String URL_PARAMS_VERIFYTYPE = "verifyType";


    public static final String ORDER_ID = "orderId";
    public static final String APP_PACKAGE_NAME = "com.msht.mshtLpg.mshtLpgMaster";
    public static final String LOGIN_SP_FILE_NAME = "lpg";
    public static final String TOKEN = "token";
    public static final String EMPLOYERID = "employerId";
    public static final String FILE_NAME = "lpgImage";
    public static final String HOME_TOP_CITEM ="HOME_TOP_CITEM";
    public static final String HOME_ORDERS_SCHEDULE_CITEM ="HOME_ORDERS_SCHEDULE_CITEM";
    public static final String HOME_ACTIVITY_BOTTOM_TAB_ITEM = "citem";
    public static final String HOME_FRAGMENT_TOP_TAB_ITEM = "HOME_FRAGMENT_TOP_TAB_ITEM";


    public static final String SCANED_FIVE_NUM = "SCANEDfivenum";
    public static final String SCANED_FIFTEEN_NUM = "SCANEDfifteennum";
    public static final String SCANED_FIFTY_NUM = "SCANEDfiftynum";
    public static final String EMPTY_FIVE_NUM = "emptyfivenum";
    public static final String EMPTY_FIFTEEN_NUM = "emptyfifteennum";
    public static final String EMPTY_FIFTY_NUM = "emptyfiftynum";
    public static final String ORDER_DETAIL_BEAN =  "OrderDetailbean";
    public static final String ORDER_FIVE_NUM = "fivenum";
    public static final String ORDER_FIFTEEN_NUM = "fifteennum";
    public static final String ORDER_FIFTY_NUM = "fiftynum";
    public static final String ORDER_DETAIL_PAYBEAN = "ORDER_DETAIL_PAYBEAN";
    public static final String REMAIN_FIVE_NUM = "REMAIN_FIVE_NUM";
    public static final String REMAIN_FIFTEEN_NUM = "REMAIN_FIFTEEN_NUM";
    public static final String REMAIN_FIFTY_NUM = "REMAIN_FIFTY_NUM";
    public static final int EXCHANGE_EMPTY_BOTTLE_REQUEST_CODE = 100;

    public static final String EXCHANGE_FEE = "EXCHANGE_FEE";
    public static final String EXCHANGE_FIFTEEN_FEE = "EXCHANGE_FIFTEEN_FEE";
    public static final String EXCHANGE_FIFTY_FEE = "EXCHANGE_FIFTY_FEE";
}
