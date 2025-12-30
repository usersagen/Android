package com.example.hsj135.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.hsj135.R;

public class SplashActivity extends AppCompatActivity {
    private boolean isSkip;
    private boolean isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = findViewById(R.id.imageView);

        // 显示GIF
        Glide.with(this)
                .load("https://wimg.588ku.com/gif320/24/07/09/5d6394b3084c462dac2f54c9952dee6b.gif")
                .into(imageView);

        // 预加载GIF（优化后续使用）
        Glide.with(this)
                .load("https://wimg.588ku.com/gif320/24/07/09/5d6394b3084c462dac2f54c9952dee6b.gif")
                .preload();

        // 返回键处理
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                isBack = true;
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        // 5秒后自动跳转
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isSkip && !isBack) {
                    navigateToNextScreen();
                }
                finish();
            }
        }, 5000);
    }

    // 跳转按钮点击事件
    public void skip(View view) {
        isSkip = true;
        navigateToNextScreen();
    }

    // 统一的跳转逻辑
    private void navigateToNextScreen() {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isFirst = sharedPreferences.getBoolean("isFirst", true);

        if (isFirst) {
            // 第一次启动：跳转到引导页
            Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
            startActivity(intent);
            // 标记已经看过引导页
            sharedPreferences.edit().putBoolean("isFirst", false).apply();
        } else {
            // 不是第一次：直接跳转到主页面
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}