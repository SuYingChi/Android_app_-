package com.msht.mshtlpgmaster.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.NaviPara;
import com.amap.api.maps2d.overlay.PoiOverlay;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtlpgmaster.Bean.LocationBean;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.adapter.PoiAdapter;
import com.msht.mshtlpgmaster.adapter.PoiSearchAdapter;
import com.msht.mshtlpgmaster.customView.ListViewForScrollView;
import com.msht.mshtlpgmaster.customView.SelecteMapDialog;
import com.msht.mshtlpgmaster.customView.TopBarView;
import com.msht.mshtlpgmaster.mapAddress.ALocationClientFactory;
import com.msht.mshtlpgmaster.mapAddress.PoiSearchTask;
import com.msht.mshtlpgmaster.util.AppUtil;
import com.msht.mshtlpgmaster.util.DimenUtil;
import com.msht.mshtlpgmaster.util.LogUtils;
import com.msht.mshtlpgmaster.util.PermissionUtils;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.util.SensorEventHelper;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SelectAddressActivity extends BaseActivity implements PermissionUtils.PermissionRequestFinishListener, AMapLocationListener, PoiSearch.OnPoiSearchListener, AMap.OnMapClickListener, GeocodeSearch.OnGeocodeSearchListener, AMap.InfoWindowAdapter, SelecteMapDialog.OnSelectMapListener {
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.id_cancel)
    TextView tvCancel;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.id_current_address)
    TextView tvCurrent;
    @BindView(R.id.id_current_recycleView)
    RecyclerView mCurrentRecycleView;
    @BindView(R.id.id_search_recycleView)
    ListViewForScrollView mSearchRecycleView;
    @BindView(R.id.id_current_layout)
    View layoutCurrent;
    @BindView(R.id.id_search_layout)
    View layoutSearch;
    @BindView(R.id.id_edit_layout)
    View layoutEdit;
    @BindView(R.id.top_bar)
    TopBarView topBarView;
    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.location)
    ImageView locationBtn;
    @BindView(R.id.collpsed)
    ImageView collpsed;
    @BindView(R.id.maplayout)
    RelativeLayout maplayout;
    private String mCity;
    private String mArea;
    private Unbinder unbinder;
    private AMapLocationClient locationClient;
    private PoiAdapter poiAdapter;
    private PoiSearchAdapter searchAdapter;
    private Context mContext;
    private ArrayList<HashMap<String, String>> mList = new ArrayList<HashMap<String, String>>();
    private String lat;
    private String lon;
    private String addressDescribe;
    private String addressName;
    private String addressStr;
    private AMap aMap;
    private SensorEventHelper mSensorHelper;
    private boolean mFirstFix = false;
    private AMapLocationClientOption locationOption;
    private Circle mCircle;
    private Marker mLocMarker;
    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    private Sensor mSensor;
    private PoiSearchTask ponSearchTask;
    private GeocodeSearch geocoderSearch;
    private LatLonPoint latLonPoint;
    private float accuracy;
    private boolean isLocationBySelf = false;
    private int maplayoutHeight=-100;
    private SelecteMapDialog selectMapDialog;
    private Marker selectMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        mContext = this;
        unbinder = ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        topBarView.setLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("addressName", addressName);
                intent.putExtra("addressDescribe", addressDescribe);
                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                intent.putExtra("mArea", mArea);
                intent.putExtra("mCity", mCity);
                setResult(1, intent);
                finish();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCurrentRecycleView.setLayoutManager(linearLayoutManager);
        mSearchRecycleView.setLayoutManager(layoutManager);
        poiAdapter = new PoiAdapter(this);
        mCurrentRecycleView.setAdapter(poiAdapter);
        searchAdapter = new PoiSearchAdapter(mContext, mList);
        mSearchRecycleView.setAdapter(searchAdapter);
        tvCancel.setEnabled(false);
        MyTextWatcher myTextWatcher = new MyTextWatcher();
        etSearch.addTextChangedListener(myTextWatcher);
        etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tvCancel.setEnabled(true);
                tvCancel.setVisibility(View.VISIBLE);
                layoutCurrent.setVisibility(View.GONE);
                layoutSearch.setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchAdapter.setOnItemClickListener(new PoiSearchAdapter.OnItemSelectClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mArea = mList.get(position).get("mArea");
                mCity = mList.get(position).get("mCity");
                lat = mList.get(position).get("latitude");
                lon = mList.get(position).get("longitude");
                addressDescribe = mList.get(position).get("addressDescribe");
                addressName = mList.get(position).get("addressName");
                Intent intent = new Intent();
                intent.putExtra("addressName", addressName);
                intent.putExtra("addressDescribe", addressDescribe);
                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                intent.putExtra("mArea", mArea);
                intent.putExtra("mCity", mCity);
                setResult(1, intent);
                finish();
            }
        });
        poiAdapter.setOnItemClickListener(new PoiAdapter.OnItemSelectClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                LocationBean bean = poiAdapter.getBeanList().get(position);
                mArea = bean.getArea();
                mCity = bean.getCity();
                lat = String.valueOf(bean.getLat());
                lon = String.valueOf(bean.getLon());
                addressDescribe = bean.getContent();
                addressName = bean.getTitle();
                Intent intent = new Intent();
                intent.putExtra("addressName", addressName);
                intent.putExtra("addressDescribe", addressDescribe);
                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                intent.putExtra("mArea", mArea);
                intent.putExtra("mCity", mCity);
                setResult(1, intent);
                finish();
            }
        });
        SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        maplayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(maplayoutHeight==-100){
                    maplayoutHeight = maplayout.getMeasuredHeight();
                }
            }
        });
        collpsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(maplayout.getVisibility()==View.GONE){
                    maplayout.setVisibility(View.VISIBLE);
                    createDropAnim(maplayout,0,maplayoutHeight).start();
                }else if(maplayout.getVisibility()==View.VISIBLE){
                    ValueAnimator va = createDropAnim(maplayout, maplayoutHeight, 0);
                    va.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            maplayout.setVisibility(View.GONE);
                        }
                    });
                    va.start();
                }
            }
        });
    }
    private ValueAnimator createDropAnim(final  View view, int start, int end) {
        ValueAnimator va = ValueAnimator.ofInt(start, end);
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);//设置高度
                Log.d("animation","value==="+value);
            }
        });
        return va;
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
          //  aMap.setLocationSource(this);// 设置定位监听
            aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(false);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            aMap.setOnMapClickListener(this);
            aMap.setInfoWindowAdapter(this);
        }
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        if(mSensor!=null) {
            mSensorHelper = new SensorEventHelper(this);
            mSensorHelper.registerSensorListener();
        }
        ponSearchTask = PoiSearchTask.getInstance(this).setAdapter(poiAdapter);
        locationClient = new AMapLocationClient(this);
        locationOption = new AMapLocationClientOption();
        //设置定位监听
        locationClient.setLocationListener(this);
        //设置为高精度定位模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationOption.setInterval(20000);
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        locationClient.startLocation();
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //手动启动定位，不然会等到上次定位结束才启动本次手动定位
                locationClient.stopLocation();
                isLocationBySelf = true;
                locationClient.startLocation();
            }
        });

    }

    @OnClick({R.id.id_cancel, R.id.id_edit_layout, R.id.id_current_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_cancel:
                tvCancel.setVisibility(View.GONE);
                layoutSearch.setVisibility(View.GONE);
                layoutCurrent.setVisibility(View.VISIBLE);
                break;
            case R.id.id_edit_layout:
                tvCancel.setEnabled(true);
                tvCancel.setVisibility(View.VISIBLE);
                layoutCurrent.setVisibility(View.GONE);
                layoutSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.id_current_address:
                Intent intent = new Intent();
                intent.putExtra("addressName", addressName);
                intent.putExtra("addressDescribe", addressDescribe);
                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                intent.putExtra("mArea", mArea);
                intent.putExtra("mCity", mCity);
                setResult(1, intent);
                finish();
                break;
            default:
                break;

        }
    }

    @Override
    public void onBackFromSettingPage() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            finish();
        } else {
            locationClient.startLocation();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        PermissionUtils.requestPermissions(this, this, Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        if (mSensor!=null&&mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
            mSensorHelper.setCurrentMarker(null);
            mSensorHelper = null;
        }
        deactivate();
        aMap.clear();
        mLocMarker=null;
        mFirstFix = false;
        isLocationBySelf = false;
        etSearch.setText("");
        tvCancel.setVisibility(View.GONE);
        layoutSearch.setVisibility(View.GONE);
        layoutCurrent.setVisibility(View.VISIBLE);
    }
    public void deactivate() {
        if (locationClient != null) {
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient = null;
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        finish();
    }

    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        initMap();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            accuracy = aMapLocation.getAccuracy();
            Log.d("location", "onLocationChanged: ");
            //获取定位信息
            double latitude = aMapLocation.getLatitude();
            lat = latitude + "";
            double longitude = aMapLocation.getLongitude();
            lon = longitude + "";
            mCity = aMapLocation.getCity();
            addressName = aMapLocation.getAoiName();
            addressDescribe = aMapLocation.getProvince() + aMapLocation.getCity()
                    + aMapLocation.getDistrict()
                    + aMapLocation.getStreet() + aMapLocation.getStreetNum();
            if (!TextUtils.isEmpty(aMapLocation.getPoiName())) {
                addressStr = addressDescribe + aMapLocation.getAoiName() + "附近";
            } else {
                addressStr = addressDescribe + aMapLocation.getAoiName();
            }
            tvCurrent.setText(addressStr);
            tvCity.setText(mCity);
            //这里是定位完成之后开始poi的附近搜索，代码在后面
             ponSearchTask.onSearch("", "", latitude, longitude);
            LatLng latlng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            if (!mFirstFix) {
                mFirstFix = true;
                addCircle(latlng, aMapLocation.getAccuracy());//添加定位精度圆
                addMarker(latlng);//添加定位图标
                if(mSensor!=null) {
                    mSensorHelper.setCurrentMarker(mLocMarker);//定位图标旋转
                }
            } else {
                mCircle.setCenter(latlng);
                mCircle.setRadius(aMapLocation.getAccuracy());
                mLocMarker.setPosition(latlng);
            }
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18));
            if(isLocationBySelf){
                isLocationBySelf = false;
                locationClient.stopLocation();
            }
        } else {
            Log.d("aMapLocation=", aMapLocation.getErrorInfo() + "错误码=" + aMapLocation.getErrorCode());
        }
    }

    private void addCircle(LatLng latlng, double radius) {
        CircleOptions options = new CircleOptions();
        options.strokeWidth(1f);
        options.fillColor(FILL_COLOR);
        options.strokeColor(STROKE_COLOR);
        options.center(latlng);
        options.radius(radius);
        mCircle = aMap.addCircle(options);
    }

    private void addMarker(LatLng latlng) {
        if (mLocMarker != null) {
            return;
        }
        Bitmap bMap;
        if(mSensor!=null) {
             bMap = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.navi_map_gps_locked);
        }else {
             bMap = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.gps_point);
        }
        BitmapDescriptor des = BitmapDescriptorFactory.fromBitmap(bMap);
        MarkerOptions options = new MarkerOptions();
        options.icon(des);
        options.anchor(0.5f, 0.5f);
        options.position(latlng);
        mLocMarker = aMap.addMarker(options);
        mLocMarker.setTitle("我的当前位置");
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int rCode) {
        if (rCode == 1000) {
            if (poiResult != null && poiResult.getQuery() != null) {
                mList.clear();
                ArrayList<PoiItem> items = poiResult.getPois();
                if (items != null && items.size() > 0) {
                    locationClient.stopLocation();
                    aMap.clear();// 清理之前的图标
                    mLocMarker=null;
                    mFirstFix=false;
                    PoiOverlay poiOverlay = new PoiOverlay(aMap, items);
                    poiOverlay.removeFromMap();
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                }
                for (PoiItem item : items) {
                    //获取经纬度对象
                    LatLonPoint llp = item.getLatLonPoint();
                    double lon = llp.getLongitude();
                    double lat = llp.getLatitude();
                    String longitude = Double.toString(lon);
                    String latitude = Double.toString(lat);
                    String title = item.getTitle();
                    String city = item.getCityName();
                    String area = item.getBusinessArea();
                    String mContent = item.getProvinceName() + item.getCityName()
                            + item.getAdName()
                            + item.getSnippet();
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("longitude", longitude);
                    map.put("latitude", latitude);
                    map.put("addressName", title);
                    map.put("addressDescribe", mContent);
                    map.put("mCity", city);
                    map.put("mArea", area);
                    mList.add(map);
                }
                searchAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    @Override
    public void onMapClick(LatLng latLng) {
        locationClient.stopLocation();
        lat= latLng.latitude+"";
        lon = latLng.longitude+ "";
        latLonPoint = new LatLonPoint(latLng.latitude, latLng.longitude);
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置异步逆地理编码请求
        showCenterLodaingDialog();
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int rCode) {
        dismissLoading();
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (regeocodeResult != null && regeocodeResult.getRegeocodeAddress() != null
                    && regeocodeResult.getRegeocodeAddress().getFormatAddress() != null) {
                mCity = regeocodeResult.getRegeocodeAddress().getCity();
                addressName = regeocodeResult.getRegeocodeAddress().getFormatAddress();
                addressDescribe = regeocodeResult.getRegeocodeAddress().getProvince() + regeocodeResult.getRegeocodeAddress().getCity()
                        + regeocodeResult.getRegeocodeAddress().getDistrict()
                        + regeocodeResult.getRegeocodeAddress().getNeighborhood() + regeocodeResult.getRegeocodeAddress().getStreetNumber();
                addressStr = addressName;
                tvCurrent.setText(addressStr);
                tvCity.setText(mCity);
                //这里是定位完成之后开始poi的附近搜索，代码在后面
                ponSearchTask.onSearch("", "", latLonPoint.getLatitude(), latLonPoint.getLongitude());
                LatLng latlng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
                if (!mFirstFix) {
                    mFirstFix = true;
                    addCircle(latlng, accuracy);//添加定位精度圆
                    addMarker(latlng);//添加定位图标
                    if(mSensor!=null) {
                        mSensorHelper.setCurrentMarker(mLocMarker);//定位图标旋转
                    }
                } else {
                    mCircle.setCenter(latlng);
                    mCircle.setRadius(accuracy);
                    mLocMarker.setPosition(latlng);
                }
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18));
            } else {
                PopUtil.toastInBottom("没搜索到具体位置名称");
            }
        } else {
            PopUtil.toastInBottom( rCode);
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    @Override
    public View getInfoWindow(Marker marker) {
        selectMarker=marker;
        View view = getLayoutInflater().inflate(R.layout.poikeywordsearch_uri, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(marker.getTitle());

        TextView snippet = (TextView) view.findViewById(R.id.snippet);
        snippet.setText(marker.getSnippet());
        ImageButton button = (ImageButton) view
                .findViewById(R.id.start_amap_app);
        // 调起高德地图app
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSlectMapDialog();
            }
        });
        return view;
    }
  private void showSlectMapDialog() {
      if (!isFinishing() && selectMapDialog == null) {
          selectMapDialog = new SelecteMapDialog(this,this);
          selectMapDialog.show();
      } else if (!isFinishing() && !selectMapDialog.isShowing()) {
          selectMapDialog.show();
      }
  }

    /**
     * 调起高德地图导航功能，如果没安装高德地图，会进入异常，可以在异常中处理，调起高德地图app的下载页面
     */
    public void startAMapNavi(Marker marker) {
        // 构造导航参数
        NaviPara naviPara = new NaviPara();
        // 设置终点位置
        naviPara.setTargetPoint(marker.getPosition());
        // 设置导航策略，这里是避免拥堵
        naviPara.setNaviStyle(AMapUtils.DRIVING_AVOID_CONGESTION);

        // 调起高德地图导航
        try {
            AMapUtils.openAMapNavi(naviPara, getApplicationContext());
        } catch (com.amap.api.maps2d.AMapException e) {

            // 如果没安装会进入异常，调起下载页面
            AMapUtils.getLatestAMapApp(getApplicationContext());

        }

    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onSelectGaode() {
        startAMapNavi(selectMarker);

    }

    @Override
    public void onSelectBaidu() {
        double[] latlong = gcj02_To_Bd09(selectMarker.getPosition().latitude, selectMarker.getPosition().longitude);
        goToBaiduMap(latlong[0], latlong[1], selectMarker.getTitle());
    }


    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                doSearchQuery(s.toString().trim());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    private void doSearchQuery(String keyWord) {
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
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        //必须重写
        if (mapView != null) {
            mapView.onDestroy();
        }
        if (null != locationClient) {
            locationClient.onDestroy();
        }
    }
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
    private void goToBaiduMap(double mLat,double mLng,String mAddressStr) {
        if (!AppUtil.isInstalled("com.baidu.BaiduMap.auto")) {
            PopUtil.toastInBottom( "请先安装百度地图客户端");
            return;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?destination=latlng:"
                + mLat + ","
                + mLng + "|name:" + mAddressStr + // 终点
                "&mode=driving" + // 导航路线方式
                "&src="+getPackageName()));

     //   intent.setData(Uri.parse("baidumap://map/navi?query=+"+mAddressStr+"+&src=andr.baidu.openAPIdemo"));
        startActivity(intent); // 启动调用
    }


}
