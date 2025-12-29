package com.example.hsj135.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        Glide.with(this).load("https://wimg.588ku.com/gif320/24/07/09/5d6394b3084c462dac2f54c9952dee6b.gif").into(imageView);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                isBack = true;
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isSkip&&!isBack) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },5000);
    }

    public void skip(View view) {
        isSkip = true;
        Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
        startActivity(intent);

    }

}