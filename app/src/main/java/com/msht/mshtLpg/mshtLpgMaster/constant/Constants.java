package com.msht.mshtLpg.mshtLpgMaster.constant;

import com.msht.mshtLpg.mshtLpgMaster.BuildConfig;

public class Constants {
    //凡是接口里有replacelist的都去掉，改为增加一个retrieveAmount字段，空瓶置换有单独接口返回数据
    public static final String BASE_URL = BuildConfig.DEBUG ? "http://192.168.0.104:7799/msht/lpgEmpOrder/": "";
    //18599988888
    public static final String LOGIN = BASE_URL+"login";

    public static final String QUERY_ORDERS = BASE_URL+"getOrderByPage";
    //订单列表点击订单进入订单详情，订单状态是待验瓶时，不只有金额，提交后才有金额，不同的订单状态，点击跳转到相应的订单详情
    public static final String ORDER_DETAIL =  BASE_URL+"getOrderDetail";
    //扫码验瓶 0重 1空,(参数填bottleCode == 9000008 ，verifyType = 1 有数据)
    public static final String SCAN_BOTTLE_QR_CODE = BASE_URL+"verifyBottle";
    //扫码后的订单详情需要实时地再去获取最新气价和押金（siteid = 2有数据）
    public static final String  GET_GAS_AND_DEPOSIT= BASE_URL+"getGasAndDeposit";
    //扫码后的订单详情需要实时地再去获取最新运费，只跟无电梯时的楼层有关
    public static final String  GET_Delivery_FEE = BASE_URL+"getAllDeliveryFee";
   //提交订单，等待付款 （orderId 111，isDelivery 0 orderType 1 deliveryBottleIds 15），
    public static final String  POST_ORDER= BASE_URL+"orderMsgComfirm";
    //获取自有产权钢瓶置换价格
    public static final String  QUERY_REPLACE_PRICE=BASE_URL+"queryReplacePrice";
    //微信付款
    public static final String WEIXIN_PAY = "https://smart-market.msbapp.cn:442/msht/weixinScanPay/scanPay2";
    //查询订单状态是否已付款
    public static final String QUERY_ORDER_PAY_STATUS = BASE_URL+"queryOrder";
   //订单详情提交页面，点击自有产权瓶时的查询接口
 public static final String RELACE_BOTTLE_LIST= BASE_URL+"getReplaceBottleList";






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
    public static final String EXCHANGE_DISCOUNT = "EXCHANGE_DISCOUNT";
    public static final String SUBSTITUTION_LIST = BASE_URL + "getSubstitutionList";

    public static final int EDIT_FLOOR_REQUEST_CODE = 101;
    public static final String FLOOR = "floor";
    public static final String IS_ELEVATOR = "IS_ELEVATOR";
}
