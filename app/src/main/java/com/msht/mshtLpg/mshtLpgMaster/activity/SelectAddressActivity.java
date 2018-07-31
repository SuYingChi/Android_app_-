package com.msht.mshtLpg.mshtLpgMaster.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.PoiAdapter;
import com.msht.mshtLpg.mshtLpgMaster.customView.ListViewForScrollView;
import com.msht.mshtLpg.mshtLpgMaster.mapAddress.ALocationClientFactory;
import com.msht.mshtLpg.mshtLpgMaster.mapAddress.PoiSearchTask;
import com.msht.mshtLpg.mshtLpgMaster.util.PermissionUtils;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SelectAddressActivity extends BaseActivity implements  PermissionUtils.PermissionRequestFinishListener, AMapLocationListener, PoiSearch.OnPoiSearchListener {
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
    private String mCity;
    private String mArea;
    private Unbinder unbinder;
    private AMapLocationClient locationClient;
    private PoiAdapter poiAdapter;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        mContext=this;
        unbinder = ButterKnife.bind(this);
        locationClient = ALocationClientFactory.createLocationClient(this, ALocationClientFactory.createDefaultOption(),this);
        PermissionUtils.requestPermissions(this, this, new String[]{Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION});

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                PermissionUtils.requestPermissions(this, this, new String[]{Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION});
            }else {
                locationClient.startLocation();
            }
        }else {
            locationClient.startLocation();
        }*/
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCurrentRecycleView.setLayoutManager(linearLayoutManager);
        poiAdapter = new PoiAdapter(this);
        mCurrentRecycleView.setAdapter(poiAdapter);
        MyTextWatcher myTextWatcher=new MyTextWatcher();
        etSearch.addTextChangedListener(myTextWatcher);
    }
    @Override
    public void onBackFromSettingPage() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            finish();
        }else {
            locationClient.startLocation();
        }
    }
    @Override
    public void onPermissionRequestDenied(List<String> permissions) {
        finish();
    }
    @Override
    public void onPermissionRequestSuccess(List<String> permissions) {
        locationClient.startLocation();
    }
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            //获取定位信息
            double latitude = aMapLocation.getLatitude();
            double longitude = aMapLocation.getLongitude();
            mCity=aMapLocation.getCity();
            String addressStr =aMapLocation.getProvince()+ aMapLocation.getCity()
                    + aMapLocation.getDistrict()
                    +aMapLocation.getStreet()+aMapLocation.getStreetNum();
            if (!TextUtils.isEmpty(aMapLocation.getPoiName())) {
                addressStr += aMapLocation.getAoiName()+ "附近";
            }else {
                addressStr = addressStr + aMapLocation.getAoiName() ;
            }
            tvCurrent.setText(addressStr);
            tvCity.setText(mCity);
            //这里是定位完成之后开始poi的附近搜索，代码在后面
            PoiSearchTask.getInstance(this).setAdapter(poiAdapter).onSearch("", "",latitude,longitude);
        }else {
            Log.d("aMapLocation=",aMapLocation.getErrorInfo());
        }

    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {


    }
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {}
    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length()>0) {
                doSearchQuery(s.toString().trim());
            }
        }
        @Override
        public void afterTextChanged(Editable s) {}
    }

    private void doSearchQuery(String keyWord) {
        int currentPage = 0;
        /*
         *  Poi查询条件类
         *  第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
         */
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", mCity);
        // 设置每页最多返回多少条poiitem
        query.setPageSize(16);
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
    }
}
