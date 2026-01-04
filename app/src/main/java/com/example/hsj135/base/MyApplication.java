package com.example.hsj135.base;

import android.app.Application;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

import cn.bmob.v3.Bmob;

public class MyApplication extends MultiDexApplication {

    private static final String TAG = "MyApplication";
    private static final String BMOB_APPLICATION_ID = "d2402adb1808bc23e26de48cf3b02b46";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MyApplication", "应用启动");

        // 1. 必须先设置隐私合规为 true
        // 注意：必须在所有百度SDK初始化之前调用！
        try {
            // 设置地图SDK的隐私合规
            SDKInitializer.setAgreePrivacy(getApplicationContext(), true);

            // 特别注意：定位SDK也需要单独设置隐私合规
            // 百度定位SDK v7.x版本需要额外设置
            LocationClient.setAgreePrivacy(true);

            Log.d("MyApplication", "隐私合规已设置为 true");
        } catch (Exception e) {
            Log.e("MyApplication", "设置隐私合规失败", e);
        }

        // 2. 初始化百度地图 SDK
        try {
            // 等待隐私合规设置生效
            Thread.sleep(100);

            SDKInitializer.initialize(getApplicationContext());
            SDKInitializer.setCoordType(CoordType.BD09LL);
            Log.d("MyApplication", "百度地图 SDK 初始化成功");
        } catch (Exception e) {
            Log.e("MyApplication", "百度地图初始化失败", e);
        }

        // 3. 初始化 Bmob
        try {
            Bmob.initialize(this, "d2402adb1808bc23e26de48cf3b02b46");
            Log.d("MyApplication", "Bmob 初始化成功");
        } catch (Exception e) {
            Log.e("MyApplication", "Bmob 初始化失败", e);
        }
    }
}