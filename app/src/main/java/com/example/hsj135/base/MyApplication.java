package com.example.hsj135.base;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import cn.bmob.v3.Bmob;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            // 新版本 Bmob 可能需要这样初始化
            cn.bmob.v3.Bmob.initialize(this, "d2402adb1808bc23e26de48cf3b02b46");

            // 或者使用 BmobSdk（根据你实际的SDK版本）
            // BmobSdk.init(this, "d2402adb1808bc23e26de48cf3b02b46");
        } catch (Exception e) {
            e.printStackTrace();
            // 记录日志
            Log.e("MyApplication", "Bmob初始化失败", e);
        }
    }
}
