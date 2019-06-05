package com.msht.mshtlpgmaster.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.NaviPara;
import com.amap.api.services.poisearch.PoiSearch;
import com.msht.mshtlpgmaster.application.LPGApplication;

public class MapUtil {
    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
     *
     * @param lat
     * @param lon
     */
    public static double[] gcj02_To_Bd09(double lat, double lon) {
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double tempLat = z * Math.sin(theta) + 0.006;
        double tempLon = z * Math.cos(theta) + 0.0065;
        double[] gps = {tempLat, tempLon};
        return gps;
    }
    /**
     * 跳转百度地图
     */
    public static void goToBaiduMap(double mLat, double mLng, String mAddressStr, Activity activity) {
        if (!AppUtil.isInstalled("com.baidu.BaiduMap.auto")) {
            PopUtil.toastInBottom( "请先安装百度地图客户端");
            return;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?destination=latlng:"
                + mLat + ","
                + mLng + "|name:" + mAddressStr + // 终点
                "&mode=driving" + // 导航路线方式
                "&src="+ LPGApplication.getLPGApplicationContext().getPackageName()));

        //   intent.setData(Uri.parse("baidumap://map/navi?query=+"+mAddressStr+"+&src=andr.baidu.openAPIdemo"));
        activity.startActivity(intent); // 启动调用
    }
    /**
     * 跳转腾讯地图
     * @param latitude
     * @param longitude
     * @param addressName
     */
    public static void goToTencentMap(double latitude, double longitude, String addressName, Activity activity) {
        if (!AppUtil.isInstalled("com.tencent.map")) {
            PopUtil.toastInBottom("请先安装腾讯地图客户端");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("qqmap://map/routeplan?type=drive")
                .append("&tocoord=").append(latitude).append(",").append(longitude).append("&to=" +addressName);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        activity.startActivity(intent);
    }
    /**
     * 调起高德地图导航功能，如果没安装高德地图，会进入异常，可以在异常中处理，调起高德地图app的下载页面
     */
    public static void startAMapNavi(LatLng latlon) {
        // 构造导航参数
        NaviPara naviPara = new NaviPara();
        // 设置终点位置
        naviPara.setTargetPoint(latlon);
        // 设置导航策略，这里是避免拥堵
        naviPara.setNaviStyle(AMapUtils.DRIVING_AVOID_CONGESTION);

        // 调起高德地图导航
        try {
            AMapUtils.openAMapNavi(naviPara, LPGApplication.getLPGApplicationContext());
        } catch (com.amap.api.maps2d.AMapException e) {

            // 如果没安装会进入异常，调起下载页面
            AMapUtils.getLatestAMapApp(LPGApplication.getLPGApplicationContext());

        }

    }
    public static void doSearchQuery(String mCity, String keyWord, PoiSearch.OnPoiSearchListener poiSearchListener, Context context) {
        int currentPage = 0;
        /*
         *  Poi查询条件类
         *  第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
         */
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", mCity);
        // 设置每页最多返回多少条poiitem
        query.setPageSize(20);
        // 设置查第一页
        query.setPageNum(currentPage);
        query.setCityLimit(true);
        // POI搜索
        PoiSearch poiSearch = new PoiSearch(context, query);
        poiSearch.setOnPoiSearchListener(poiSearchListener);
        poiSearch.searchPOIAsyn();
    }
}
