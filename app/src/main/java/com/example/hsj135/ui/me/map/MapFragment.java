package com.example.hsj135.ui.me.map;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.hsj135.R;


public class MapFragment extends Fragment {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private static final int REQUEST_CODE = 0x1001;
    private LocationClient mLocationClient;
    private PoiSearch mPoiSearch;
    private LatLng latLng;

    public class MyPoiOverlay extends PoiOverlay {
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            if (getPoiResult().getAllPoi() != null){
                PoiInfo poiInfo= getPoiResult().getAllPoi().get(index);
                if (poiInfo != null) {
                    Toast.makeText(getContext(),
                                    "名称："+poiInfo.name+"\n"+
                                            "地址："+poiInfo.address+"\n"+
                                            "电话："+poiInfo.phoneNum, Toast.LENGTH_LONG)
                            .show();
                }
            }
            return super.onPoiClick(index);
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            latLng = new LatLng(location.getLatitude(), location.getLongitude());

            mBaiduMap.setMyLocationData(locData);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = root.findViewById(R.id.bmapView);
        // 检查地图视图是否成功创建
        if (mMapView == null) {
            Toast.makeText(getContext(), "地图视图创建失败", Toast.LENGTH_LONG).show();
            return root;
        }

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        mBaiduMap = mMapView.getMap();
        if (mBaiduMap == null) {
            Toast.makeText(getContext(), "百度地图对象创建失败", Toast.LENGTH_LONG).show();
            return root;
        }
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && root.getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

        }
        mBaiduMap.setMyLocationEnabled(true);

        //定位初始化
        try {
            mLocationClient = new LocationClient(getContext());
            // 其他初始化代码
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，例如显示错误信息或使用备用方案
            Toast.makeText(getContext(), "定位服务初始化失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            mLocationClient = null; // 确保为 null
        }

        //通过LocationClientOption设置LocationClient相关参数
        if (mLocationClient != null) {
            //通过LocationClientOption设置LocationClient相关参数
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);

            //设置locationClientOption
            mLocationClient.setLocOption(option);

            //注册LocationListener监听器
            MyLocationListener myLocationListener = new MyLocationListener();
            mLocationClient.registerLocationListener(myLocationListener);

            //开启地图定位图层
            mLocationClient.start();

            //自定义精度圈边框颜色
            MyLocationConfiguration mLocationConfiguration = new MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.COMPASS, true, null);
            mBaiduMap.setMyLocationConfiguration(mLocationConfiguration);
        }

        mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    mBaiduMap.clear();

                    //创建PoiOverlay对象
                    MyPoiOverlay poiOverlay = new MyPoiOverlay(mBaiduMap);

                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);

                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                    mBaiduMap.setOnMarkerClickListener(poiOverlay);
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }

            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };

        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        EditText editText = root.findViewById(R.id.editText);
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            String keyword = editText.getText().toString();
            if (keyword.isEmpty()) {
                keyword = "餐厅";
            }
            mPoiSearch.searchNearby(new PoiNearbySearchOption()
                    .location(latLng)
                    .radius(10000)
                    .keyword(keyword));
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        if (mLocationClient != null) {
            mLocationClient.stop();
        }

        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationEnabled(false);
        }

        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 修改：添加 null 检查
                if (mLocationClient != null) {
                    mLocationClient.restart();
                }
            } else {
                Toast.makeText(getActivity(), "你拒绝了GPS定位权限，无法显示当前位置", Toast.LENGTH_SHORT).show();
            }
        }
    }

}